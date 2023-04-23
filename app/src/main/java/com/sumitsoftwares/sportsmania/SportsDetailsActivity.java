package com.sumitsoftwares.sportsmania;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.Resource;

public class SportsDetailsActivity extends AppCompatActivity {
private String sportName;
private TextView txt_sportName,textView11;
private ImageView relativeLayout;
private CardView card_getdemo,card_applyNow;
private ImageView img_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports_details);
        initialization();
        getDemo();
        applyNow();
        gotoBack();
    }
    private void initialization()
    {
        txt_sportName= findViewById(R.id.txt_sportName);
        textView11 = findViewById(R.id.textView11);
        relativeLayout = findViewById(R.id.relativeLayout);
        card_getdemo = findViewById(R.id.card_getdemo);
        card_applyNow = findViewById(R.id.card_applyNow);
        img_back = findViewById(R.id.img_back);
        Resources res = getResources();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            sportName = extras.getString("sportName");
            txt_sportName.setText(sportName);
            if(sportName.equals("Cricket"))
            {
                Glide.with(getApplicationContext()).load("https://firebasestorage.googleapis.com/v0/b/sportsmania-6efc2.appspot.com/o/img_cricket.jpg?alt=media&token=70e287b4-10e6-418b-a48a-5c57cf47fedf").into(relativeLayout);
                textView11.setText(R.string.CricketDes);
            }
            else if(sportName.equals("Football"))
            {
                Glide.with(getApplicationContext()).load("https://firebasestorage.googleapis.com/v0/b/sportsmania-6efc2.appspot.com/o/img_football.jpg?alt=media&token=d38e7403-8c33-4ef3-ba83-a7c0a4669054").into(relativeLayout);
                textView11.setText(R.string.FootballDes);
            }
            else if(sportName.equals("Hockey"))
            {
                Glide.with(getApplicationContext()).load("https://firebasestorage.googleapis.com/v0/b/sportsmania-6efc2.appspot.com/o/img_football.jpg?alt=media&token=d38e7403-8c33-4ef3-ba83-a7c0a4669054").into(relativeLayout);
                textView11.setText(R.string.HockeyDes);

            }
            else if(sportName.equals("Basketball"))
            {
                Glide.with(getApplicationContext()).load("https://firebasestorage.googleapis.com/v0/b/sportsmania-6efc2.appspot.com/o/img_basket.jpg?alt=media&token=3c7ab95a-533c-4d0e-814f-091dae935950").into(relativeLayout);
                textView11.setText(R.string.BasketballDes);

            }
            else if(sportName.equals("Tennis"))
            {
                Glide.with(getApplicationContext()).load("https://firebasestorage.googleapis.com/v0/b/sportsmania-6efc2.appspot.com/o/img_tennis.jpg?alt=media&token=b15544c6-47ee-4f47-b3a5-b857291b460d").into(relativeLayout);
                textView11.setText(R.string.TennisDes);

            }
            else if(sportName.equals("Volley"))
            {
                Glide.with(getApplicationContext()).load("https://firebasestorage.googleapis.com/v0/b/sportsmania-6efc2.appspot.com/o/img_volley.jpg?alt=media&token=9f3fc0a5-c778-4905-b024-929ada7d45b7").into(relativeLayout);
                textView11.setText(R.string.VolleyDes);

            }else if(sportName.equals("Baseball"))
            {
                Glide.with(getApplicationContext()).load("https://firebasestorage.googleapis.com/v0/b/sportsmania-6efc2.appspot.com/o/img_baseball.jpg?alt=media&token=a2b53774-5d14-4fcd-9fca-aa4cff57ff75").into(relativeLayout);
                textView11.setText(R.string.BaseballDes);

            }else if(sportName.equals("Snooker"))
            {
                Glide.with(getApplicationContext()).load("https://firebasestorage.googleapis.com/v0/b/sportsmania-6efc2.appspot.com/o/img_snooker.jpg?alt=media&token=12f774b5-6f80-4958-bb68-5720e4f02bf9").into(relativeLayout);
                textView11.setText(R.string.SnookerDes);

            }
            else if(sportName.equals("TableTennis"))
            {
                Glide.with(getApplicationContext()).load("https://firebasestorage.googleapis.com/v0/b/sportsmania-6efc2.appspot.com/o/img_tabletennis.jpg?alt=media&token=ce13facc-399a-470b-85cf-488a2de8c6c2").into(relativeLayout);
                textView11.setText(R.string.TableTennisDes);

            }
            else if(sportName.equals("Darts"))
            {
                Glide.with(getApplicationContext()).load("https://firebasestorage.googleapis.com/v0/b/sportsmania-6efc2.appspot.com/o/img_darts.jpg?alt=media&token=e1b7eb26-8421-4b55-905f-89662496febe").into(relativeLayout);
                textView11.setText(R.string.DartsDes);

            }else if(sportName.equals("Badminton"))
            {
                Glide.with(getApplicationContext()).load("https://firebasestorage.googleapis.com/v0/b/sportsmania-6efc2.appspot.com/o/img_badminton.jpg?alt=media&token=1c41addc-9a3e-4faf-b2b4-79ecf6b067b3").into(relativeLayout);
                textView11.setText(R.string.BadmintonDes);

            }
            else if(sportName.equals("Handball"))
            {
                Glide.with(getApplicationContext()).load("https://firebasestorage.googleapis.com/v0/b/sportsmania-6efc2.appspot.com/o/img_handball.jpg?alt=media&token=51fcab02-fe40-4903-9df0-24606a43c80a").into(relativeLayout);
                textView11.setText(R.string.HandballDes);

            }

        }
    }

    private void getDemo()
    {
        card_getdemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),GetDemoActivity.class);
                startActivity(i);
            }
        });
    }

    private void applyNow()
    {
        card_applyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),ApplyNowActivity.class);
                startActivity(i);
            }
        });
    }

    private void gotoBack()
    {
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}