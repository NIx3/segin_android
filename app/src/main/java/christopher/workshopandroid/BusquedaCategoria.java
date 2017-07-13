package christopher.workshopandroid;



        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.ListView;
        import android.widget.RadioButton;

        import com.android.volley.AuthFailureError;
        import com.android.volley.Request;
        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.StringRequest;
        import com.android.volley.toolbox.Volley;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class BusquedaCategoria extends Fragment {


    public BusquedaCategoria() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_busqueda_categoria, container, false);
    }

    ListView listaBusqueda;
    ArrayList<String> list = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    public void onViewCreated (View view, Bundle savedInstance){
        Button botonBuscar = (Button) view.findViewById(R.id.button_buscardatos);
        listaBusqueda = (ListView) view.findViewById(R.id.ListaBusqueda);
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,list);

        final RadioButton filtroSeguridad = (RadioButton) view.findViewById(R.id.radio_seguridad);
        final RadioButton filtroCalidad = (RadioButton) view.findViewById(R.id.radio_calidad);

        listaBusqueda.setAdapter(adapter);

        botonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                list.clear();

                if (filtroSeguridad.isChecked()){

                    stringBuscar("http://watoncri.hol.es/categoria.php","seguridad");

                }
                if (filtroCalidad.isChecked()){

                    stringBuscar("http://watoncri.hol.es/categoria.php","calidad");

                }
            }
        });
    }

    public void stringBuscar(String url, final String parametro){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObj = jsonArray.getJSONObject(i);
                        list.add(jsonObj.getString("nombre_certificacion"));
                    }
                    adapter.notifyDataSetChanged();
                }
                catch (JSONException e) {
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parameters = new HashMap<String,String>();
                parameters.put("tipo_certificacion",parametro);

                return parameters;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

}

