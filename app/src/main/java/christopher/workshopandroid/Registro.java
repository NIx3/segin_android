package christopher.workshopandroid;

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

public class Registro extends AppCompatActivity {
    private static final String URL_REGISTRO="http;//watoncri.hol.es/login.php";
    private String username, email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Button button_registrar = (Button) findViewById(R.id.button_registro);

        final EditText nombreRegistro = (EditText) findViewById(R.id.editText_usuario);
        final EditText emailRegistro = (EditText) findViewById(R.id.editText_correo);
        final EditText contraseñaRegistro = (EditText) findViewById(R.id.editText_contraseñacorreo);

        button_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = nombreRegistro.getText().toString();
                email = emailRegistro.getText().toString();
                password = contraseñaRegistro.getText().toString();
            Registro();
            }
        });
    }
    private void Registro(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGISTRO, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                    Toast.makeText(Registro.this, "La cuenta está pulenta", Toast.LENGTH_SHORT).show();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put("username",username);
                map.put("email",email);
                map.put("password",password);

                return map;

            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}

