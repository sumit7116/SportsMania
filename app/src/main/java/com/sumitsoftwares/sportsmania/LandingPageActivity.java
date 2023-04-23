package com.sumitsoftwares.sportsmania;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sumitsoftwares.sportsmania.ui.GetDemoFragment;

public class LandingPageActivity extends AppCompatActivity {
    public CardView card_getdemo,card_login;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser currentUser;
    private LinearLayout linear_applynow,linear_ContactUs;
    private ImageView img_holeyball,img_img2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        intialization();
        gotologin();
        gotoDemo();
        gotoApply();
        contactUs();
    }

    private void intialization()

    {
        card_getdemo = findViewById(R.id.card_getdemo);
        card_login = findViewById(R.id.card_login);
        card_getdemo = findViewById(R.id.card_getdemo);
        linear_applynow = findViewById(R.id.linear_applynow);
        linear_ContactUs = findViewById(R.id.linear_ContactUs);
        img_holeyball = findViewById(R.id.img_holeyball);
        img_img2 = findViewById(R.id.img_img2);
        Glide.with(getApplicationContext()).load(R.drawable.img_holyball).into(img_holeyball);
        Glide.with(getApplicationContext()).load(R.drawable.team1).into(img_img2);

        firebaseAuth = FirebaseAuth.getInstance();

    }
    @Override
    protected void onStart() {
        super.onStart();
        currentUser = firebaseAuth.getCurrentUser();
        if(currentUser != null){
            SharedPreferences sh = getSharedPreferences("SportsMania", Context.MODE_PRIVATE);
            String s1 = sh.getString("isnew", "");
            //Toast.makeText(getApplicationContext(),""+s1,Toast.LENGTH_SHORT).show();
            if(s1.equals("true"))
            {
                Intent i = new Intent(getApplicationContext(),CompleteProfileActivity.class);
                startActivity(i);
                finish();
            }
            else
            {
                Intent i = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(i);
                finish();
            }
        }
        else
        {
            //Toast.makeText(getApplicationContext(),"Nulled",Toast.LENGTH_SHORT).show();
        }

    }
    private void gotologin()
    {
        card_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
            }
        });
    }

    private void gotoDemo()
    {
        card_getdemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),GetDemoActivity.class);
                startActivity(i);
            }
        });
    }

    private void gotoApply()
    {
        linear_applynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),ApplyNowActivity.class);
                startActivity(i);
            }
        });

    }

    private void contactUs()
    {
        linear_ContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = "+911234567890";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
            }
        });
    }
}