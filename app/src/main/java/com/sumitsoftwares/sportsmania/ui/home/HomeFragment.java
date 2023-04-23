package com.sumitsoftwares.sportsmania.ui.home;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.sumitsoftwares.sportsmania.ApplyNowActivity;
import com.sumitsoftwares.sportsmania.LiveSportSeeAllActivity;
import com.sumitsoftwares.sportsmania.R;
import com.sumitsoftwares.sportsmania.SportsDetailsActivity;
import com.sumitsoftwares.sportsmania.SportsListActivity;
import com.sumitsoftwares.sportsmania.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

    binding = FragmentHomeBinding.inflate(inflater, container, false);
    View root = binding.getRoot();
        ImageView img_cricketScore ;
        ImageView img_footballScore ;
        ImageView img_hockeyScore ;
        ImageView img_basketballScore ;
        ImageView img_tennisScore ;
        //final TextView textView = binding.textHome;
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        List<SlideModel> imageList = new ArrayList<>();
        imageList.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/sportsmania-6efc2.appspot.com/o/img_cricket.jpg?alt=media&token=70e287b4-10e6-418b-a48a-5c57cf47fedf","Sports Mania", ScaleTypes.FIT));
        imageList.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/sportsmania-6efc2.appspot.com/o/img_handball.jpg?alt=media&token=b7f13b42-2355-4c13-9797-6d5e15a7d390","Get Trained", ScaleTypes.FIT));
        imageList.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/sportsmania-6efc2.appspot.com/o/img_volley.jpg?alt=media&token=9f3fc0a5-c778-4905-b024-929ada7d45b7","Live Dream", ScaleTypes.FIT));
        ImageSlider imageSlider = binding.imageSlider;
                imageSlider.setImageList(imageList);
         img_cricketScore = binding.imgCricketScore;
         img_footballScore = binding.imgFootballScore;
         img_hockeyScore = binding.imgHockeyScore;
         img_basketballScore = binding.imgBasketballScore;
         img_tennisScore = binding.imgTennisScore;
        Glide.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/sportsmania-6efc2.appspot.com/o/img_cricket_circle.png?alt=media&token=f2a917e9-80b2-412d-ab12-11bd53b8559d").placeholder(R.drawable.img_placeholderlogo).into(img_cricketScore);
        Glide.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/sportsmania-6efc2.appspot.com/o/img_football_circle.png?alt=media&token=c5b07871-04ff-4885-b1d5-3c5bb56665f2").placeholder(R.drawable.img_placeholderlogo).into(img_footballScore);
        Glide.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/sportsmania-6efc2.appspot.com/o/img_hockey_circular.png?alt=media&token=2be08edc-27b2-4838-961d-b6c6025bf5e8").placeholder(R.drawable.img_placeholderlogo).into(img_hockeyScore);
        Glide.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/sportsmania-6efc2.appspot.com/o/img_basketball_circular.png?alt=media&token=7a2290ae-9d47-41d3-acbc-fcea83ef9d86").placeholder(R.drawable.img_placeholderlogo).into(img_basketballScore);
        Glide.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/sportsmania-6efc2.appspot.com/o/img_tabletennis-modified.png?alt=media&token=2b014259-89c3-4d96-a8cd-cfbf3b037749").placeholder(R.drawable.img_placeholderlogo).into(img_tennisScore);
        loadSportS();
        LinearLayout linear_cricketScore = binding.linearCricketScore;
        LinearLayout linear_footballScore = binding.linearFootballScore;
        LinearLayout linear_hockeyScore = binding.linearHockeyScore;
        LinearLayout linear_basketballScore = binding.linearBasketballScore;
        LinearLayout linear_tennisScore = binding.linearTennisScore;

        linear_cricketScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cricbuzz.com/"));
                startActivity(browserIntent);
            }
        });

        linear_footballScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.espn.in/football/scoreboard/_/league/all/date/20221119"));
                startActivity(browserIntent);
            }
        });
        linear_hockeyScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.diretta.it/hockey/"));
                startActivity(browserIntent);
            }
        });
        linear_basketballScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.diretta.it/basket/"));
                startActivity(browserIntent);
            }
        });
        linear_tennisScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.diretta.it/tennis/"));
                startActivity(browserIntent);
            }
        });

        TextView txt_seeAll = binding.txtSeeAll;
        txt_seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), LiveSportSeeAllActivity.class);
                startActivity(i);
            }
        });

        TextView txt_seeAll1 = binding.txtSeeAll1;
        txt_seeAll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), SportsListActivity.class);
                startActivity(i);
            }
        });

        CardView card_CricketSport = binding.cardCricketSport;
        CardView card_FootballSport = binding.cardFootballSport;
        CardView card_HockeySport = binding.cardHockeySport;
        CardView card_BasketballSport = binding.cardBasketballSport;
        CardView card_TennisSport = binding.cardTennisSport;
        CardView card_VolleySport = binding.cardVolleySport;

        card_CricketSport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), SportsDetailsActivity.class);
                i.putExtra("sportName","Cricket");
                startActivity(i);
            }
        });
        card_FootballSport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), SportsDetailsActivity.class);
                i.putExtra("sportName","Football");
                startActivity(i);
            }
        });
        card_HockeySport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), SportsDetailsActivity.class);
                i.putExtra("sportName","Hockey");
                startActivity(i);
            }
        });
        card_BasketballSport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), SportsDetailsActivity.class);
                i.putExtra("sportName","Basketball");
                startActivity(i);
            }
        });
        card_TennisSport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), SportsDetailsActivity.class);
                i.putExtra("sportName","Tennis");
                startActivity(i);
            }
        });
        card_VolleySport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), SportsDetailsActivity.class);
                i.putExtra("sportName","Volley");
                startActivity(i);
            }
        });
        CardView card_applyNow =binding.cardApplyNow;
        card_applyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), ApplyNowActivity.class);
                startActivity(i);

            }
        });
        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void loadSportS()
    {
        RelativeLayout rel_cricket = binding.relCricket;
        RelativeLayout rel_football = binding.relFootball;
        RelativeLayout rel_hockey = binding.relHockey;
        RelativeLayout rel_basketball = binding.relBasket;
        RelativeLayout rel_tennis = binding.relTennis;
        RelativeLayout rel_volley = binding.relVolley;

        Glide.with(getContext())
                .load("https://firebasestorage.googleapis.com/v0/b/sportsmania-6efc2.appspot.com/o/img_cricket.jpg?alt=media&token=70e287b4-10e6-418b-a48a-5c57cf47fedf")
                .into(new CustomTarget<Drawable>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        rel_cricket.setBackground(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });

        Glide.with(getContext())
                .load("https://firebasestorage.googleapis.com/v0/b/sportsmania-6efc2.appspot.com/o/img_football.jpg?alt=media&token=d38e7403-8c33-4ef3-ba83-a7c0a4669054")
                .into(new CustomTarget<Drawable>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        rel_football.setBackground(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });

        Glide.with(getContext())
                .load("https://firebasestorage.googleapis.com/v0/b/sportsmania-6efc2.appspot.com/o/img_hockey.jpg?alt=media&token=e970d364-7434-4bf1-b343-f62a91749684")
                .into(new CustomTarget<Drawable>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        rel_hockey.setBackground(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });

        Glide.with(getContext())
                .load("https://firebasestorage.googleapis.com/v0/b/sportsmania-6efc2.appspot.com/o/img_basket.jpg?alt=media&token=3c7ab95a-533c-4d0e-814f-091dae935950")
                .into(new CustomTarget<Drawable>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        rel_basketball.setBackground(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });

        Glide.with(getContext())
                .load("https://firebasestorage.googleapis.com/v0/b/sportsmania-6efc2.appspot.com/o/img_tennis.jpg?alt=media&token=b15544c6-47ee-4f47-b3a5-b857291b460d")
                .into(new CustomTarget<Drawable>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        rel_tennis.setBackground(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });

        Glide.with(getContext())
                .load("https://firebasestorage.googleapis.com/v0/b/sportsmania-6efc2.appspot.com/o/img_volley.jpg?alt=media&token=b19119bc-7555-4003-acdf-05ec5437319a")
                .into(new CustomTarget<Drawable>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        rel_volley.setBackground(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });
    }



}