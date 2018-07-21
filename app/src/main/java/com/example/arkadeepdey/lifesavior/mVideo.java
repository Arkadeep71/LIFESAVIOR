package com.example.arkadeepdey.lifesavior;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.Toast;

public class mVideo extends AppCompatActivity {
    WebView wb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvideo);
        ImageButton v1=findViewById(R.id.imageButton);
        ImageButton v2=findViewById(R.id.imageButton2);
        ImageButton v3=findViewById(R.id.imageButton3);
         wb=findViewById(R.id.mvideo);
         wb.setVisibility(View.GONE);
         wb.setWebChromeClient(new WebChromeClient());
         wb.getSettings().setPluginState(WebSettings.PluginState.ON);

        wb.getSettings().setPluginState(WebSettings.PluginState.ON_DEMAND);
         wb.getSettings().setJavaScriptEnabled(true);
        wb.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wb.setVisibility(View.VISIBLE);
                String obj="<object width=\"100%\" height=\"260\"><param name=\"movie\" value=\"https://www.youtube.com/embed/EHUzIva6HV4?enablejsapi=1&origin=example.com\"?version=3&amp;hl=pt_BR&amp;rel=0\"></param><param name=\"allowFullScreen\" value=\"true\"></param><param name=\"allowscriptaccess\" value=\"always\"></param><embed src=\"http://www.youtube.com/embed/iiLepwjBhZE?enablejsapi=1&origin=example.com\" ?version=3&amp;hl=pt_BR&amp;rel=0\" type=\"application/x-shockwave-flash\" width=\"480\" height=\"330\" allowscriptaccess=\"always\" allowfullscreen=\"true\" /></object>";
                //wb.loadDataWithBaseURL("https://youtu.be/EHUzIva6HV4","file:///android_asset/v4.html","text/html","UTF-8",null);
                //wb.loadUrl("file:///android_asset/v4.html");
                wb.loadData(obj,"text/html","UTF-8");

            }
        });
        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wb.setVisibility(View.VISIBLE);
                wb.loadUrl("file:///android_asset/v3.html");

            }
        });
        v3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wb.setVisibility(View.VISIBLE);
                wb.loadUrl("file:///android_asset/v2.html");

            }
        });
    }
}
