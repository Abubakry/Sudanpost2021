package com.example.vollforandinsert03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ListActivity extends AppCompatActivity {




    RequestQueue queue;
    TextView tv, txtphone, phonenum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        queue = Volley.newRequestQueue(this);



        tv =findViewById(R.id.text);
        //  txtphone = findViewById(R.id.txtphone);



        //     String url ="http://starlord.hackerearth.com/studio";
        final String url ="https://postsudan.000webhostapp.com/listsana2.php";


        JsonArrayRequest jor = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {


            @Override
            public void onResponse(JSONArray jsonArray) {

                tv.setText("");

                for (int i = 0 ; i < jsonArray.length(); i++) {

                    try {

                        JSONObject jb= jsonArray.getJSONObject(i);
                        String comphone =jb.getString("comphone");
                        String comtitle =jb.getString("comtitle");
                        String thecomp =jb.getString("thecomp");


                        tv.append(comphone+"\n"+comtitle+"\n"+thecomp+" "+"\n"+"\n");


                        final TextView mTextView = (TextView) findViewById(R.id.txtphone);
                        mTextView.setText(comphone);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError VolleyError) {

                tv.setText(VolleyError.getMessage());
            }
        });

        queue.add(jor);

    }

}

