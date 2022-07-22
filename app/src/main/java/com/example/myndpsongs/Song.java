package com.example.myndpsongs;

import java.io.Serializable;

public class Song implements Serializable{

    private int id,year,stars;
    private String title,singers;

    public Song(int id,String title, String singers,int year, int stars) {
        this.id=id;
        this.title = title;
        this.singers=singers;
        this.year=year;
        this.stars=stars;
    }

    public int get_id() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSingers() {
        return singers;
    }

    public int getYear() {
        return year;
    }

    public int getStar() {
        return stars;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setSingers(String singers) {
        this.singers = singers;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
    public void updateDetails(String title,String singers, int year, int stars)
    {
        this.id=id;
        this.title = title;
        this.singers=singers;
        this.stars=stars;
        this.year=year;
    }
    public String toString() { return "ID: " + id + ",\nTitle: " + title +",\nSingers: "+singers+",\nYear: "+ year+
            " ,\nStars: "+displayStars(stars);  }

    public String displayStars(int stars)
    {
        String Show="";

        switch (stars)
        {
            case R.id.radioButton1:
                Show=" *";
                break;
            case  R.id.radioButton2:
                Show=" * * ";
                break;
            case  R.id.radioButton3:
                Show=" * * * ";
                break;
            case  R.id.radioButton4:
                Show=" * * * * ";
                break;
            case  R.id.radioButton5:
                Show=" * * * * * ";
                break;

        }
        return Show;
    }
}