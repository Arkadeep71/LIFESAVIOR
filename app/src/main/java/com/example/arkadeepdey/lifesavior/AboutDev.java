package com.example.arkadeepdey.lifesavior;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class AboutDev extends AppCompatActivity implements View.OnClickListener {
    Button b,b2,b3,b4,b5;
    FloatingActionButton fb,fb2,fb3,fb4;
     RatingBar ratingBar;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dev);
         b2= findViewById(R.id.button5);
         b3= findViewById(R.id.button6);
         b4= findViewById(R.id.button7);
         b5= findViewById(R.id.button8);
        fb = findViewById(R.id.floatingActionButton);
        fb2 = findViewById(R.id.floatingActionButton2);
        fb3= findViewById(R.id.floatingActionButton3);
        fb4 = findViewById(R.id.floatingActionButton4);
        b=findViewById(R.id.button4);
        ratingBar=findViewById(R.id.ratingBar);



        b.setOnClickListener(this);
        b2.setOnClickListener( this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        fb.setOnClickListener(this);
        fb2.setOnClickListener(this);
        fb3.setOnClickListener(this);
        fb4.setOnClickListener(this);


    }
    @SuppressLint("RestrictedApi")
    public void onClick(View view) {


        if (view == fb ) {
            b3.setVisibility(View.INVISIBLE);
            b2.setVisibility(View.VISIBLE);
            b4.setVisibility(View.INVISIBLE);
            b5.setVisibility(View.INVISIBLE);
        }
        else if (view == fb2 ) {
            b3.setVisibility(View.VISIBLE);
            b2.setVisibility(View.INVISIBLE);
            b4.setVisibility(View.INVISIBLE);
            b5.setVisibility(View.INVISIBLE);
        }
        else if (view == fb3 ) {
            b3.setVisibility(View.INVISIBLE);
            b2.setVisibility(View.INVISIBLE);
            b4.setVisibility(View.VISIBLE);
            b5.setVisibility(View.INVISIBLE);
        }
        else if (view == fb4 ) {
            b3.setVisibility(View.INVISIBLE);
            b2.setVisibility(View.INVISIBLE);
            b4.setVisibility(View.INVISIBLE);
            b5.setVisibility(View.VISIBLE);
        }
        else if (view == b2 ) {
            Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com/arkadeep.dey.311"));

            startActivity(intent);
            Toast.makeText(AboutDev.this,"REDIRECT TO FACEBOOK PROFILE",Toast.LENGTH_SHORT).show();
        }
        else if (view == b3 ) {
            Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.linkedin.com/in/arkadeep-dey-38a634140"));

            startActivity(intent);
            Toast.makeText(AboutDev.this,"REDIRECT TO LINKEDIN PROFILE",Toast.LENGTH_SHORT).show();
        }
        else if (view == b4 ) {
 /**/          Intent Email = new Intent(Intent.ACTION_SEND);
            Email.setType("text/email");
            Email.putExtra(Intent.EXTRA_EMAIL, new String[] { ".arkadeep.dey.71@gmail.com" });
            Email.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
            Email.putExtra(Intent.EXTRA_TEXT, "Dear ...," + "");
            startActivity(Intent.createChooser(Email, "Send Feedback:"));
            Toast.makeText(AboutDev.this,"GIVE A FEEDBACK",Toast.LENGTH_SHORT).show();
        }
        else if (view == b5 ) {
            Intent intent =new Intent(Intent.ACTION_DIAL);
            String number="+918967927399" ;
            intent.setData(Uri.parse("tel: " +number));
            startActivity(intent);
        }
        else if(view==b){
            String rating=String.valueOf(ratingBar.getRating());
            Toast.makeText(AboutDev.this,"Rating: "+rating,Toast.LENGTH_LONG).show();
        }

}

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}