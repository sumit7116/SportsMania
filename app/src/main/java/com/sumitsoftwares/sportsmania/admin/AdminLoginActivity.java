package com.sumitsoftwares.sportsmania.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sumitsoftwares.sportsmania.R;

public class AdminLoginActivity extends AppCompatActivity {
private EditText  et_email, et_pass;
private CardView card_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        initialization();
        loginUser();
    }

    private void initialization(){
        et_email = findViewById(R.id.et_email);
        et_pass = findViewById(R.id.et_pass);
        card_login = findViewById(R.id.card_login);
    }

    private void loginUser()
    {
        card_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_email.getText().toString().isEmpty()){
                    et_email.setError("Please enter email");
                }else if(et_pass.getText().toString().isEmpty()){
                    et_pass.setError("Please enter password");
                }else if(et_email.getText().toString().equals("admin@sportsmania.com")&& et_pass.getText().toString().equals("Admin@1234")){
                    Intent i = new Intent(getApplicationContext(), DashboardActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(AdminLoginActivity.this, "Enter valid details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}