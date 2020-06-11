package com.bat.dto;

import java.io.Serializable;

public class CourseDto extends BaseDto implements Serializable {
    private int id;
    private String encId;
    private String title;
    private float rating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEncId() {
        return encId;
    }

    public void setEncId(String encId) {
        this.encId = encId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
