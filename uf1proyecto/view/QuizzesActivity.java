package com.example.gerard.uf1proyecto.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.gerard.uf1proyecto.model.Quiz;
import com.example.gerard.uf1proyecto.viewmodel.QuizzesViewModel;

import java.util.ArrayList;
import java.util.List;

public class QuizzesActivity extends AppCompatActivity {

    ArrayList<Quiz> quizzes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        long repositoryId = intent.getLongExtra("repositoryId",0);

        QuizzesViewModel quizzesViewModel = ViewModelProviders.of(this).get(QuizzesViewModel.class);
        quizzesViewModel.getQuizzes(repositoryId).observe(this, new Observer<List<Quiz>>() {
            @Override
            public void onChanged(@Nullable List<Quiz> result) {
                quizzes.addAll(result);
            }
        });
    }
}
