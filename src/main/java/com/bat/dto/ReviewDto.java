package com.bat.dto;

import java.io.Serializable;

public class ReviewDto extends BaseDto implements Serializable {
    private float rating;
    private String comment;

    public ReviewDto() {}

    public ReviewDto(float rating, String comment) {
        this.rating = rating;
        this.comment = comment;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
