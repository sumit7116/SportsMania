package com.sumitsoftwares.sportsmania;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
private CardView card_login;
private TextView txt_signup;
private EditText et_email,et_pass;
private Boolean anyError=false;
private FirebaseAuth mAuth;
private MaterialCardView card_signinwithgoogle;
private GoogleSignInAccount googleSignInAccount;
private GoogleSignInClient googleSignInClient;
private FirebaseDatabase firebaseDatabase;
private DatabaseReference databaseReference;
private SharedPreferences sharedPreferences;
private  FirebaseUser currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       processRequest();
        initalization();
       gotoSignup();
       gotoLogin();
       loginwithgoogle();
    }
    private void initalization()
    {
        firebaseDatabase = FirebaseDatabase.getInstance();
        et_email = findViewById(R.id.et_email);
        et_pass = findViewById(R.id.et_pass);
        card_login = findViewById(R.id.card_login);
        txt_signup = findViewById(R.id.txt_signup);
        mAuth = FirebaseAuth.getInstance();
        card_signinwithgoogle = findViewById(R.id.card_signinwithgoogle);
        sharedPreferences = getSharedPreferences("SportsMania",MODE_PRIVATE);
    }

    private void signIN()
    {
        Intent signinIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signinIntent,101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 101){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try{
                GoogleSignInAccount account =task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            }catch (ApiException e)
            {
                Toast.makeText(this, "error getting", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential firebaseCredential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(firebaseCredential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("DEBUG", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            boolean isNewUser = task.getResult().getAdditionalUserInfo().isNewUser();
                            if(isNewUser==true)
                            {
                                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                                myEdit.putString("isnew","true");
                                myEdit.commit();
                                Intent intent= new Intent(LoginActivity.this,CompleteProfileActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else
                            {
                                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                                myEdit.putString("isnew","false");
                                myEdit.commit();
                                 Intent intent= new Intent(LoginActivity.this,HomeActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            databaseReference = firebaseDatabase.getReference().child(mAuth.getCurrentUser().getUid());
                            databaseReference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                   // Toast.makeText(LoginActivity.this, "", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                            //startActivity(intent);
                            //finish();

                            Toast.makeText(LoginActivity.this, "sucesss", Toast.LENGTH_SHORT).show();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("DEBUG", "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void processRequest()
    {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder((GoogleSignInOptions.DEFAULT_SIGN_IN))
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(getApplicationContext(),gso);
    }


    private void loginwithgoogle()
    {
        card_signinwithgoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIN();
            }
        });
    }


    private void gotoSignup()
    {
        txt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(i);
            }
        });
    }
    private void gotoLogin()
    {
        card_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateInput()==true)
                {

                }
                else
                {
                    loginAccount(et_email.getText().toString(),et_pass.getText().toString());
                }
            }
        });
    }

    private void loginAccount(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(
                                    @NonNull Task<AuthResult> task)
                            {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(),"Login successful!!",Toast.LENGTH_LONG).show();
                                    //mAuth.getCurrentUser();

//                                        Intent intent= new Intent(LoginActivity.this,HomeActivity.class);
//                                        startActivity(intent);
//                                        finish();

                                }

                                else {
                                    Toast.makeText(getApplicationContext(),"Login failed!!",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
    }

    private boolean validateInput()
    {

        if (et_email.getText().toString().isEmpty())
        {
            et_email.setError("Please Enter Email");
            et_email.requestFocus();
            anyError=true;
        }
        else if(!et_email.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+")){
            et_email.setError("Please enter correct email");
            et_email.requestFocus();
            anyError=true;
        }
        else if (et_pass.getText().toString().isEmpty())
        {
            et_pass.setError("Please Enter Password");
            et_pass.requestFocus();
            anyError=true;
        }
        else if(et_pass.getText().toString().length()<8)
        {
            et_pass.setError("Password cannot be less than 8");
            et_pass.requestFocus();
            anyError=true;
        }
        else
        {
            anyError=false;
        }
        return anyError;
    }




}