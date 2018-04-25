package com.ufmg.dener.movieme;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dener on 14/04/2018.
 */

public class Movie {
    private String name;
    private String director;
    private String genre;
    private int age;
    private int date;
    private int id;

    Movie() {
        this.name = null;
        this.genre = null;
        this.director = null;
        this.age = 3;
        this.date = -1;
        this.id = 0;
    }

    Movie(String name,  String director, String genre, int age, int date) {
        this.name = name;
        this.genre = genre;
        this.director = director;
        this.age = age;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
