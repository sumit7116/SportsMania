package com.sumitsoftwares.sportsmania;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class applicationsAdapter extends FirebaseRecyclerAdapter<applicationsModel, applicationsAdapter.applicationViewHolder> {
    public applicationsAdapter(FirebaseRecyclerOptions<applicationsModel> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull applicationViewHolder holder, int position, @NonNull applicationsModel model) {
        holder.fname.setText("First Name- "+ model.getUserFirstName().toString());
        holder.lname.setText("Last Name- "+model.getUserLastName().toString());
        holder.gender.setText("Gender- "+model.getUserGender().toString());
        holder.faname.setText("Father Name- "+model.getUserFatherName().toString());
        holder.email.setText("Email- "+model.getUserEmail().toString());
        holder.mobno.setText("Mobile Number- "+model.getUserMobileNo().toString());
        holder.sportsAchievement.setText("Sport Achievement- "+model.getUserSportAchievement().toString());
        holder.appliedFor.setText("Applied For- "+model.getUserSportApply().toString());
        holder.tid.setText("Transaction ID- "+model.getUserTransactionID().toString());

    }


    @NonNull
    @Override
    public applicationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_applications,parent,false);
        return new applicationsAdapter.applicationViewHolder(view);
    }

    class applicationViewHolder extends RecyclerView.ViewHolder{
        TextView fname,lname,age,gender,faname, email, mobno, sportsAchievement, appliedFor, tid;
        public applicationViewHolder(@NonNull View itemView) {
            super(itemView);
            fname = itemView.findViewById(R.id.txt_fname);
            lname = itemView.findViewById(R.id.txt_lname);
            gender = itemView.findViewById(R.id.txt_gender);
            faname = itemView.findViewById(R.id.txt_fatherName);
            email = itemView.findViewById(R.id.txt_email);
            mobno = itemView.findViewById(R.id.txt_mobno);
            sportsAchievement = itemView.findViewById(R.id.txt_sportachievement);
            appliedFor = itemView.findViewById(R.id.txt_appliedfor);
            tid = itemView.findViewById(R.id.transaction_id);

        }
    }
}
