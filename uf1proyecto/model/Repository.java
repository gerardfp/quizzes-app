package com.example.gerard.uf1proyecto.model;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


/**
 * Created by gerard on 22/11/2017.
 */

@Entity
public class Repository {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public String name;
    public String url;
    public String imageUrl;
}
