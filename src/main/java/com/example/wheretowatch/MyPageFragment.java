package com.example.wheretowatch;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.core.Context;

import java.util.ArrayList;

public class MyPageFragment extends Fragment {

    private RecyclerView recyclerView_keep, recyclerView_record;
    private RecyclerViewAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<Movie> movies = new ArrayList<Movie>();
    private Button btn_login, btn_logout; // 로그인, 로그아웃 버튼
    private FirebaseAuth mFirebaseAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mypage, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FirebaseApp.initializeApp(getContext()); // firebase 초기화

        mFirebaseAuth = FirebaseAuth.getInstance();

//        btn_login.setVisibility(View.INVISIBLE); // 로그인 버튼 숨기기
//        btn_logout.setVisibility(View.VISIBLE); // 로그아웃 버튼 보이기

//        for(int i=0; i<5; i++) {
//            movies.add(new ItemMovie(R.drawable.about));
//            movies.add(new ItemMovie(R.drawable.notebook));
//        }

        //recyclerview 보고싶어요(찜하기+평가하기)
        recyclerView_keep = (RecyclerView)view.findViewById(R.id.mypage_rvRanking_keep);
        adapter = new RecyclerViewAdapter(getContext(), movies);

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_keep.setHasFixedSize(true);
        recyclerView_keep.setLayoutManager(linearLayoutManager);
        recyclerView_keep.setAdapter(adapter);

        //recyclerview 시청기록
        recyclerView_record = (RecyclerView)view.findViewById(R.id.mypage_rvRanking_record);
        adapter = new RecyclerViewAdapter(getContext(), movies);

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_record.setHasFixedSize(true);
        recyclerView_record.setLayoutManager(linearLayoutManager);
        recyclerView_record.setAdapter(adapter);

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
}
