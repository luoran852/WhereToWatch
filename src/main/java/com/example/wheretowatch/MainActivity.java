package com.example.wheretowatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    BottomNavigationView botNav;
    MainFragment mainFragment = new MainFragment();
    SearchFragment searchFragment = new SearchFragment();
    RankingFragment rankingFragment = new RankingFragment();
    MyPageFragment myPageFragment = new MyPageFragment();

    ArrayList<Movie> mMovieList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.mainFragment, mainFragment).commitAllowingStateLoss();
        botNav = findViewById(R.id.botNavMain);

        recyclerView = (RecyclerView) findViewById(R.id.main_rvRanking_romance);

        botNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        transaction.replace(R.id.mainFragment, mainFragment).commitAllowingStateLoss();
                        break;
                    case R.id.nav_search:
                        transaction.replace(R.id.mainFragment, searchFragment).commitAllowingStateLoss();
                        break;
                    case R.id.nav_ranking:
                        transaction.replace(R.id.mainFragment, rankingFragment).commitAllowingStateLoss();
                        break;
                    case R.id.nav_my:
                        transaction.replace(R.id.mainFragment, myPageFragment).commitAllowingStateLoss();
                        break;
                }

                return true;
            }
        });
    }



        }










