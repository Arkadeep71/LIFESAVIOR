package com.example.arkadeepdey.lifesavior;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Request extends AppCompatActivity {
    ListView ls;
    String address="https://lifesavior.000webhostapp.com/request.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        User user=new User(this);
        if(!user.getName().contains("@")) {
            Toast.makeText(Request.this,"Log in or Register first to accsess this :",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Request.this, Home.class);
            startActivity(intent);
        }else {
            ls = findViewById(R.id.listView1);
            getJSON(address);
            Button add = findViewById(R.id.button9);

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent =new Intent(Request.this,AddRequest.class);
                    startActivity(intent);
                    Toast.makeText(Request.this,"wait",Toast.LENGTH_LONG).show();

                }
            });
        }
}
    private void getJSON(final String urlWebService) {

        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                try {
                    loadIntoListView(s);
                    if(!s.contains("@")){
                        Toast.makeText(Request.this,"Sorry, No data Found!!!",Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }
    private void loadIntoListView(String json) throws JSONException {
        JSONArray jArray = new JSONArray(json);
        String[] profile = new String[jArray.length()];
        StringBuilder sb =new StringBuilder(jArray.length());
        for (int i = 0; i < jArray.length(); i++) {
            JSONObject obj = jArray.getJSONObject(i);
            // heroes[i] = obj.getString("name");
            sb.setLength(0);
            sb.append("Request id:").append(obj.getString("id")).append("\nName:").append(obj.getString("name")).append("\nRequired Blood gr.:--->   ").append(obj.getString("blood")).append("\nRegistration id:").append(obj.getString("regid")).append("\nMobile No:").append(obj.getString("number")).append("\nGender:").append(obj.getString("gender")).append("\nhospital:").append(obj.getString("hospital")).append("\nAddress:").append(obj.getString("address")).append("\nDate of request:").append(obj.getString("date")).append("\n");
            profile[i] =sb.toString();
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, profile);
        ls.setAdapter(arrayAdapter);

    }

}
