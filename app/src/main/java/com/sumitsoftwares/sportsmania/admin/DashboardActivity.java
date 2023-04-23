package com.sumitsoftwares.sportsmania.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sumitsoftwares.sportsmania.R;
import com.sumitsoftwares.sportsmania.applicationsAdapter;
import com.sumitsoftwares.sportsmania.applicationsModel;

public class DashboardActivity extends AppCompatActivity {
private RecyclerView recyle_applications;
private applicationsAdapter adapter;
private DatabaseReference mbase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        recyle_applications = findViewById(R.id.recyle_applications);
        loadApplications();
    }

    private void loadApplications(){

        mbase = FirebaseDatabase.getInstance().getReference().child("Applications");
        recyle_applications.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<applicationsModel> options
                = new FirebaseRecyclerOptions.Builder<applicationsModel>()
                .setQuery(mbase, applicationsModel.class)
                .build();

        adapter = new applicationsAdapter(options);
        recyle_applications.setAdapter(adapter);
        adapter.startListening();
    }
}