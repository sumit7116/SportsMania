package com.sumitsoftwares.sportsmania;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class LiveSportSeeAllActivity extends AppCompatActivity {
private RecyclerView recyle_sports;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_sport_see_all);
        LiveCourseData[] liveCourseData = new LiveCourseData[]
                {
                        new LiveCourseData("Cricket","https://firebasestorage.googleapis.com/v0/b/sportsmania-6efc2.appspot.com/o/img_cricket.jpg?alt=media&token=70e287b4-10e6-418b-a48a-5c57cf47fedf"),
                        new LiveCourseData("Football","https://firebasestorage.googleapis.com/v0/b/sportsmania-6efc2.appspot.com/o/img_football.jpg?alt=media&token=d38e7403-8c33-4ef3-ba83-a7c0a4669054"),
                        new LiveCourseData("Hockey","https://firebasestorage.googleapis.com/v0/b/sportsmania-6efc2.appspot.com/o/img_football.jpg?alt=media&token=d38e7403-8c33-4ef3-ba83-a7c0a4669054"),
                        new LiveCourseData("Basketball","https://firebasestorage.googleapis.com/v0/b/sportsmania-6efc2.appspot.com/o/img_basket.jpg?alt=media&token=3c7ab95a-533c-4d0e-814f-091dae935950"),
                        new LiveCourseData("Tennis","https://firebasestorage.googleapis.com/v0/b/sportsmania-6efc2.appspot.com/o/img_tennis.jpg?alt=media&token=b15544c6-47ee-4f47-b3a5-b857291b460d"),
                        new LiveCourseData("Volley","https://firebasestorage.googleapis.com/v0/b/sportsmania-6efc2.appspot.com/o/img_volley.jpg?alt=media&token=9f3fc0a5-c778-4905-b024-929ada7d45b7"),
                        new LiveCourseData("Baseball","https://firebasestorage.googleapis.com/v0/b/sportsmania-6efc2.appspot.com/o/img_baseball.jpg?alt=media&token=a2b53774-5d14-4fcd-9fca-aa4cff57ff75"),
                        new LiveCourseData("Snooker","https://firebasestorage.googleapis.com/v0/b/sportsmania-6efc2.appspot.com/o/img_snooker.jpg?alt=media&token=12f774b5-6f80-4958-bb68-5720e4f02bf9"),
                        new LiveCourseData("TableTennis","https://firebasestorage.googleapis.com/v0/b/sportsmania-6efc2.appspot.com/o/img_tabletennis.jpg?alt=media&token=ce13facc-399a-470b-85cf-488a2de8c6c2"),
                        new LiveCourseData("Darts","https://firebasestorage.googleapis.com/v0/b/sportsmania-6efc2.appspot.com/o/img_darts.jpg?alt=media&token=e1b7eb26-8421-4b55-905f-89662496febe"),
                        new LiveCourseData("Badminton","https://firebasestorage.googleapis.com/v0/b/sportsmania-6efc2.appspot.com/o/img_badminton.jpg?alt=media&token=1c41addc-9a3e-4faf-b2b4-79ecf6b067b3"),
                        new LiveCourseData("Handball","https://firebasestorage.googleapis.com/v0/b/sportsmania-6efc2.appspot.com/o/img_handball.jpg?alt=media&token=51fcab02-fe40-4903-9df0-24606a43c80a"),
                };
        recyle_sports=findViewById(R.id.recyle_sports);
        liveScoreAdapter adapter = new liveScoreAdapter(getApplicationContext(),liveCourseData);
        recyle_sports.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyle_sports.setLayoutManager(mLayoutManager);
        //recyle_sports.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyle_sports.setAdapter(adapter);

    }
}