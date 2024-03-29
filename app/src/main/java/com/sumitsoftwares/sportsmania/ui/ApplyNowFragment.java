package com.sumitsoftwares.sportsmania.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sumitsoftwares.sportsmania.ApplyNowActivity;
import com.sumitsoftwares.sportsmania.R;


public class ApplyNowFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText et_fname,et_lname,et_email,et_mobno,et_fathername,et_address,et_sportsAchievement,et_trsactionid;
    private Spinner spinner_sports;
    private RadioGroup grp_gender;
    private Boolean anyError=false;
    private RadioButton gen_male,gen_female;
    private CardView card_applynow;
    private DatabaseReference databaseReference,dbref1;
    private FirebaseAuth mAuth;
    private long maxid=0;
    private View root;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         root = inflater.inflate(R.layout.fragment_apply_now, container, false);
            initialization();
        card_applynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbref1 = FirebaseDatabase.getInstance().getReference();
                int selectedId = grp_gender.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) root.findViewById(selectedId);

                if(validateInput()==false)
                {
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Applications");
                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            long count = dataSnapshot.getChildrenCount();
                            ref.child(String.valueOf(count+1)).child("UserFirstName").setValue(et_fname.getText().toString());
                            ref.child(String.valueOf(count+1)).child("UserLastName").setValue(et_lname.getText().toString());
                            ref.child(String.valueOf(count+1)).child("UserEmail").setValue(et_email.getText().toString());
                            ref.child(String.valueOf(count+1)).child("UserMobileNo").setValue(et_mobno.getText().toString());
                            ref.child(String.valueOf(count+1)).child("UserGender").setValue(radioButton.getText().toString());
                            ref.child(String.valueOf(count+1)).child("UserFatherName").setValue(et_fathername.getText().toString());
                            ref.child(String.valueOf(count+1)).child("UserAddress").setValue(et_address.getText().toString());
                            ref.child(String.valueOf(count+1)).child("UserSportApply").setValue(spinner_sports.getSelectedItem().toString());
                            ref.child(String.valueOf(count+1)).child("UserSportAchievement").setValue(et_sportsAchievement.getText().toString());
                            ref.child(String.valueOf(count+1)).child("UserTransactionID").setValue(et_trsactionid.getText().toString());

                            Toast.makeText(getContext(), "Application Submitted", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.e("Firebase", "Error: " + databaseError.getMessage());
                        }
                    });

                }

            }
        });
        return root;
    }

    private void initialization()
    {
        et_fname  = root.findViewById(R.id.et_fname);
        et_lname  = root.findViewById(R.id.et_lname);
        et_email  = root.findViewById(R.id.et_email);
        et_mobno  = root.findViewById(R.id.et_mobno);
        et_fathername  = root.findViewById(R.id.et_fathername);
        et_address  = root.findViewById(R.id.et_address);
        et_trsactionid  = root.findViewById(R.id.et_trsactionid);
        spinner_sports  = root.findViewById(R.id.spinner_sports);
        grp_gender  = root.findViewById(R.id.grp_gender);
        gen_male = root.findViewById(R.id.gen_male);
        et_sportsAchievement = root.findViewById(R.id.et_sportsAchievement);
        card_applynow = root.findViewById(R.id.card_signup);
        gen_female = root.findViewById(R.id.gen_female);
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child(mAuth.getCurrentUser().getUid().toString());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                et_fname.setText(snapshot.child("fname").getValue(String.class));
                et_lname.setText(snapshot.child("lname").getValue(String.class));
                et_email.setText(snapshot.child("mail").getValue(String.class));
                et_mobno.setText(snapshot.child("mobno").getValue(String.class));
                if(snapshot.child("gender").getValue(String.class).equals("Male"))
                {
                    gen_male.setChecked(true);
                }
                else
                {
                    gen_female.setChecked(true);
                }
                et_fname.setEnabled(false);
                et_lname.setEnabled(false);
                et_email.setEnabled(false);
                et_mobno.setEnabled(false);
                gen_male.setEnabled(false);
                gen_female.setEnabled(false);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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
        else if (et_email.getText().toString().isEmpty())
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
            Toast.makeText(getContext(), "Please select gender", Toast.LENGTH_SHORT).show();
            gen_male.requestFocus();
            anyError=true;
        }
        else if (et_fathername.getText().toString().isEmpty())
        {
            et_fathername.setError("Please Enter Father Name");
            et_fathername.requestFocus();
            anyError=true;
        }
        else if(!et_fathername.getText().toString().matches("^[A-Za-z]+$")){
            et_fathername.setError("Please enter correct father name");
            et_fathername.requestFocus();
            anyError=true;
        }
        else if (et_address.getText().toString().isEmpty())
        {
            et_address.setError("Please Enter Address");
            et_address.requestFocus();
            anyError=true;
        }
        else if (spinner_sports.getSelectedItem().toString().equals("Choose Sport"))
        {
            Toast.makeText(getContext(),"Please choose sport",Toast.LENGTH_SHORT).show();
            spinner_sports.requestFocus();
            anyError=true;
        }
        else if (et_sportsAchievement.getText().toString().isEmpty())
        {
            et_sportsAchievement.setError("Please Enter Sport Achievement");
            et_sportsAchievement.requestFocus();
            anyError=true;
        }
        else if (et_trsactionid.getText().toString().isEmpty())
        {
            et_trsactionid.setError("Please Enter Transaction ID");
            et_trsactionid.requestFocus();
            anyError=true;
        }
        else
        {
            anyError=false;
        }
        return anyError;
    }

}