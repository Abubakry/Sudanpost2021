package com.example.vollforandinsert03;

import androidx.appcompat.app.AppCompatActivity;

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

public class SecandActivity extends AppCompatActivity {

    EditText txtphone, txttitle, txtthecomp ;
    Button btn_insert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secand);


        txtphone = findViewById(R.id.editPhone);
        txttitle = findViewById(R.id.editTitle);
        txtthecomp = findViewById(R.id.editThecomp);
        btn_insert = findViewById(R.id.btn_insert);


        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insertData();

            }
        });


    }

    private void insertData() {



        final String comphone = txtphone.getText().toString().trim();
        final String comtitle = txttitle.getText().toString().trim();
        final String thecomp = txtthecomp.getText().toString().trim();

        if (comphone.isEmpty()) {
            Toast.makeText(this,"أدخل رقم الهاتف",Toast.LENGTH_SHORT).show();
            return;
        }
        else if (comtitle.isEmpty()) {
            Toast.makeText(this,"أدخل عنوان الشكوى",Toast.LENGTH_SHORT).show();
            return;
        }
        else if (thecomp.isEmpty()) {
            Toast.makeText(this,"أدخل نص الشكوى",Toast.LENGTH_SHORT).show();
            return;
        }


        else
        {

            Toast.makeText(SecandActivity.this,comphone,Toast.LENGTH_SHORT).show();
            Toast.makeText(SecandActivity.this,comtitle, Toast.LENGTH_SHORT).show();
            Toast.makeText(SecandActivity.this,thecomp, Toast.LENGTH_SHORT).show();



            StringRequest request =new StringRequest(Request.Method.POST, "https://postsudan.000webhostapp.com/insertvolley.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    if (response.equalsIgnoreCase("تم إضافة الشكوى")) {

                        Toast.makeText(SecandActivity.this,"تم إضافة الشكوى", Toast.LENGTH_SHORT).show();

                    }
                    else

                    {
                        Toast.makeText(SecandActivity.this,response , Toast.LENGTH_SHORT).show();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(SecandActivity.this,error.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            ){

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {

                    Map<String,String>  params = new HashMap<String, String>();

                    params.put("$comphone", comphone);
                    params.put("$comtitle", comtitle);
                    params.put("$thecomp", thecomp);

                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(SecandActivity.this);
            requestQueue.add(request);

        }

    }



    @Override
        public void onBackPressed() {
            super.onBackPressed();
            finish();
        }


    }
