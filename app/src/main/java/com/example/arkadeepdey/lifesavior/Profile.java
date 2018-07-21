package com.example.arkadeepdey.lifesavior;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Profile extends AppCompatActivity {
    TextView b2,b3,b4,b5,b6,b7,b8,b9,b10;
    Button log,mod;
    ListView ls;
    String email;

    String address="https://lifesavior.000webhostapp.com/profile.php";

    String data[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        User user = new User(Profile.this);
        email =user.getName();
        ls= findViewById(R.id.listView1);
        b2= findViewById(R.id.txt_loggedName);
        b3= findViewById(R.id.textView5);
        b4= findViewById(R.id.textView9);
        b5= findViewById(R.id.textView3);
        b6= findViewById(R.id.textView11);
        b7= findViewById(R.id.textView12);
        b8= findViewById(R.id.textView16);
        b9= findViewById(R.id.textView18);
        log= findViewById(R.id.btnlogout);
        mod= findViewById(R.id.mod);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user=new User(Profile.this);
                Toast.makeText(Profile.this,"Successfully logout  :"+user.getName(),Toast.LENGTH_SHORT).show();
                user.removeuser();
                Intent intent =new Intent(Profile.this,Login.class);
                startActivity(intent);}
             });
        mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user=new User(Profile.this);
                Toast.makeText(Profile.this,"wait",Toast.LENGTH_SHORT).show();
                user.removeuser();
                Intent intent =new Intent(Profile.this,Home.class);
                startActivity(intent);}
        });


        if(!user.getName().contains("@")) {
            Toast.makeText(Profile.this,"Log in or Register first to accsess your profile :",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Profile.this, Login.class);
            startActivity(intent);
        }else {

  getJSON(address);

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

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("POST");
                    con.setDoOutput(true);
                    con.setDoInput(true);
                    OutputStream outputStream=con.getOutputStream();
                    BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                    String post_data= URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
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
        JSONArray jsonArray = new JSONArray(json);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            b2.setText(obj.getString("name"));
            b3.setText(obj.getString("email"));
            b4.setText(obj.getString("number"));
            b6.setText(obj.getString("gender"));
            b5.setText(obj.getString("dob"));

            b7.setText(obj.getString("bloodgroup"));

            b9.setText(obj.getString("Address"));
            b8.setText(obj.getString("donar"));



        }


    }




}

