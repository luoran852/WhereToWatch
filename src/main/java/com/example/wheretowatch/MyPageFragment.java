package com.example.wheretowatch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Context;

import java.io.Serializable;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

public class MyPageFragment extends Fragment implements MyPageAdapter.OnMovieClickListener{

    private RecyclerView recyclerView_keep, recyclerView_watch;
    private MyPageAdapter keepAdapter, watchAdapter;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<Movie> keptMovies = new ArrayList<Movie>();
    private ArrayList<Movie> watchedMovies = new ArrayList<Movie>();
    private Button btn_login, btn_logout; // 로그인, 로그아웃 버튼
    private TextView txt_keep, txt_watch, txt_before_login;
    private FirebaseAuth mFirebaseAuth;

    SharedPreferences sharedPreferences;
    private FirebaseDatabase userDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference userReference = userDatabase.getReference();
    String title, original_title, name, poster_path, overview, backdrop_path, release_date;
    int id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mypage, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FirebaseApp.initializeApp(getContext()); // firebase 초기화
//        mFirebaseAuth = FirebaseAuth.getInstance();

        FirebaseDatabase userDatabase = FirebaseDatabase.getInstance();
        DatabaseReference userReference = userDatabase.getReference();

        sharedPreferences = getActivity().getSharedPreferences("login", MODE_PRIVATE);    // test 이름의 기본모드 설정, 만약 test key값이 있다면 해당 값을 불러옴.
        String sf_name = sharedPreferences.getString("login","noname");

        // firebase 시청목록 가져오기
        DatabaseReference watch = userReference.child("WhereToWatch").child("users").child(sf_name).child("watch");
        watch.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()) {
                    title = ds.child("title").getValue(String.class);
                    original_title = ds.child("original_title").getValue(String.class);
                    name = ds.child("name").getValue(String.class);
                    id = ds.child("id").getValue(Integer.class);
                    poster_path = ds.child("poster_path").getValue(String.class);
                    Log.d("poster_path", poster_path);
                    overview = ds.child("overview").getValue(String.class);
                    backdrop_path = ds.child("backdrop_path").getValue(String.class);
                    release_date = ds.child("release_date").getValue(String.class);
                    watchedMovies.add(0, new Movie(title, original_title, name, id, poster_path, overview, backdrop_path, release_date));
                    watchAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // firebase 찜목록 가져오기
        DatabaseReference keep = userReference.child("WhereToWatch").child("users").child(sf_name).child("favorite");
        keep.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()) {
                    title = ds.child("title").getValue(String.class);
                    original_title = ds.child("original_title").getValue(String.class);
                    name = ds.child("name").getValue(String.class);
                    id = ds.child("id").getValue(Integer.class);
                    poster_path = ds.child("poster_path").getValue(String.class);
                    overview = ds.child("overview").getValue(String.class);
                    backdrop_path = ds.child("backdrop_path").getValue(String.class);
                    release_date = ds.child("release_date").getValue(String.class);
                    keptMovies.add(0, new Movie(title, original_title, name, id, poster_path, overview, backdrop_path, release_date));
                    keepAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //recyclerview 보고싶어요(찜하기+평가하기)
        recyclerView_keep = (RecyclerView)view.findViewById(R.id.mypage_rvRanking_keep);
        keepAdapter = new MyPageAdapter(getContext(), keptMovies, this);

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_keep.setHasFixedSize(true);
        recyclerView_keep.setLayoutManager(linearLayoutManager);
        recyclerView_keep.setAdapter(keepAdapter);

        //recyclerview 시청기록
        recyclerView_watch = (RecyclerView)view.findViewById(R.id.mypage_rvRanking_record);
        watchAdapter = new MyPageAdapter(getContext(), watchedMovies, this);

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_watch.setHasFixedSize(true);
        recyclerView_watch.setLayoutManager(linearLayoutManager);
        recyclerView_watch.setAdapter(watchAdapter);

        txt_keep = (TextView) view.findViewById(R.id.txt_keep);
        txt_watch = (TextView) view.findViewById(R.id.txt_record);
        txt_before_login = (TextView) view.findViewById(R.id.txt_before_login);

        // 비로그인 상태일 때 리싸이클러뷰 안 보이게
        if (sf_name.equals("noname")) {
            txt_keep.setVisibility(View.INVISIBLE);
            txt_watch.setVisibility(View.INVISIBLE);
            recyclerView_keep.setVisibility(View.INVISIBLE);
            recyclerView_watch.setVisibility(View.INVISIBLE);
            txt_before_login.setVisibility(View.VISIBLE);
        }

        btn_login = (Button) getView().findViewById(R.id.btn_login);
        btn_logout = (Button) getView().findViewById(R.id.btn_logout);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 로그인 화면으로 이동
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 로그아웃 하기
                mFirebaseAuth.signOut();

                Toast.makeText(getActivity(), "로그아웃에 성공했습니다", Toast.LENGTH_SHORT).show();
            }
        });

        // 탈퇴 처리
        // mFirebaseAuth.getCurrentUser().delete();

    }

    @Override
    public void onMovieClick(int position, ArrayList<Movie> mMovieList) {
        Log.e(TAG, "onMovieClick: 영화 아이템이 클릭됨" + position);

        // 세부 액티비티로 이동
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("movieList", (Serializable)mMovieList.get(position));
        startActivity(intent);
    }
}