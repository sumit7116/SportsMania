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

public class liveScoreAdapter extends RecyclerView.Adapter<liveScoreAdapter.ViewHolder>{

    private LiveCourseData[] listdata;
    private Context context;

    public liveScoreAdapter(Context context,LiveCourseData[] listdata) {
        this.listdata = listdata;
        this.context = context;
    }

    @NonNull
    @Override
    public liveScoreAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.design_livesporlist, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull liveScoreAdapter.ViewHolder holder, int position) {
        final LiveCourseData myListData = listdata[position];
        holder.textView.setText(listdata[position].getSportName());
        String imageUrl = listdata[position].getSportImage();
        Glide.with(context).load(imageUrl).placeholder(R.drawable.img_placeholderlogo).into(holder.imageView);
        holder.rel_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myListData.getSportName().equals("Cricket"))
                {
                    String url = "https://www.cricbuzz.com/";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    context.startActivity(i);
                }
                else if(myListData.getSportName().equals("Football"))
                {
                    String url = "https://www.espn.in/football/scoreboard/_/league/all/date/20221119";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
                else if(myListData.getSportName().equals("Hockey"))
                {
                    String url = "https://www.diretta.it/hockey/";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
                else if(myListData.getSportName().equals("Basketball"))
                {
                    String url = "https://www.diretta.it/basket/";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }else if(myListData.getSportName().equals("Tennis"))
                {
                    String url = "https://www.diretta.it/tennis/";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }else if(myListData.getSportName().equals("Volley"))
                {
                    String url = "https://www.diretta.it/volley/";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
                else if(myListData.getSportName().equals("Baseball"))
                {
                    String url = "https://www.diretta.it/baseball/";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
                else if(myListData.getSportName().equals("Snooker"))
                {
                    String url = "https://www.diretta.it/snooker/";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
                else if(myListData.getSportName().equals("TableTennis"))
                {
                    String url = "https://www.livescore.in/table-tennis/";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
                else if(myListData.getSportName().equals("Darts"))
                {
                    String url = "https://www.flashscore.in/darts/";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
                else if(myListData.getSportName().equals("Badminton"))
                {
                    String url = "https://www.diretta.it/badminton/";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
                else if(myListData.getSportName().equals("Handball"))
                {
                    String url = "https://www.flashscore.in/handball/";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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
