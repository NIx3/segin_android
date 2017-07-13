package christopher.workshopandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Galeria extends AppCompatActivity {


    private ArrayList<imagenGaleria> listaImagenesGaleria;
    private RecyclerView recyclerView;
    private adaptadorGaleria adapter;

    imagenGaleria imagenGaleria;
    String titulo, texto, imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView1);
        listaImagenesGaleria = new ArrayList<>();
        adapter = new adaptadorGaleria(this, listaImagenesGaleria);

        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        stringBuscar("http://watoncri.hol.es/galeria.php");

    }

    public void stringBuscar(String url) {
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObj = jsonArray.getJSONObject(i);
                        titulo = jsonObj.getString("tipo_certificacion");
                        texto = "Tipo Certificación: " + jsonObj.getString("tipo_certificacion");
                        imagen = jsonObj.getString("imagen");
                        imagenGaleria = new imagenGaleria(titulo, texto, imagen);
                        listaImagenesGaleria.add(imagenGaleria);
                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}