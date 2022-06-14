package app.fernando.covidrastreo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import app.fernando.covidrastreo.BaseDatos.ConexionSQLiteHelper;
import app.fernando.covidrastreo.BaseDatos.ConsultasSQL;
import app.fernando.covidrastreo.Entidades.Ajuste;
import app.fernando.covidrastreo.Entidades.Contacto;
import app.fernando.covidrastreo.Entidades.Usuario;
import app.fernando.covidrastreo.Entidades.UsuarioContacto;

import app.fernando.covidrastreo.R;

import app.fernando.covidrastreo.actividades.AcercaDeActivity;

import app.fernando.covidrastreo.Utilidades.Utilidades;
import app.fernando.covidrastreo.actividades.AjustesActivity;
import app.fernando.covidrastreo.actividades.DiagnosticoActivity;
import app.fernando.covidrastreo.actividades.DietaActivity;
import app.fernando.covidrastreo.actividades.InformacionActivity;
import app.fernando.covidrastreo.actividades.ReporteActivity;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
    public Time time;
    int horaSistema, minutoSistema;
    private static final int SOLICITA_ACTIVACION = 1;
    private BluetoothAdapter bluetoothAdapter;
    List<UsuarioContacto> listaUsuarioContactoWS = new ArrayList<>();

    //JSON
    ProgressDialog progreso;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    int seleccionador = 0;

    private PendingIntent pendingIntent;
    private final static String CHANNEL_ID = "NOTIFICACION";
    private final static int NOTIFICACION_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String json = getIntent().getStringExtra("usuarioCopia");
        Usuario usuario = new Gson().fromJson(json, Usuario.class);

        //Solicitar permisos
        permisosBT();
        permisosUbicacion();
        permisoGPS();

        IntentFilter filterBusqueda = new IntentFilter(BluetoothDevice.ACTION_FOUND);

        registerReceiver(broadcastReceiver, filterBusqueda);

        //Notificación
        createNotificationChannel();

        //Segundo plano
        time = new Time();
        time.execute();

    }

    //***************************** NOTIFICACION ***************************

    public void lanzarNotificación(){
        Calendar calendar = Calendar.getInstance();
        String cadenaAMostrar = "";
        int semanaActual = calendar.get(Calendar.WEEK_OF_YEAR);
        int numeroInfectados = ConsultasSQL.retornaNumeroinfectadosSemana(semanaActual);

        if(numeroInfectados != 1){
            cadenaAMostrar = "Tuviste " + numeroInfectados + " contactos infectados esta semana";
        }else{
            cadenaAMostrar = "Tuviste " + numeroInfectados + " contacto infectado esta semana";
        }

        Intent intent = new Intent(this, ReporteActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.logo_tech_mode)
                .setContentTitle("COVID Alerta")
                .setContentText(cadenaAMostrar)
                .setPriority(NotificationCompat.PRIORITY_HIGH)

                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
        notificationManager.notify(NOTIFICACION_ID, builder.build());
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_name);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


    //***************************** JSON *************************
    @Override
    public void onResponse(JSONObject response) {
        if(seleccionador == 1) {
            Toast.makeText(getApplicationContext(), "Se ha registrado correctamente", Toast.LENGTH_SHORT).show();
        }else if(seleccionador == 2) {
            Toast.makeText(getApplicationContext(), "Mensaje: " + response, Toast.LENGTH_SHORT).show();

            Contacto contactoPrueba = new Contacto();

            JSONArray json = response.optJSONArray("contacto");
            JSONObject jsonObject = null;

            try {
                jsonObject = json.getJSONObject(0);
                contactoPrueba.setIdContacto(jsonObject.optInt("idContacto"));
                contactoPrueba.setDireccionBT(jsonObject.optString("direccionBT"));
                contactoPrueba.setEstadoInfeccion(jsonObject.optInt("estadoInfeccion"));
                contactoPrueba.setIdUsuario(jsonObject.optInt("idUsuario"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Toast.makeText(getApplicationContext(), contactoPrueba.idContacto + "", Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), contactoPrueba.getDireccionBT(), Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), contactoPrueba.estadoInfeccion + "", Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), contactoPrueba.idUsuario + "", Toast.LENGTH_SHORT).show();


        }else if(seleccionador == 3){
            UsuarioContacto usuarioContacto = null;

            JSONArray json = response.optJSONArray("usuariocontacto");

            try {
                for(int i = 0; i < json.length(); i++){
                    usuarioContacto = new UsuarioContacto();
                    JSONObject jsonObject = null;

                    jsonObject = json.getJSONObject(i);

                    usuarioContacto.setIdUsuarioContacto(jsonObject.optInt("idUsuarioContacto"));
                    usuarioContacto.setNombreUsuario(jsonObject.optString("nombreUsuario"));
                    usuarioContacto.setApellidoUsuario(jsonObject.optString("apellidoUsuario"));
                    usuarioContacto.setSexoUsuario(jsonObject.optInt("sexoUsuario"));
                    usuarioContacto.setDireccionBTUsuario(jsonObject.optString("direccionBTUsuario"));
                    usuarioContacto.setEstadoInfeccionUsuario(jsonObject.optInt("estadoInfeccionUsuario"));
                    usuarioContacto.setSemanaInfeccionUsuario(jsonObject.optInt("semanaInfeccionUsuario"));
                    usuarioContacto.setDireccionBTContacto(jsonObject.optString("direccionBTContacto"));
                    usuarioContacto.setEstadoInfeccionContacto(jsonObject.optInt("estadoInfeccionContacto"));
                    usuarioContacto.setSemanaInfeccionContacto(jsonObject.optInt("semanaInfeccionContacto"));

                    if(!ConsultasSQL.contactoRepetido(usuarioContacto)) {
                        ConsultasSQL.registrarUsuarioContactoCopia(usuarioContacto);
                    }
                    if(!ConsultasSQL.direccionBTContactoRepetido(usuarioContacto)){
                        ConsultasSQL.registrarUsuarioContactoDesdeWS(usuarioContacto);
                    }else{
                        ConsultasSQL.actualizarTablaUsuarioContacto(usuarioContacto);
                        ConsultasSQL.actualizarTablaUsuarioContactoCopia(usuarioContacto);
                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
                progreso.hide();
            }
        }
        seleccionador = 0;
        progreso.hide();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(getApplicationContext(), "No se pudo registrar" + error.toString(), Toast.LENGTH_SHORT).show();
        Log.i("Error: ", error.toString());
    }



    //*************************** BUSCANDO BLUETOOTH DISPONIBLE *******************************
    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if(BluetoothDevice.ACTION_FOUND.equals(action)){
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                if(!ConsultasSQL.buscarDireccionBD(device.getAddress())) {

                    Contacto contacto = new Contacto();
                    UsuarioContacto usuarioContacto = new UsuarioContacto();
                    usuarioContacto.setDireccionBTContacto(device.getAddress());
                    Usuario usuario = ConsultasSQL.retornaUsuario();

                    contacto.setDireccionBT(device.getAddress());
                    contacto.setEstadoInfeccion(0);
                    contacto.setIdUsuario(1);

                    ConsultasSQL.registrarContactoSQL(contacto);

                    if(!ConsultasSQL.direccionBTContactoRepetido(usuarioContacto))
                        ConsultasSQL.registrarUsuarioContacto(usuario, contacto);
                }
            }
        }
    };

    @Override
    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }

    public void buscarDispositivos(){
        if(bluetoothAdapter.isDiscovering()) {
            //    Toast.makeText(getApplicationContext(), "Ya se están buscando dispositivos", Toast.LENGTH_LONG).show();
            bluetoothAdapter.cancelDiscovery();
        }
        if(bluetoothAdapter.startDiscovery()){
            Toast.makeText(getApplicationContext(), "Buscando dispositivos", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(), "Error al buscar dispositivo", Toast.LENGTH_LONG).show();
        }
    }

    //*************************** PERMISOS *******************************
    public void permisosBT(){
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if(bluetoothAdapter == null){
            Toast.makeText(getApplicationContext(), "Su dispositivo no cuenta con Bluetooth", Toast.LENGTH_LONG).show();
        }else if(!bluetoothAdapter.isEnabled()){
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, SOLICITA_ACTIVACION);
        }

        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 3600);
        startActivity(discoverableIntent);
    }

    public void permisosUbicacion(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION}, 0);
        }
    }

    public void permisoGPS(){
        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        boolean gpsHabilitado = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if(!gpsHabilitado){
            startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), 1);
        }
    }

    //*************************** SEGUNDO PLANO *******************************
    public void hilo(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void ejecutar(){
        Time time = new Time();
        time.execute();
    }

    public class Time extends AsyncTask<Void, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {
            for(int i = 1; i <= 60; i++){
                hilo();
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            ejecutar();

            if(ConsultasSQL.retornaEscaneoAutomático())
                buscarDispositivos();

            actualizarDatosWS();

            Calendar calendar = Calendar.getInstance();
            int semanaBotonCovid = ConsultasSQL.retornaSemanaBotonCovid();

            if(semanaBotonCovid + 4 <= calendar.get(Calendar.WEEK_OF_YEAR)) {
                Usuario usuario = new Usuario();
                usuario.setSemInfUsuario(0);
                ConsultasSQL.actualizarUsuarioEstadoPositivoCovid(usuario);
                usuario = ConsultasSQL.retornaUsuario();
                ConsultasSQL.desactivarBotonCovid();
                ConsultasSQL.actualizarUsuarioContactoEstadoPositivoCovid(usuario);
            }
        }
    }

    //********************* ACTUALIZAR DATOS WEB SERVICE **********************
    public void actualizarDatosWS(){
        Calendar c = Calendar.getInstance();

        horaSistema = c.get(Calendar.HOUR_OF_DAY);
        minutoSistema = c.get(Calendar.MINUTE);

        Ajuste ajuste = ConsultasSQL.consultarAjuste();

        if(horaSistema == ajuste.horaActualizacion && minutoSistema == ajuste.minActualizacion) {
            seleccionador = 3;
            request = Volley.newRequestQueue(getApplicationContext());
            retornarListaWS();
           //Toast.makeText(getApplicationContext(), "Retornando datos", Toast.LENGTH_SHORT).show();
        }

        if(horaSistema == ajuste.horaActualizacion && minutoSistema == ajuste.minActualizacion + 1) {
            seleccionador = 1;
            cargarDatosWS();
            //Toast.makeText(getApplicationContext(), "Subiendo datos", Toast.LENGTH_SHORT).show();
        }

        if(horaSistema == ajuste.horaActualizacion && minutoSistema == ajuste.minActualizacion + 2) {
            Usuario usuario = ConsultasSQL.retornaUsuario();
            actualizarUsuarioWS(usuario);
            actualizarContactoWS(usuario);
            //Toast.makeText(getApplicationContext(), "Actualizando estado usuario", Toast.LENGTH_SHORT).show();
            //Toast.makeText(getApplicationContext(), "Estado usuario: " + usuario.estadoInfeccion, Toast.LENGTH_SHORT).show();
        }

        if(horaSistema == ajuste.horaActualizacion && minutoSistema == ajuste.minActualizacion + 3)
           lanzarNotificación();
    }


    //*************************** BOTONES *******************************
    public void escaneo(View view){
        Intent intent = new Intent(MainActivity.this, ReporteActivity.class);
        startActivity(intent);
    }

    public void diagnostico(View view){
        Intent intent = new Intent(MainActivity.this, DiagnosticoActivity.class);
        startActivity(intent);
    }

    public void informacion(View view){
        Intent intent = new Intent(MainActivity.this, InformacionActivity.class);
        startActivity(intent);
    }

    public void dieta(View view){
        Intent intent = new Intent(MainActivity.this, DietaActivity.class);
        startActivity(intent);
    }

    public void ajustes(View view){
        Intent intent = new Intent(MainActivity.this, AjustesActivity.class);
        startActivity(intent);
    }

    public void acercaDe(View view){
        Intent intent = new Intent(MainActivity.this, AcercaDeActivity.class);
        startActivity(intent);
    }


    //************************ WEB SERVICE ************************************
    private void cargarDatosWS() {
        request = Volley.newRequestQueue(getApplicationContext());
        progreso = new ProgressDialog(this);
        progreso.setMessage("Cargando...");
        progreso.show();

        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(this, "bd_covidRastreo", null, 1);
        SQLiteDatabase db = conexion.getReadableDatabase();
        UsuarioContacto usuarioContacto = null;

        try {
            Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_USUARIO_CONTACTO, null);
            while (cursor.moveToNext()){
                usuarioContacto = new UsuarioContacto();
                usuarioContacto.setIdUsuarioContacto(cursor.getInt(0));
                usuarioContacto.setNombreUsuario(cursor.getString(1));
                usuarioContacto.setApellidoUsuario(cursor.getString(2));
                usuarioContacto.setSexoUsuario(cursor.getInt(3));
                usuarioContacto.setDireccionBTUsuario(cursor.getString(4));
                usuarioContacto.setEstadoInfeccionUsuario(cursor.getInt(5));
                usuarioContacto.setSemanaInfeccionUsuario(cursor.getInt(6));
                usuarioContacto.setDireccionBTContacto(cursor.getString(7));
                usuarioContacto.setEstadoInfeccionContacto(cursor.getInt(8));
                usuarioContacto.setSemanaInfeccionContacto(cursor.getInt(9));

                if(!ConsultasSQL.contactoRepetido(usuarioContacto)) {
                    String url = "http://techmodecajamarca.com/wsJSONRegistroUsuarioContacto.php?nombreUsuario=" + usuarioContacto.nombreUsuario +
                            "&apellidoUsuario=" + usuarioContacto.apellidoUsuario + "&sexoUsuario=" + usuarioContacto.sexoUsuario + "&direccionBTUsuario=" +
                            usuarioContacto.direccionBTUsuario + "&estadoInfeccionUsuario=" + usuarioContacto.estadoInfeccionUsuario + "&semanaInfeccionUsuario=" +
                            usuarioContacto.semanaInfeccionUsuario + "&direccionBTContacto=" + usuarioContacto.direccionBTContacto + "&estadoInfeccionContacto=" +
                            usuarioContacto.estadoInfeccionContacto + "&semanaInfeccionContacto=" + usuarioContacto.semanaInfeccionContacto;

                    url = url.replace(" ", "%20");

                    jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
                    request.add(jsonObjectRequest);
                }
            }

            progreso.hide();
            db.close();

        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Error al cargar datos", Toast.LENGTH_SHORT).show();
        }

        conexion.close();

    }


    public void retornarWebService(){
        progreso = new ProgressDialog(this);
        progreso.setMessage("Consultando...");
        progreso.show();

        String url = "http://192.168.1.34:84/ejemploBDRemota/wsJSONConsulta.php?direccionBT=A4:17:31:CE:72:3E";

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);
    }

    public void retornarListaWS(){
        progreso = new ProgressDialog(this);
        progreso.setMessage("Consultando...");
        progreso.show();

        Usuario usuario = ConsultasSQL.retornaUsuario();
        String url = "http://techmodecajamarca.com/wsJSONRetornaListaUsuarioContacto.php?direccionBTUsuario=" + usuario.direccionBT;

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);
    }

    public void actualizarUsuarioWS(final Usuario usuario){
        final Usuario usuario1 = usuario;
        progreso = new ProgressDialog(this);
        progreso.setMessage("Consultando...");
        progreso.show();
        request = Volley.newRequestQueue(getApplicationContext());

        String url = "http://techmodecajamarca.com/wsJSONActualizaUsuarioContacto.php?";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progreso.hide();

                if (response.trim().equalsIgnoreCase("actualiza")) {
                    Toast.makeText(getApplicationContext(), "Se ha actualizado correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "No se ha actualizado", Toast.LENGTH_SHORT).show();
                    Log.i("RESPUESTA: ", "" + response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "No se ha podido conectar", Toast.LENGTH_SHORT).show();
                progreso.hide();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<>();

                parametros.put("direccionBTUsuario", usuario.direccionBT);
                parametros.put("estadoInfeccionUsuario", usuario.estadoInfeccion + "");
                parametros.put("semanaInfeccionUsuario", usuario.semInfUsuario + "");

                return parametros;
            }
        };

        request.add(stringRequest);

    }

    public void actualizarContactoWS(final Usuario usuario){
        final Usuario usuario1 = usuario;
        request = Volley.newRequestQueue(getApplicationContext());

        String url = "http://techmodecajamarca.com/wsJSONActualizaContacto.php?";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.trim().equalsIgnoreCase("actualiza")) {
                    Toast.makeText(getApplicationContext(), "Se ha actualizado correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "No se ha actualizado", Toast.LENGTH_SHORT).show();
                    Log.i("RESPUESTA: ", "" + response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "No se ha podido conectar", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<>();

                parametros.put("direccionBTUsuario", usuario.direccionBT);
                parametros.put("estadoInfeccionUsuario", usuario.estadoInfeccion + "");
                parametros.put("semanaInfeccionUsuario", usuario.semInfUsuario + "");

                return parametros;
            }
        };

        request.add(stringRequest);

    }

}
