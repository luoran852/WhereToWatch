package com.example.wheretowatch;

import android.os.Bundle;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    RecyclerView mRecyclerView ;
    RecyclerView.LayoutManager mLayoutManager;
    RvAdapter mAdapter;
    ArrayList searchedItems;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText searchText = (EditText)view.findViewById(R.id.etSearch);
        AppCompatImageButton searchBtn = (AppCompatImageButton)view.findViewById(R.id.btnSearch);
        AppCompatButton eraseBtn = (AppCompatButton)view.findViewById(R.id.btnErase);
        LinearLayout searchTextInfo = (LinearLayout)view.findViewById(R.id.searchTexts);
        TextView searchName = (TextView)view.findViewById(R.id.searchName);
        HorizontalScrollView recommends = (HorizontalScrollView)view.findViewById(R.id.searchRecs);
        searchedItems = new ArrayList();

        // If enter pressed, do search
        searchText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    String searchString = searchText.getText().toString();
                    Log.d("search", searchString);
                    searchTextInfo.setVisibility(View.VISIBLE);
                    recommends.setVisibility(View.VISIBLE);
                    searchName.setText(searchString);
                }
                return false;
            }
        });

        // If search button pressed, do search
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchString = searchText.getText().toString();
                Log.d("search", searchString);
                searchTextInfo.setVisibility(View.VISIBLE);
                recommends.setVisibility(View.VISIBLE);
                searchName.setText(searchString);
                search(view);
            }
        });

        // If erase button pressed, erase text
        eraseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchText.getText().clear();
                searchTextInfo.setVisibility(View.INVISIBLE);
                mRecyclerView.setVisibility(View.INVISIBLE);
                recommends.setVisibility(View.INVISIBLE);
            }
        });

    }

    public void search(View view) {
        mRecyclerView = (RecyclerView)view.findViewById(R.id.rvSearched);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new RvAdapter(searchedItems);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setVisibility(View.VISIBLE);
    }
}
