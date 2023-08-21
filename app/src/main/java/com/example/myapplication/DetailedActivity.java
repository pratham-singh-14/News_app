package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailedActivity extends AppCompatActivity {
    private TextView tvTitle,tvSource,tvTime,tvDesc;
    private WebView webView;
    private ImageView image1;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        tvTitle = findViewById(R.id.tvId);
        tvSource= findViewById(R.id.tvSource);
        tvDesc = findViewById(R.id.tvDesc);
        tvTime = findViewById(R.id.tvDate);
        webView= findViewById(R.id.webView);
        image1=findViewById(R.id.image12);
        progressBar=findViewById(R.id.webViewloader);
        progressBar.setVisibility(View.VISIBLE);
        Intent intent= getIntent();
      String t=  intent.getStringExtra("title");
   String u=     intent.getStringExtra("url");
     String desc=   intent.getStringExtra("desc");
        String time=intent.getStringExtra("time");
       String source= intent.getStringExtra("source");
      String image=  intent.getStringExtra("image");
        tvTitle.setText(t);
        tvTime.setText(time);
        tvDesc.setText(desc);
        tvSource.setText(source);
        Picasso.with(this).load(image).into(image1);

        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(u);
        if(webView.isShown())
        {
            progressBar.setVisibility(View.INVISIBLE);
        }

    }
}