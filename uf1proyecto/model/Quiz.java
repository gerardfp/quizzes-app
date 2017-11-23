package com.example.gerard.uf1proyecto.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gerard on 21/11/2017.
 */
@Entity(foreignKeys = @ForeignKey(entity = Repository.class,
        parentColumns = "id",
        childColumns = "repositoryId"))
public class Quiz {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public String name;
    public int result;

    public long repositoryId;
}
