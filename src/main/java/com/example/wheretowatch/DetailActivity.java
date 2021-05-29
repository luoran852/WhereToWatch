package com.example.wheretowatch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.security.Provider;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends AppCompatActivity implements Serializable {

    ImageView img_poster, img_ott;
    Button btn_watch, btn_keep;
    TextView txt_title, txt_year, txt_episodes_or_time, txt_genre, txt_rating, txt_overview;
    String title, original_title, name, poster_path, overview, backdrop_path, release_date;
    int position;
    private int id;
    private RecyclerView recyclerView_ott;
    private OttRvAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<String> ottList = new ArrayList();

    private FirebaseDatabase userDatabase;
    private DatabaseReference userReference;

    SharedPreferences sharedPreferences;

    private String api_key = "89247ab465eba040acb566dcd1724b96";
    private final String baseUrl = "https://api.themoviedb.org/3/";
    ProviderRequest providerRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // 각 영화 데이터
        Intent passedIntent = getIntent();
        Movie list = (Movie) passedIntent.getSerializableExtra("movieList");
        passedIntent.removeExtra("movieList");
        id = list.getId();
        String idString = Integer.toString(id);

        FirebaseApp.initializeApp(getApplicationContext()); // firebase 초기화
        userDatabase = FirebaseDatabase.getInstance();
        userReference = userDatabase.getReference();

        img_poster = findViewById(R.id.searched_detail_img);
        img_ott = findViewById(R.id.ott_img);
        txt_title = findViewById(R.id.txt_title);
        txt_year = findViewById(R.id.txt_year);
        txt_episodes_or_time = findViewById(R.id.txt_episodes_or_time);
        txt_genre = findViewById(R.id.txt_genre_detail);
        txt_rating = findViewById(R.id.txt_rating_detail);
        txt_overview = findViewById(R.id.txt_synopsis_detail);
        btn_watch = findViewById(R.id.btn_watch);
        btn_keep = findViewById(R.id.btn_keep);
        recyclerView_ott = findViewById(R.id.recyclerview_ott);

        position = passedIntent.getIntExtra("position", 0);
        passedIntent.removeExtra("position");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        providerRequest = retrofit.create(ProviderRequest.class);
        Call<ProviderResponse> searchMovie = providerRequest.getProvider(id, api_key);
        searchMovie.enqueue(responseCallback);

        // 임시 oot 이미지
//        for(int i=0; i<3; i++){
//            ottList.add(img_ott);
//        }

        adapter = new OttRvAdapter(this , ottList);
        recyclerView_ott.setAdapter(adapter);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_ott.setHasFixedSize(true);
        recyclerView_ott.setLayoutManager(linearLayoutManager);


        // 아이템 정보 연결
        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500" + list.getPoster_path())
                .into(img_poster);
//        Glide.with(this)
//                .load(list.getPoster_path())
//                .into(img_ott);
        txt_title.setText(list.getTitle());
        txt_year.setText(list.getRelease_date());
        //추가해야함


        // 시청기록 버튼 클릭
        btn_watch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //추가해야함!
                title = txt_title.getText().toString();
                poster_path = list.getPoster_path();
                overview = list.getOverview();
                release_date = list.getRelease_date();

                Movie movie = new Movie(title, original_title, name, id, poster_path, overview, backdrop_path, release_date);

                sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);    // test 이름의 기본모드 설정, 만약 test key값이 있다면 해당 값을 불러옴.
                String name = sharedPreferences.getString("login","noname");
                if(name.equals("noname"))
                {
                    Toast.makeText(getApplicationContext(), "로그인이 필요한 서비스입니다.", Toast.LENGTH_SHORT).show();
                }
                else{
                    userReference.child("WhereToWatch").child("users").child(name).child("watch").child(idString).setValue(movie).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getApplicationContext(), "시청기록 목록에 추가되었습니다.", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "시청기록 추가에 실패했습니다.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        // 찜버튼 클릭
        btn_keep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //추가해야함!
                title = txt_title.getText().toString();
                poster_path = list.getPoster_path();
                overview = list.getOverview();
                release_date = list.getRelease_date();

                Log.d("poster", list.getPoster_path());
                Movie movie = new Movie(title, original_title, name, id, poster_path, overview, backdrop_path, release_date);

                sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);    // test 이름의 기본모드 설정, 만약 test key값이 있다면 해당 값을 불러옴.
                String name = sharedPreferences.getString("login","noname");
                if(name.equals("noname"))
                {
                    Toast.makeText(getApplicationContext(), "로그인이 필요한 서비스입니다.", Toast.LENGTH_SHORT).show();
                }
                else{
                    userReference.child("WhereToWatch").child("users").child(name).child("favorite").child(idString).setValue(movie).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getApplicationContext(), "찜목록에 추가되었습니다.", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "찜목록 추가에 실패했습니다.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }
    private Callback<ProviderResponse> responseCallback = new Callback<ProviderResponse>() {

        @Override
        public void onResponse(Call<ProviderResponse> call, Response<ProviderResponse> response) {
            if (response.isSuccessful()) {
                Log.d("ResponseSuccess", "Success");
                ProviderResponse result = response.body();
                ProductionCompany[] platforms= response.body().getProduction_companies();
                int total = platforms.length;
                Log.d("ResponseSuccess", String.valueOf(total));

                for (int i=0; i<total; i++) {
                    Log.d("ResponseSuccess", platforms[i].getName());
                    if (platforms[i].getLogo_path()!=null) {
                        ottList.add(platforms[i].getLogo_path());
                        adapter.notifyDataSetChanged();
                    }

                }

                Genre[] genres = response.body().getGenres();
                String genreText = "";
                for (int i=0; i<genres.length; i++) {
                    genreText += genres[i].getName() + ", ";
                }

                int runtime = (int) result.getRuntime();
                String runtimeString = Integer.toString(runtime);
                txt_episodes_or_time.setText(runtimeString);
                txt_genre.setText(genreText);
                txt_rating.setText(result.getVote_average()+"");
                txt_overview.setText(result.getOverview());
            }
            else {
                Log.d("ResponseError", response.raw().toString());
            }
        }

        @Override
        public void onFailure(Call<ProviderResponse> call, Throwable t) {
            Log.d("Response", "Fail");
            t.printStackTrace();
        }
    };

}