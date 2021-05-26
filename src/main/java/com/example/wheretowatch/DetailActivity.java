package com.example.wheretowatch;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity implements Serializable {

    ImageView img_poster, img_ott;
    Button btn_watch, btn_keep;
    TextView txt_title, txt_year, txt_episodes_or_time, txt_genre, txt_director, txt_rating, txt_overview;
    String title, original_title, poster_path, overview, release_date;
    int position;
    private RecyclerView recyclerView_ott;
    private OttRvAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList ottList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        original_title = intent.getStringExtra("original_title");
        poster_path = intent.getStringExtra("poster_path");
        overview = intent.getStringExtra("overview");
        release_date = intent.getStringExtra("release_date");

        img_poster = findViewById(R.id.searched_detail_img);
        img_ott = findViewById(R.id.ott_img);
        txt_title = findViewById(R.id.txt_title);
        txt_year = findViewById(R.id.txt_year);
        txt_episodes_or_time = findViewById(R.id.txt_episodes_or_time);
        txt_genre = findViewById(R.id.txt_genre_detail);
        txt_director = findViewById(R.id.txt_director_detail);
        txt_rating = findViewById(R.id.txt_rating_detail);
        txt_overview = findViewById(R.id.txt_synopsis_detail);
        btn_watch = findViewById(R.id.btn_watch);
        btn_keep = findViewById(R.id.btn_keep);
        recyclerView_ott = findViewById(R.id.recyclerview_ott);

        // 임시 oot 이미
        for(int i=0; i<3; i++){
            ottList.add(img_ott);
        }

        adapter = new OttRvAdapter(this , ottList);
        recyclerView_ott.setAdapter(adapter);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_ott.setHasFixedSize(true);
        recyclerView_ott.setLayoutManager(linearLayoutManager);

    }
}
