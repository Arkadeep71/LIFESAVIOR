package com.example.arkadeepdey.lifesavior;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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

public class Register extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private Register.UserRegisterTask mAuthTask = null;

    // UI references.
    private EditText mNameView;
    private AutoCompleteTextView mEmailView;
    private EditText mNumberView;
    private EditText mPasswordView;
    private EditText mPasswordView2;
    private RadioGroup mRadiogroup ;
    private EditText mDateView;
    private EditText mAddressView;
    CheckBox checkBox ;
    private View mProgressView;
    private View mRegisterFormView;
    private Spinner mSpinner;
    String blood;//to store spinner item

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mNameView= findViewById(R.id.name);
        mEmailView = findViewById(R.id.email);
        mNumberView = findViewById(R.id.number);
        mDateView = findViewById(R.id.date);
        mPasswordView = findViewById(R.id.password);
        mPasswordView2 = findViewById(R.id.password2);
        mRadiogroup = findViewById(R.id.RadioGroup);
        mAddressView= findViewById(R.id.address);
        Button mRegisterButton = findViewById(R.id.register);
        checkBox = findViewById(R.id.checkBox);
        mRegisterFormView = findViewById(R.id.register_form);
        mProgressView = findViewById(R.id.progressBar);
        mSpinner = findViewById(R.id.spinner);

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
        String []bloodGroup =new String[] {"A+","A-","B+","B-","O+","O-","AB+","AB-"};

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
        Toast.makeText(Register.this, "No blood Group Selected", Toast.LENGTH_LONG).show();
    }
    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mNameView.setError(null);
        mEmailView.setError(null);
        mPasswordView.setError(null);
        mPasswordView2.setError(null);
        mAddressView.setError(null);
        mNumberView.setError(null);
        mDateView.setError(null);
        checkBox.setError(null);

        // Store values at the time of the login attempt.
        String name=mNameView.getText().toString();
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        String password2 = mPasswordView2.getText().toString();
        String address=mAddressView.getText().toString();
     //   long number=Long.parseLong(mNumberView.getText().toString());
        String num=mNumberView.getText().toString();
        String date=mDateView.getText().toString();
        String donar="No";
        String gender="others";
        String type="register";
        boolean cancel = false;
        View focusView = null;

        int Sid = mRadiogroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(Sid);
        if (Sid == -1) {
            Toast.makeText(Register.this, "No gender Selected", Toast.LENGTH_LONG).show();
            cancel = true;
        } else {
            gender=radioButton.getText().toString();
        }

        if (checkBox.isChecked()) {
            donar="Yes";
        }
        else{
            donar="No";
        }
        // Check for a valid password, if the user entered one.
        if (!password.equals(password2)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }
        if (TextUtils.isEmpty(password) || !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
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
            mAuthTask = new Register.UserRegisterTask(this);
            mAuthTask.execute(type,name,email,num,password,gender,date,blood,address,donar);
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@")&& email.contains(".com");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mRegisterFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mRegisterFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mRegisterFormView.setVisibility(show ? View.GONE : View.VISIBLE);
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
            mRegisterFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }








    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserRegisterTask extends AsyncTask<String, Void, String> {

        Context context;
        AlertDialog alertDialog;

        UserRegisterTask(Context ctx) {
            context=ctx;
        }

        @Override
        protected String doInBackground(String... params) {
            // TODO: attempt authentication against a network service.
            String type=params[0];
            String register_url="https://lifesavior.000webhostapp.com/register.php";

            try {
                String name=params[1];
                String email=params[2];
                String number=params[3];
                String password=params[4];
                String gender=params[5];
                String date=params[6];
                String blood=params[7];
                String Address=params[8];
                String donar=params[9];

                URL url=new URL(register_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data= URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"
                        +URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"
                        +URLEncoder.encode("number","UTF-8")+"="+URLEncoder.encode(number,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"
                        +URLEncoder.encode("gender","UTF-8")+"="+URLEncoder.encode(gender,"UTF-8")+"&"
                        +URLEncoder.encode("dob","UTF-8")+"="+URLEncoder.encode(date,"UTF-8")+"&"
                        +URLEncoder.encode("bloodgroup","UTF-8")+"="+URLEncoder.encode(blood,"UTF-8")+"&"
                        + URLEncoder.encode("Address","UTF-8")+"="+URLEncoder.encode(Address,"UTF-8")+"&"
                        +URLEncoder.encode("donar","UTF-8")+"="+URLEncoder.encode(donar,"UTF-8");
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
            alertDialog.setTitle("Registration Status");
        }
        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}

