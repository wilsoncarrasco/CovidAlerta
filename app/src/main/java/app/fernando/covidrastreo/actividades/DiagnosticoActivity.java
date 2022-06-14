package app.fernando.covidrastreo.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import app.fernando.covidrastreo.BaseDatos.ConsultasSQL;
import app.fernando.covidrastreo.Entidades.Ajuste;
import app.fernando.covidrastreo.Entidades.Usuario;

import app.fernando.covidrastreo.R;

import java.util.Calendar;

public class DiagnosticoActivity extends AppCompatActivity {

    Switch aSwitchCP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnostico);

        aSwitchCP = (Switch) findViewById(R.id.switchCovidPositivo);
        aSwitchCP.setEnabled(!ConsultasSQL.retornaEstadoBotonCovid());
        aSwitchCP.setChecked(ConsultasSQL.retornaEstadoBotonCovid());

        if(aSwitchCP.isChecked())
            aSwitchCP.setEnabled(!ConsultasSQL.retornaEstadoBotonCovid());
        else
            aSwitchCP.setEnabled(!ConsultasSQL.retornaEstadoBotonCovid());
    }

    public void llamada(View view){
        Toast.makeText(getApplicationContext(), "Llamando", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:117"));
        if(intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
    }

    public void covidPositivo(View view){
        if(view.getId()==R.id.switchCovidPositivo){
            if(aSwitchCP.isChecked()){
                Calendar calendar = Calendar.getInstance();
                Usuario usuario = new Usuario();
                usuario.setEstadoInfeccion(1);

                usuario.setSemInfUsuario(calendar.get(Calendar.WEEK_OF_YEAR));
                ConsultasSQL.actualizarUsuarioEstadoPositivoCovid(usuario);

                aSwitchCP.setEnabled(ConsultasSQL.retornaEstadoBotonCovid());

                usuario = ConsultasSQL.retornaUsuario();
                Ajuste ajuste = new Ajuste();
                ajuste.setBotonCovid(usuario.estadoInfeccion);
                ajuste.setSemanaBotonCovid(Calendar.getInstance().get(Calendar.WEEK_OF_YEAR));
                ajuste.setIdUsuario(usuario.idUsuario);

                ConsultasSQL.actualizarEstadoBotonCovid(ajuste);
                ConsultasSQL.actualizarUsuarioContactoEstadoPositivoCovid(usuario);
            }else {
                ConsultasSQL.actualizarEscaneoAjustes(false);
                Toast.makeText(getApplicationContext(), "Desactivado", Toast.LENGTH_SHORT).show();
            }
        }

    }

}