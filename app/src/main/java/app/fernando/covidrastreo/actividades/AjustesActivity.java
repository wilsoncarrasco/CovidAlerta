package app.fernando.covidrastreo.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import app.fernando.covidrastreo.BaseDatos.ConsultasSQL;
import app.fernando.covidrastreo.Entidades.Ajuste;
import app.fernando.covidrastreo.Login;

import app.fernando.covidrastreo.R;
import com.facebook.login.LoginManager;

import java.util.Calendar;

public class AjustesActivity extends AppCompatActivity {
    Switch aSwitchE;
    TextView tvHora;
    EditText etHora;
    int mHora, mMin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

        aSwitchE = (Switch) findViewById(R.id.switchEscanear);
        tvHora = (TextView) findViewById(R.id.tvHora);
        etHora = (EditText) findViewById(R.id.etHora);

        Ajuste ajuste = ConsultasSQL.consultarAjuste();
        definirHora(ajuste.horaActualizacion, ajuste.minActualizacion);

        aSwitchE.setChecked(ConsultasSQL.escaneoAutomatico());
        etHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                mHora = c.get(Calendar.HOUR_OF_DAY);
                mMin = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(AjustesActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        definirHora(hourOfDay, minute);
                        Ajuste ajuste = new Ajuste();
                        ajuste.setHoraActualizacion(hourOfDay);
                        ajuste.setMinActualizacion(minute);
                        ajuste.setIdUsuario(1);
                        ConsultasSQL.actualizarHoraAjustes(ajuste);
                    }
                },mHora, mMin, false);
                timePickerDialog.show();
            }
        });
    }

    public void escanearManual(View view){
        if(view.getId()==R.id.switchEscanear){
            if(aSwitchE.isChecked()){
                ConsultasSQL.actualizarEscaneoAjustes(true);
                Toast.makeText(getApplicationContext(), "Activado", Toast.LENGTH_SHORT).show();
            }else {
                ConsultasSQL.actualizarEscaneoAjustes(false);
                Toast.makeText(getApplicationContext(), "Desactivado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void definirHora(int hourOfDay, int minute){
        String minuto ="";
        String hora = "";
        String valor = "";
        if(minute < 10)
            minuto = "0" + minute ;
        else
            minuto = minute + "";
        if(hourOfDay >= 0 && hourOfDay < 12){
            if(hourOfDay == 0){
                hora = "12";
            }else {
                hora = hourOfDay + "";
            }
            valor = "A.M.";
        }else{
            if(hourOfDay == 12){
                hora = "12";
            }else{
                hora = hourOfDay - 12 + "";
            }
            valor = "P.M.";
        }

        etHora.setText(hora + ":" + minuto + " " + valor);
    }

    public void cerrarSesion(View view){
        LoginManager.getInstance().logOut();
        Intent intent = new Intent(AjustesActivity.this, Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}