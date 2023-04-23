package com.sumitsoftwares.sportsmania;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CompleteProfileActivity extends AppCompatActivity {
    private CardView card_signup;
    private TextView txt_login;
    private EditText et_fname,et_lname,et_mail,et_mobno,et_city;
    private RadioGroup grp_gender;
    private RadioButton gen_male,gen_female;
    private Boolean anyError=false;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private RadioButton radioButton;
    private ProgressDialog progressDialog;
    private MaterialCardView card_signupwithgogle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_profile);
        initialization();
        getUserData();
        card_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateInput()==true)
                {

                }
                else
                {
                    createAccount();
                }
            }
        });
    }
    private void initialization()
    {
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        card_signup = findViewById(R.id.card_signup);
        txt_login = findViewById(R.id.txt_login);
        et_fname = findViewById(R.id.et_fname);
        et_lname = findViewById(R.id.et_lname);
        et_mail = findViewById(R.id.et_mail);
        et_mobno = findViewById(R.id.et_mobno);
        grp_gender = findViewById(R.id.grp_gender);
        gen_male = findViewById(R.id.gen_male);
        gen_female = findViewById(R.id.gen_female);
        et_city =findViewById(R.id.et_city);
    }

    private void getUserData()
    {
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        //firebaseUser.getDisplayName();
        et_fname.setText(firebaseUser.getDisplayName());
        et_mobno.setText(firebaseUser.getPhoneNumber());
        et_mail.setText(firebaseUser.getEmail());
    }

    private boolean validateInput()
    {
        if(et_fname.getText().toString().isEmpty())
        {
            et_fname.setError("Please Enter First Name");
            et_fname.requestFocus();
            anyError=true;
        }
        else if(!et_fname.getText().toString().matches("^[A-Za-z]+$")){
            et_fname.setError("Please enter correct first name");
            et_fname.requestFocus();
            anyError=true;
        }
        else if (et_lname.getText().toString().isEmpty())
        {
            et_lname.setError("Please Enter Last Name");
            et_lname.requestFocus();
            anyError=true;
        }
        else if(!et_lname.getText().toString().matches("^[A-Za-z]+$")){
            et_lname.setError("Please enter correct last name");
            et_lname.requestFocus();
            anyError=true;
        }
        else if (et_mail.getText().toString().isEmpty())
        {
            et_mail.setError("Please Enter Email");
            et_mail.requestFocus();
            anyError=true;
        }
        else if(!et_mail.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+")){
            et_mail.setError("Please enter correct email");
            et_mail.requestFocus();
            anyError=true;
        }
        else if(et_mobno.getText().toString().isEmpty())
        {
            et_mobno.setError("Please Enter Mobile Number");
            et_mobno.requestFocus();
            anyError=true;
        }
        else if(android.util.Patterns.PHONE.matcher(et_mobno.getText().toString()).matches()==false)
        {
            et_mobno.setError("Please Enter Correct Mobile Number");
            et_mobno.requestFocus();
            anyError=true;
        }
        else if (grp_gender.getCheckedRadioButtonId() == -1)
        {
            Toast.makeText(getApplicationContext(), "Please select gender", Toast.LENGTH_SHORT).show();
            gen_male.requestFocus();
            anyError=true;
        }
        else
        {
            anyError=false;
        }
        return anyError;
    }

    private void createAccount() {
        // [START create_user_with_email]
        progressDialog = new ProgressDialog(CompleteProfileActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Wait while we registering you");
        progressDialog.setIndeterminate(true);
        progressDialog.show();
                            int selectedId = grp_gender.getCheckedRadioButtonId();

                            // find the radiobutton by returned id
                            radioButton = (RadioButton) findViewById(selectedId);
                            // Sign in success, update UI with the signed-in user's information
                            databaseReference = firebaseDatabase.getReference().child(mAuth.getCurrentUser().getUid());
                            databaseReference.child("fname").setValue(et_fname.getText().toString());
                            databaseReference.child("lname").setValue(et_lname.getText().toString());
                            databaseReference.child("gender").setValue(radioButton.getText().toString());
                            databaseReference.child("mail").setValue(et_mail.getText().toString());
                            databaseReference.child("mobno").setValue(et_mobno.getText().toString());
                            databaseReference.child("city").setValue(et_city.getText().toString());
                            databaseReference.child("register").setValue("yes");
                            Toast.makeText(getApplicationContext(),"Registered Successfully.",Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                            SharedPreferences sharedPreferences = getSharedPreferences("SportsMania",MODE_PRIVATE);
                            SharedPreferences.Editor myEdit = sharedPreferences.edit();
                            myEdit.putString("isnew", "false");
                            myEdit.commit();
                            Intent i = new Intent(getApplicationContext(),HomeActivity.class);
                            startActivity(i);
                            finish();
                            //updateUI(user);

        // [END create_user_with_email]
    }

}