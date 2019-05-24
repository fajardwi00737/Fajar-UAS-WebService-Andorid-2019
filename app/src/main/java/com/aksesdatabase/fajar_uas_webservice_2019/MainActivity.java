package com.aksesdatabase.fajar_uas_webservice_2019;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private TextView textHasilJson;
    private TextView textHasilkota;
    private RequestQueue mQueue;
    String url = "http://papaside.com/data.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mQueue = Volley.newRequestQueue(this);
        textHasilJson = findViewById(R.id.textJSON);
        textHasilkota = findViewById(R.id.textkota);
        Button tombolJson = (Button) findViewById(R.id.btnJSON);

        tombolJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uraiJSON();
            }
        });
    }
    private void uraiJSON  (){

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {


                    for (int i = 0; i < response.length(); i++) {
                        JSONObject mahasantri = response.getJSONObject(i);

                        String kota = mahasantri.getString("Kota");
                        String siang = mahasantri.getString("siang");
                        String malam = mahasantri.getString("malam");
                        String diniHari = mahasantri.getString("dini_hari");
                        String suhu = mahasantri.getString("suhu");
                        String kelembapan = mahasantri.getString("Kelembapan");


                        textHasilJson.append("Nama Kota : "+kota+"\nSiang : "+ siang + "\nMalam : "+ malam + "\nDini Hari : "+ diniHari + "\nSuhu : "+ suhu+"\nKelembapan : "+kelembapan+"\n\n");
                    }
                } catch (JSONException e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(getApplicationContext(), "Error oyy", Toast.LENGTH_SHORT).show();
            }
        });

        mQueue.add(request);
    }
}
