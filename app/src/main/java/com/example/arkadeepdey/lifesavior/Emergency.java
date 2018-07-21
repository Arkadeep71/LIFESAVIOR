package com.example.arkadeepdey.lifesavior;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Emergency extends AppCompatActivity {
    Button b, b2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        b = findViewById(R.id.button);
        b2 = findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Emergency.this, DonarFetch.class);
                startActivity(intent);
                //Toast.makeText(Home.this, "wait", Toast.LENGTH_LONG).show();

            }
        });
    }
}