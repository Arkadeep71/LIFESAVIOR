package com.example.arkadeepdey.lifesavior;

import android.content.Intent;
import android.net.Uri;
import android.net.UrlQuerySanitizer;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
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

public class DonarFetch extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
Spinner mSpinner;
    ListView ls;
    String blood;

    String address="https://lifesavior.000webhostapp.com/donar.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donar_fetch);
        User user=new User(this);
        if(!user.getName().contains("@")) {
            Toast.makeText(DonarFetch.this,"Log in or Register first to accsess this :",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(DonarFetch.this, Home.class);
            startActivity(intent);
        }else {


            ls = findViewById(R.id.listView1);


            mSpinner = findViewById(R.id.spinner2);
            String[] bloodGroup = new String[]{"A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};

            mSpinner.setOnItemSelectedListener(this);
            ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, bloodGroup);
            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mSpinner.setAdapter(aa);
            // getJSON(address,blood);

        }




    }
    private void getJSON(final String urlWebService,String Blood) {

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
                        Toast.makeText(DonarFetch.this,"Sorry, No data Found!!!",Toast.LENGTH_SHORT).show();

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
                    con.setRequestMethod("POST");
                    con.setDoOutput(true);
                    con.setDoInput(true);
                    OutputStream outputStream=con.getOutputStream();
                    BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                    String post_data= URLEncoder.encode("blood","UTF-8")+"="+URLEncoder.encode(blood,"UTF-8");
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
        String[] profile = new String[jsonArray.length()];
        StringBuilder sb =new StringBuilder(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
           // heroes[i] = obj.getString("name");
            sb.setLength(0);
            sb.append("Name:").append(obj.getString("name")).append("\nEmail id:").append(obj.getString("email")).append("\nMobile No:").append(obj.getString("number")).append("\nGender:").append(obj.getString("gender")).append("\nAddress:").append(obj.getString("Address")).append("\n");
            profile[i] =sb.toString();
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, profile);
        ls.setAdapter(arrayAdapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        blood=adapterView.getItemAtPosition(i).toString();
        getJSON(address,blood);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

