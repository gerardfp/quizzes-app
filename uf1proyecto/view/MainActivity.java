package com.example.gerard.uf1proyecto.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.gerard.uf1proyecto.model.Repository;
import com.example.gerard.uf1proyecto.viewmodel.QuizzesViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Repository> repositories;

    QuizzesViewModel quizzesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        quizzesViewModel = ViewModelProviders.of(this).get(QuizzesViewModel.class);

        quizzesViewModel.downloadRepository("https://gitlab.com/gerardfp/my-quizzes-json/raw/master/quizzes.json");
        quizzesViewModel.downloadRepository("https://gitlab.com/gerardfp/my-quizzes-json/raw/master/quizzes2.json");
        quizzesViewModel.downloadRepository("https://gitlab.com/gerardfp/my-quizzes-json/raw/master/quizzes3.json");
        quizzesViewModel.downloadRepository("https://gitlab.com/gerardfp/my-quizzes-json/raw/master/quizzes4.json");
        quizzesViewModel.downloadRepository("https://gitlab.com/gerardfp/my-quizzes-json/raw/master/quizzes5.json");
        quizzesViewModel.downloadRepository("https://gitlab.com/gerardfp/my-quizzes-json/raw/master/quizzes6.json");
        quizzesViewModel.downloadRepository("https://gitlab.com/gerardfp/my-quizzes-json/raw/master/quizzes7.json");
        quizzesViewModel.downloadRepository("https://gitlab.com/gerardfp/my-quizzes-json/raw/master/quizzes8.json");
        quizzesViewModel.downloadRepository("https://gitlab.com/gerardfp/my-quizzes-json/raw/master/quizzes9.json");
                
        quizzesViewModel.getRepositories().observe(this, new Observer<List<Repository>>() {
            @Override
            public void onChanged(@Nullable List<Repository> repositories) {
                if (repositories != null) {
                    MainActivity.this.repositories.clear();
                    MainActivity.this.repositories.addAll(repositories);
                }
            }
        });
    }
}
