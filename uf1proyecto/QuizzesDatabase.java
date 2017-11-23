package com.example.gerard.uf1proyecto;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.gerard.uf1proyecto.model.Answer;
import com.example.gerard.uf1proyecto.model.Question;
import com.example.gerard.uf1proyecto.model.Quiz;
import com.example.gerard.uf1proyecto.model.Repository;


@Database(entities = {Repository.class, Quiz.class, Question.class, Answer.class}, version = 11)
public abstract class QuizzesDatabase extends RoomDatabase {

    private static QuizzesDatabase INSTANCE;

    public static QuizzesDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), QuizzesDatabase.class, "db")
                            .fallbackToDestructiveMigration()
                            .build();
        }
        return INSTANCE;
    }

    public abstract QuizzesDao getQuizzesDao();
}
