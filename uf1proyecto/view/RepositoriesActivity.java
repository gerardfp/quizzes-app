package com.example.gerard.uf1proyecto.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.gerard.uf1proyecto.model.Repository;
import com.example.gerard.uf1proyecto.viewmodel.QuizzesViewModel;

import java.util.List;

public class RepositoriesActivity extends AppCompatActivity {
    List<Repository> repositories;

    QuizzesViewModel quizzesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        quizzesViewModel = ViewModelProviders.of(this).get(QuizzesViewModel.class);

        quizzesViewModel.getRepositories().observe(this, new Observer<List<Repository>>() {
            @Override
            public void onChanged(@Nullable List<Repository> result) {
                if (repositories != null) {
                    repositories.clear();
                    repositories.addAll(result);
                }
            }
        });
    }

    void addNewRepo(){
        String repoUrl = "http://...";

        quizzesViewModel = ViewModelProviders.of(this).get(QuizzesViewModel.class);

        quizzesViewModel.downloadRepository(repoUrl).observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if(aBoolean){
                    System.out.println("DOWNLOADED!!!!!");
                } else {
                    System.out.println("NOT DOWNLOADEDD :((((((");
                }
            }
        });
    }
}
