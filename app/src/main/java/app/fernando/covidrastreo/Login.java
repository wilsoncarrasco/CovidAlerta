package app.fernando.covidrastreo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import app.fernando.covidrastreo.BaseDatos.ConsultasSQL;
import app.fernando.covidrastreo.Entidades.Usuario;

import app.fernando.covidrastreo.R;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.gson.Gson;


import org.json.JSONObject;

import java.security.MessageDigest;
import java.util.Arrays;

public class Login extends AppCompatActivity {

    private CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;

    LoginButton loginButton;
    Usuario usuario;
    TextView etCorreo;
    TextView etContrasenia;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        setContentView(R.layout.activity_login);

        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("public_profile"));
        callbackManager = CallbackManager.Factory.create();

        etCorreo = (TextView) findViewById(R.id.etCorreo);
        etContrasenia = (TextView) findViewById(R.id.etContrasenia);

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.v("LoginActivity", response.toString());

                                try {
                                    String nombres = object.getString("first_name");
                                    String apellidos = object.getString("last_name");

                                    usuario = new Usuario();
                                    usuario.setNombres(nombres);
                                    usuario.setApellidos(apellidos);

                                    Intent intent = new Intent(Login.this, RegistroBluetooth.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
                                    String json = new Gson().toJson(usuario);
                                    intent.putExtra("usuario", json);
                                    startActivity(intent);

                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                //parameters.putString("fields", "id,first_name,last_name,email,gender,birthday");
                parameters.putString("fields", "id,first_name,last_name");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "Inicio de sesión cancelado", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(getApplicationContext(), "Ha ocurrido un error, intente nuevamente", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void crearCuenta(View view) {
        Intent intent = new Intent(Login.this, RegistroUsuario.class);
        startActivity(intent);
    }

    public void iniciarSesion(View view){
        usuario = ConsultasSQL.retornaUsuario();
        String correo = etCorreo.getText().toString();
        String contrasenia = etContrasenia.getText().toString();

        if(correo.equals(usuario.correo) && contrasenia.equals(usuario.contrasenia)){
            Intent intent = new Intent(Login.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }else{
            etCorreo.setError("Correo incorrecto");
            etContrasenia.setError("Contraseña incorrecta");
        }
    }

    public void olvidasteContraseña(View view) {
        Toast.makeText(getApplicationContext(), "¿Olvidaste tu contraseña?", Toast.LENGTH_SHORT).show();
    }

}
