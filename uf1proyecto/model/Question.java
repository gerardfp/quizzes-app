package com.example.gerard.uf1proyecto.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by gerard on 21/11/2017.
 */

@Entity(foreignKeys = @ForeignKey(entity = Quiz.class,
        parentColumns = "id",
        childColumns = "quizId"))
public class Question {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public String question;
    public int correct;

    public long quizId;

}
