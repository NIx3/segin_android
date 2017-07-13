package christopher.workshopandroid;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class detalleGaleria extends AppCompatActivity {

    ImageView imagen_detalle;

    TextView texto_detalle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_galeria);

        imagen_detalle = (ImageView) findViewById(R.id.imagen_iso);
        texto_detalle= (TextView) findViewById(R.id.texto_imagen);

        Bundle bundle = getIntent().getExtras();
        final String palabra_clave = bundle.getString("palabra clave");
        stringBuscar("http://watoncri.hol.es/detalleImagen.php",palabra_clave);

    }

    public void stringBuscar(String url, final String parametro){
        Log.e("pass","detallegaleria metodo");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObj = jsonArray.getJSONObject(i);
                        //jsonObj.getString("nombre_certificacion");
                        texto_detalle.setText(jsonObj.getString("nombre_certificacion"));
                        Glide.with(detalleGaleria.this).load(jsonObj.getString("imagen")).into(imagen_detalle);
                        Log.e("pass","detallegaleria");

                    }

                    Log.e("error en json","");
                }

                catch (JSONException e) {

                    Log.e("error en json",e.toString());
                }
                Log.e("error en json","metidi");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("error en json",error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parameters = new HashMap<String,String>();
                parameters.put("tipo_certificacion",parametro);

                return parameters;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
