package com.example.gerard.uf1proyecto.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by gerard on 23/11/2017.
 */
@Entity(foreignKeys = @ForeignKey(entity = Question.class,
        parentColumns = "id",
        childColumns = "questionId"))
public class Answer {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public String answer;

    public long questionId;
}
