package com.sumitsoftwares.sportsmania;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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

import org.w3c.dom.Text;

public class RegisterActivity extends AppCompatActivity {
private CardView card_signup;
private TextView txt_login;
private EditText et_fname,et_lname,et_mail,et_mobno,et_pass;
private RadioGroup grp_gender;
private RadioButton gen_male,gen_female;
private Boolean anyError=false;
private FirebaseAuth mAuth;
private  FirebaseUser currentUser;
private FirebaseDatabase firebaseDatabase;
private DatabaseReference databaseReference;
private RadioButton radioButton;
private ProgressDialog progressDialog;
private SharedPreferences sharedPreferences;
private MaterialCardView card_signupwithgogle;
private GoogleSignInClient googleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initialization();
        processRequest();
        loginwithgoogle();
        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //validateInput();
                Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
        card_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //validateInput();
                if(validateInput()==true)
                {

                }
                else
                {
                    createAccount(et_mail.getText().toString(),et_pass.getText().toString());
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
         currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent i = new Intent(getApplicationContext(),HomeActivity.class);
            startActivity(i);
            finish();
        }
        else
        {
            //Toast.makeText(getApplicationContext(),"Nulled",Toast.LENGTH_SHORT).show();
        }

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
        et_pass = findViewById(R.id.et_pass);
        grp_gender = findViewById(R.id.grp_gender);
        gen_male = findViewById(R.id.gen_male);
        gen_female = findViewById(R.id.gen_female);
        card_signupwithgogle = findViewById(R.id.card_signupwithgogle);
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
                                Intent intent= new Intent(RegisterActivity.this,CompleteProfileActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else
                            {
                                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                                myEdit.putString("isnew","false");
                                myEdit.commit();
                                Intent intent= new Intent(RegisterActivity.this,HomeActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            databaseReference = firebaseDatabase.getReference().child(mAuth.getCurrentUser().getUid());
                            databaseReference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    Toast.makeText(RegisterActivity.this, "", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                            //startActivity(intent);
                            //finish();

                            Toast.makeText(RegisterActivity.this, "sucesss", Toast.LENGTH_SHORT).show();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("DEBUG", "signInWithCredential:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "failed", Toast.LENGTH_SHORT).show();
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
        card_signupwithgogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIN();
            }
        });
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

    private void createAccount(String email, String password) {
        // [START create_user_with_email]
        progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Wait while we registering you");
        progressDialog.setIndeterminate(true);
        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
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
                            databaseReference.child("register").setValue("yes");
                            Toast.makeText(getApplicationContext(),"Registered Successfully.",Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                            Intent i = new Intent(getApplicationContext(),HomeActivity.class);
                            startActivity(i);
                            finish();
                            //updateUI(user);
                        } else {
                            progressDialog.dismiss();
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(),"Failed to Register",Toast.LENGTH_LONG).show();
                        }
                    }
                });
        // [END create_user_with_email]
    }

}