package com.divyanshu.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Healthfragment extends Fragment {
    String api="bbd9029b6ed7449db138cdf2c6b52145";
    ArrayList<Modelclass> modelclassArrayList;
    Adapter adapter;
    String country="in";
    private RecyclerView healthrecyclerview;
    private String category="health";

    @Nullable

    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable  Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.healthfragment,null);
        healthrecyclerview=v.findViewById(R.id.healthrecyclerview);
        modelclassArrayList=new ArrayList<>();
        healthrecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter=new Adapter(getContext(),modelclassArrayList);
        healthrecyclerview.setAdapter(adapter);
        findNews();
        return v;
    }
    private void findNews() {
        ApiUtilities.getApiInterface().getCategoryNews(country,category,100,api).enqueue(new Callback<mainNews>() {
            @Override
            public void onResponse(Call<mainNews> call, Response<mainNews> response) {
                if (response.isSuccessful())
                {
                    modelclassArrayList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<mainNews> call, Throwable t) {

            }
        });
    }
}

