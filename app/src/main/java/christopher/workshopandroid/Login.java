package christopher.workshopandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    private static final String URL_LOGIN="http://watoncri.hol.es/login.php";
    private String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText texto_nombreUsuario = (EditText) findViewById(R.id.editText_nombreUsuario);
        final EditText texto_contrseña = (EditText) findViewById(R.id.editText_contraseña);

        Button boton_login = (Button) findViewById(R.id.button_entrar);
        Button boton_registrarse = (Button) findViewById(R.id.button_registrarse);

        boton_registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, Registro.class);
                startActivity(i);

            }
        });

        boton_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = texto_nombreUsuario.getText().toString();
                password = texto_contrseña.getText().toString();
                Login();
            }
        });
    }
    private void Login(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("success")) {
                    Toast.makeText(Login.this, "Has iniciado Sesión", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Login.this,MenuPrincipal.class);
                    startActivity(i);
                    finish();
                }else {
                    Toast.makeText(Login.this, "Problema al Iniciar Sesión", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError{
                Map<String,String> map = new HashMap<String,String>();
                map.put("username",username);
                map.put("password",password);

                return map;

            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}
