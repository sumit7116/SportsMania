package com.sumitsoftwares.sportsmania;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class sportListAdapter extends RecyclerView.Adapter<sportListAdapter.ViewHolder>{

    private LiveSportData[] listdata;
    private Context context;
    public sportListAdapter(Context context, LiveSportData[] listdata) {
        this.context = context;
        this.listdata = listdata;
    }

    @NonNull
    @Override
    public sportListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.design_sporlist, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull sportListAdapter.ViewHolder holder, int position) {
        final LiveSportData myListData = listdata[position];
        holder.textView.setText(listdata[position].getSportName());
        String imageUrl = listdata[position].getSportImage();
        Glide.with(context).load(imageUrl).placeholder(R.drawable.img_placeholderlogo).into(holder.imageView);
        //holder.imageView.setImageResource(listdata[position].getSportImage());
        holder.rel_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myListData.getSportName().equals("Cricket"))
                {
                    Intent i = new Intent(context,SportsDetailsActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("sportName","Cricket");
                    context.startActivity(i);
                }
                else if(myListData.getSportName().equals("Football"))
                {
                    Intent i = new Intent(context,SportsDetailsActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("sportName","Football");
                    context.startActivity(i);
                }
                else if(myListData.getSportName().equals("Hockey"))
                {
                    Intent i = new Intent(context,SportsDetailsActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("sportName","Hockey");
                    context.startActivity(i);
                }
                else if(myListData.getSportName().equals("Basketball"))
                {
                    Intent i = new Intent(context,SportsDetailsActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("sportName","Basketball");
                    context.startActivity(i);
                }else if(myListData.getSportName().equals("Tennis"))
                {
                    Intent i = new Intent(context,SportsDetailsActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("sportName","Tennis");
                    context.startActivity(i);
                }else if(myListData.getSportName().equals("Volley"))
                {
                    Intent i = new Intent(context,SportsDetailsActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("sportName","Volley");
                    context.startActivity(i);
                }
                else if(myListData.getSportName().equals("Baseball"))
                {
                    Intent i = new Intent(context,SportsDetailsActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("sportName","Baseball");
                    context.startActivity(i);
                }
                else if(myListData.getSportName().equals("Snooker"))
                {
                    Intent i = new Intent(context,SportsDetailsActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("sportName","Snooker");
                    context.startActivity(i);
                }
                else if(myListData.getSportName().equals("TableTennis"))
                {
                    Intent i = new Intent(context,SportsDetailsActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("sportName","TableTennis");
                    context.startActivity(i);
                }
                else if(myListData.getSportName().equals("Darts"))
                {
                    Intent i = new Intent(context,SportsDetailsActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("sportName","Darts");
                    context.startActivity(i);
                }
                else if(myListData.getSportName().equals("Badminton"))
                {
                    Intent i = new Intent(context,SportsDetailsActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("sportName","Badminton");
                    context.startActivity(i);
                }
                else if(myListData.getSportName().equals("Handball"))
                {
                    Intent i = new Intent(context,SportsDetailsActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("sportName","Handball");
                    context.startActivity(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public RelativeLayout rel_main;
        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.img_sport);
            this.textView = (TextView) itemView.findViewById(R.id.txt_sportname);
            this.rel_main = (RelativeLayout) itemView.findViewById(R.id.rel_main);
        }
    }
}
