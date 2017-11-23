package com.example.gerard.uf1proyecto.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.gerard.uf1proyecto.R;
import com.example.gerard.uf1proyecto.model.Answer;
import com.example.gerard.uf1proyecto.model.Question;
import com.example.gerard.uf1proyecto.viewmodel.QuizzesViewModel;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    int questionIndex;
    Question question;
    List<Question> questions;
    List<Answer> answers;
    int correct;

    QuizzesViewModel quizzesViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Intent intent = getIntent();
        final long quizId = intent.getLongExtra("quizId",0);

        questions = new ArrayList<>();
        answers = new ArrayList<>();

        quizzesViewModel = ViewModelProviders.of(this).get(QuizzesViewModel.class);
        quizzesViewModel.getQuestions(quizId).observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(@Nullable List<Question> result) {
                questions.clear();
                questions.addAll(result);
                loadAnswers();
            }
        });
    }

    void loadAnswers(){
        question = questions.get(questionIndex);

        quizzesViewModel.getAnswers(question.id).observe(this, new Observer<List<Answer>>() {
            @Override
            public void onChanged(@Nullable List<Answer> result) {
                if (result != null) {
                    answers.clear();
                    answers.addAll(result);
                }
            }
        });
    }
}
