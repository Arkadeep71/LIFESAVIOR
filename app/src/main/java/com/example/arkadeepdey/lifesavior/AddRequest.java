package com.example.arkadeepdey.lifesavior;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

public class AddRequest extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private UserRequestTask mAuthTask = null;
    private EditText mNameView;
    private AutoCompleteTextView mregidView;
    private EditText mNumberView;
    private EditText mHospitalView;
    private RadioGroup mRadiogroup ;
    private EditText mDateView;
    private EditText mAddressView;
    private View mProgressView;
    private View mRequestFormView;
    private Spinner mSpinner;
    String blood;//to store spinner item
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_request);
        mNameView= findViewById(R.id.name);
        mregidView = findViewById(R.id.regid);
        mNumberView = findViewById(R.id.number);
        mDateView = findViewById(R.id.date);
        mHospitalView = findViewById(R.id.hospital);
        mRadiogroup = findViewById(R.id.RadioGroup);
        mAddressView= findViewById(R.id.address);
        Button mAddButton = findViewById(R.id.add);
        mRequestFormView = findViewById(R.id.request_form);
        mProgressView = findViewById(R.id.progressBar);
        mSpinner = findViewById(R.id.spinner);
        mDateView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
        mRequestFormView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });String []bloodGroup =new String[] {"A+","A-","B+","B-","O+","O-","AB+","AB-"};

        mSpinner.setOnItemSelectedListener(this);
        ArrayAdapter aa= new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,bloodGroup);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(aa);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        blood=adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(AddRequest.this, "No blood Group Selected", Toast.LENGTH_LONG).show();

    }

    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mNameView.setError(null);
        mregidView.setError(null);
        mHospitalView.setError(null);
        mAddressView.setError(null);
        mNumberView.setError(null);
        mDateView.setError(null);


        // Store values at the time of the login attempt.
        String name=mNameView.getText().toString();
        String regid = mregidView.getText().toString();
        String hospital = mHospitalView.getText().toString();
        String address=mAddressView.getText().toString();
        //   long number=Long.parseLong(mNumberView.getText().toString());
        String num=mNumberView.getText().toString();
        String date=mDateView.getText().toString();
        String gender="others";
        String type="register";
        boolean cancel = false;
        View focusView = null;

        int Sid = mRadiogroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(Sid);
        if (Sid == -1) {
            Toast.makeText(AddRequest.this, "No gender Selected", Toast.LENGTH_LONG).show();
            cancel = true;
        } else {
            gender=radioButton.getText().toString();
        }


        // Check for a valid password, if the user entered one.

        if (TextUtils.isEmpty(hospital) ) {
            mHospitalView.setError(getString(R.string.error_invalid_password));
            focusView = mHospitalView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(regid)) {
            mregidView.setError(getString(R.string.error_field_required));
            focusView = mregidView;
            cancel = true;
        }
        //Check for Name
        if (TextUtils.isEmpty(name)) {
            mNameView.setError(getString(R.string.error_field_required));
            focusView = mNameView;
            cancel = true;
        }
        //Check for Number
        if (TextUtils.isEmpty(num)&& num.length()==10) {
            mNumberView.setError(getString(R.string.error_field_required));
            focusView = mNumberView;
            cancel = true;
        }
        //Check for Date
        if (TextUtils.isEmpty(date)) {
            mDateView.setError(getString(R.string.error_field_required));
            focusView = mDateView;
            cancel = true;
        }
        //Check for Name
        if (TextUtils.isEmpty(address)) {
            mAddressView.setError(getString(R.string.error_field_required));
            focusView = mAddressView;
            cancel = true;
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            User user= new User(AddRequest.this);
            mAuthTask = new UserRequestTask(this);
            mAuthTask.execute(type,name,regid,num,hospital,gender,date,blood,address,user.getName());
        }
    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mRequestFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mRequestFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mRequestFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mRequestFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
    public class UserRequestTask extends AsyncTask<String, Void, String> {

        Context context;
        AlertDialog alertDialog;

        UserRequestTask(Context ctx) {
            context=ctx;
        }

        @Override
        protected String doInBackground(String... params) {
            // TODO: attempt authentication against a network service.
            String type=params[0];
            String register_url="https://lifesavior.000webhostapp.com/addrequest.php";
            try {
                String name=params[1];
                String regid=params[2];
                String number=params[3];
                String hospital=params[4];
                String gender=params[5];
                String date=params[6];
                String blood=params[7];
                String address=params[8];
                String user =params[9];

                URL url=new URL(register_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data= URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"
                        +URLEncoder.encode("regid","UTF-8")+"="+URLEncoder.encode(regid,"UTF-8")+"&"
                        +URLEncoder.encode("number","UTF-8")+"="+URLEncoder.encode(number,"UTF-8")+"&"
                        +URLEncoder.encode("hospital","UTF-8")+"="+URLEncoder.encode(hospital,"UTF-8")+"&"
                        +URLEncoder.encode("gender","UTF-8")+"="+URLEncoder.encode(gender,"UTF-8")+"&"
                        +URLEncoder.encode("date","UTF-8")+"="+URLEncoder.encode(date,"UTF-8")+"&"
                        +URLEncoder.encode("blood","UTF-8")+"="+URLEncoder.encode(blood,"UTF-8")+"&"
                        + URLEncoder.encode("address","UTF-8")+"="+URLEncoder.encode(address,"UTF-8")+"&"
                        + URLEncoder.encode("user","UTF-8")+"="+URLEncoder.encode(user,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line=bufferedReader.readLine()) != null){
                    result +=line;}
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;


                // Simulate network access.
                // Thread.sleep(2000);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }



            // TODO: register the new account here.
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            mAuthTask = null;
            showProgress(false);

            alertDialog.setMessage(result);
            alertDialog.show();
        }
        @Override
        protected  void  onPreExecute(){
            alertDialog=new AlertDialog.Builder(context).create();
            alertDialog.setTitle("Request Status");
        }
        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}
