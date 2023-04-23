package com.sumitsoftwares.sportsmania;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class GetDemoActivity extends AppCompatActivity {
private RelativeLayout rel_prerecordedvideos,rel_requestdemovideos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_demo);
        initialization();
        gotoPrecordedVideo();
        gotoLiveVideo();
    }

    private void initialization()
    {
        rel_prerecordedvideos = findViewById(R.id.rel_prerecordedvideos);
        rel_requestdemovideos = findViewById(R.id.rel_requestdemovideos);

    }

    private void gotoPrecordedVideo()
    {
        rel_prerecordedvideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com"));
                startActivity(browserIntent);
            }
        });
    }

    private void gotoLiveVideo()
    {
        rel_prerecordedvideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://zoom.us/"));
                startActivity(browserIntent);
            }
        });
    }
}