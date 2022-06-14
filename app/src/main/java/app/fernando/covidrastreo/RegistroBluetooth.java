package app.fernando.covidrastreo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import app.fernando.covidrastreo.BaseDatos.ConsultasSQL;
import app.fernando.covidrastreo.Entidades.Ajuste;
import app.fernando.covidrastreo.Entidades.Usuario;

import app.fernando.covidrastreo.R;

import app.fernando.covidrastreo.actividades.ContenedorInstruccionesBTHuawei;
import app.fernando.covidrastreo.actividades.ContenedorInstruccionesBTMotorola;
import app.fernando.covidrastreo.actividades.ContenedorInstruccionesBTSamsung;
import app.fernando.covidrastreo.actividades.ContenedorInstruccionesBTXiaomi;
import app.fernando.covidrastreo.actividades.TerminosYCondicionesActivity;

import app.fernando.covidrastreo.actividades.TutorialPermisoUbicacionActivity;
import com.google.gson.Gson;

public class RegistroBluetooth extends AppCompatActivity {
    Button btnRegistrarBT;
    Button btnPermisoUbicacion;
    EditText etDireccionBT;
    Usuario usuario;
    Usuario usuarioCopia;
    CheckBox cbTerminosCondiciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_bluetooth);

        String json = getIntent().getStringExtra("usuario");
        usuario = new Gson().fromJson(json, Usuario.class);

        btnPermisoUbicacion = (Button) findViewById(R.id.btnPermisoUbicacion);
        int version = Build.VERSION.SDK_INT;
        if(version < 28)
            btnPermisoUbicacion.setVisibility(View.GONE);

        usuarioCopia = usuario;
        //mostrarInfoDispositivo();
        btnRegistrarBT = (Button) findViewById(R.id.btnRegistrarBT);
        etDireccionBT = (EditText) findViewById(R.id.etDireccionBT);
        cbTerminosCondiciones = (CheckBox) findViewById(R.id.cbTerminosCondiciones);

        btnRegistrarBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String direccionBT = etDireccionBT.getText().toString().toUpperCase();
                char puntos = ':';
                int contadorPuntos = 0;
                if(direccionBT.length() == 17){
                   for(int i = 0; i < 17; i++){
                        if(direccionBT.charAt(i) == puntos){
                            contadorPuntos++;
                        }
                    }
                }

                if(contadorPuntos == 5) {
                    if(cbTerminosCondiciones.isChecked()){
                        usuarioCopia.setIdUsuario(1);
                        usuarioCopia.setDireccionBT(direccionBT);
                        usuarioCopia.setSemInfUsuario(0);
                        Ajuste ajuste = new Ajuste(1, 1, 8, 0, 0, 0, 1);
                        Intent intent = new Intent(RegistroBluetooth.this, MainActivity.class);
                        String json = new Gson().toJson(usuarioCopia);
                        ConsultasSQL.registrarUsuarioSQL(usuarioCopia);
                        ConsultasSQL.registrarAjustes(ajuste);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("usuarioCopia", json);
                        startActivity(intent);
                    }else{
                        cbTerminosCondiciones.setError("Aceptar términos y condiciones");
                    }
                }else{
                    etDireccionBT.setError("Dirección Bluetooth incorrecta");
                }
            }
        });
    }

    public void permisosUbicacion(View view){
        Intent intent = new Intent(RegistroBluetooth.this, TutorialPermisoUbicacionActivity.class);
        startActivity(intent);
    }

    public void direccionBluetooth(View view){
        String marca = Build.MANUFACTURER.toUpperCase();

        if(marca.equals("HUAWEI")){

            Intent intent = new Intent(this, ContenedorInstruccionesBTHuawei.class);
            startActivity(intent);
        }
        else if(marca.equals("MOTOROLA")){
            Intent intent = new Intent(this, ContenedorInstruccionesBTMotorola.class);
            startActivity(intent);
        }
        else if(marca.equals("XIAOMI")){
            Intent intent = new Intent(this, ContenedorInstruccionesBTXiaomi.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(this, ContenedorInstruccionesBTSamsung.class);
            startActivity(intent);
        }
    }

    public void terminosYCondiciones(View view){
        Intent intent = new Intent(RegistroBluetooth.this, TerminosYCondicionesActivity.class);
        startActivity(intent);
    }
}
