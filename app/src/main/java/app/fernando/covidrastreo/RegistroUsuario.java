package app.fernando.covidrastreo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

import app.fernando.covidrastreo.Entidades.Usuario;

import app.fernando.covidrastreo.R;
import com.google.gson.Gson;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistroUsuario extends AppCompatActivity {

    private int nYearIni, nMonthIni, nDayIni, sYearIni, sMonthIni, sDayIni;
    static final int DATE_ID = 0;
    Calendar c = Calendar.getInstance();

    Button btnRegistrar;
    EditText etNombres;
    EditText etApellidos;
    EditText etFechaNacimiento;
    EditText etCorreo;
    EditText etContrasenia;
    RadioButton rbMasculino;
    RadioButton rbFemenino;

    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrousuario);

        sMonthIni = c.get(Calendar.MONTH);
        sDayIni = c.get(Calendar.DAY_OF_MONTH);
        sYearIni = c.get(Calendar.YEAR);

        etNombres = (EditText) findViewById(R.id.etNombres);
        etApellidos = (EditText) findViewById(R.id.etApellidos);
        etFechaNacimiento = (EditText) findViewById(R.id.etFechaNacimiento);
        etCorreo = (EditText) findViewById(R.id.etCorreo);
        etContrasenia = (EditText) findViewById(R.id.etContrasenia);
        rbMasculino = (RadioButton) findViewById(R.id.rbMasculino);
        rbFemenino = (RadioButton) findViewById(R.id.rbFemenino);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);

        etFechaNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialog(DATE_ID);
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validarCampos()){
                    Intent intent = new Intent(RegistroUsuario.this, RegistroBluetooth.class);
                    String json = new Gson().toJson(usuario);
                    intent.putExtra("usuario", json);
                    startActivity(intent);
                }
            }
        });
    }


    public boolean validarCampos(){
        boolean retorno = true;

        etNombres = (EditText) findViewById(R.id.etNombres);
        etApellidos = (EditText) findViewById(R.id.etApellidos);
        etFechaNacimiento = (EditText) findViewById(R.id.etFechaNacimiento);
        etCorreo = (EditText) findViewById(R.id.etCorreo);
        etContrasenia = (EditText) findViewById(R.id.etContrasenia);
        rbMasculino = (RadioButton) findViewById(R.id.rbMasculino);
        rbFemenino = (RadioButton) findViewById(R.id.rbFemenino);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);

        Calendar fecha = Calendar.getInstance();
        int anio = fecha.get(Calendar.YEAR);
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        usuario = new Usuario();
        usuario.setNombres(etNombres.getText().toString());
        usuario.setApellidos(etApellidos.getText().toString());
        usuario.setFechaNacimiento(etFechaNacimiento.getText().toString());
        usuario.setCorreo(etCorreo.getText().toString());
        usuario.setContrasenia(etContrasenia.getText().toString());

        String email = usuario.correo;
        Matcher matcher = pattern.matcher(email);

        if(usuario.nombres.equals("") || usuario.nombres.length() < 3){
            etNombres.setError("Campo vacío o incorrecta");
            retorno = false;
        }

        if(usuario.apellidos.equals("") || usuario.apellidos.length() < 3) {
            etApellidos.setError("Campo vacío o incorrecta");
            retorno = false;
        }

        if(anio - 18 < nYearIni) {
            etFechaNacimiento.setError("Fecha incorrecta");
            retorno = false;
        }

        if(usuario.correo.equals("") || matcher.find() == false) {
            etCorreo.setError("Campo vacío o incorrecto");
            retorno = false;
        }

        if(usuario.contrasenia.equals("") || usuario.contrasenia.length() < 5) {
            etContrasenia.setError("Campo vacío o incorrecto");
            retorno = false;
        }

        if (rbMasculino.isChecked()) {
            usuario.setSexo(1);
        } else if (rbFemenino.isChecked()) {
            usuario.setSexo(0);
        }

        return retorno;
    }

    private void colocarFecha(){
        etFechaNacimiento.setText(nDayIni + "/" + (nMonthIni + 1) + "/" + nYearIni + " ");
    }

    private DatePickerDialog.OnDateSetListener nDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    nYearIni = year;
                    nMonthIni = month;
                    nDayIni = dayOfMonth;
                    colocarFecha();
                }
            };


    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id){
            case DATE_ID:
                return new DatePickerDialog(this, nDateSetListener, sYearIni, sMonthIni, sDayIni);
        }
        return null;
    }
}
