package app.fernando.covidrastreo.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import app.fernando.covidrastreo.BaseDatos.ConsultasSQL;
import app.fernando.covidrastreo.R;

import java.util.Calendar;

public class ReporteActivity extends AppCompatActivity {

    public TextView tvPrimeraSemana;
    public TextView tvSegundaSemana;
    public TextView tvTerceraSemana;
    public TextView tvCuartaSemana;
    public Button btnPrueba;
    private final static String CHANNEL_ID = "NOTIFICACION";
    private final static int NOTIFICACION_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte);

        Calendar calendar = Calendar.getInstance();
        int semanaActual = calendar.get(Calendar.WEEK_OF_YEAR);

        int primeraSemana = ConsultasSQL.retornaNumeroinfectadosSemana(semanaActual);
        int segundaSemana = ConsultasSQL.retornaNumeroinfectadosSemana(semanaActual - 1);
        int terceraSemana = ConsultasSQL.retornaNumeroinfectadosSemana(semanaActual - 2);
        int cuartaSemana = ConsultasSQL.retornaNumeroinfectadosSemana(semanaActual - 3);

        tvPrimeraSemana = (TextView) findViewById(R.id.tvPrimeraSemana);
        tvPrimeraSemana.setText(retornaCadenaInfectados(primeraSemana));

        tvSegundaSemana = (TextView) findViewById(R.id.tvSegundaSemana);
        tvSegundaSemana.setText(retornaCadenaInfectados(segundaSemana));

        tvTerceraSemana = (TextView) findViewById(R.id.tvTerceraSemana);
        tvTerceraSemana.setText(retornaCadenaInfectados(terceraSemana));

        tvCuartaSemana = (TextView) findViewById(R.id.tvCuartaSemana);
        tvCuartaSemana.setText(retornaCadenaInfectados(cuartaSemana));
    }


    public String retornaCadenaInfectados(int numeroInfectadosSemana){
        String cadenaAMostrar = "";

        if(numeroInfectadosSemana != 1){
            cadenaAMostrar = "Tuviste " + numeroInfectadosSemana + " contactos infectados.";
        }else{
            cadenaAMostrar = "Tuviste " + numeroInfectadosSemana + " contacto infectado.";
        }
        return cadenaAMostrar;
    }
}