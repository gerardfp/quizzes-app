package com.example.gerard.uf1proyecto;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.gerard.uf1proyecto.model.Answer;
import com.example.gerard.uf1proyecto.model.Question;
import com.example.gerard.uf1proyecto.model.Quiz;
import com.example.gerard.uf1proyecto.model.Repository;

import java.util.List;

/**
 * Created by gerard on 23/11/2017.
 */
@Dao
public interface QuizzesDao {

    @Query("select * from repository")
    LiveData<List<Repository>> getRepositories();

    @Query("select * from repository where name = :name")
    public Repository getRepository(String name);

    @Insert( onConflict = 1)
    public long insertRepository(Repository repository);

    @Query("select  * from quiz where repositoryId = :repositoryId")
    public LiveData<List<Quiz>> getQuizzes(long repositoryId);

    @Insert
    public long insertQuiz(Quiz quiz);

    @Update
    public void updateQuizz(Quiz quiz);

    @Query("select * from question where quizId = :quizId")
    public LiveData<List<Question>> getQuestions(long quizId);

    @Insert
    public long insertQuestion(Question question);

    @Query("select * from answer where questionId = :questionId")
    public LiveData<List<Answer>> getAnswers(long questionId);

    @Insert
    public long insertAnswer(Answer answer);


}
