package com.bat.dto;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ReviewDto extends BaseDto implements Serializable {
    private int id;
    private String encId;
    private String encCourse_id;

    @NotNull(message = "Rating can not be null")
    @DecimalMin(message = "Invalid rating", value = "0.0")
    @DecimalMax(message = "Invalid rating", value = "5.0")
    private float rating;

    private String comment;

    public ReviewDto() {}

    public ReviewDto(float rating, String comment) {
        this.rating = rating;
        this.comment = comment;
    }

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

    public String getEncCourse_id() {
        return encCourse_id;
    }

    public void setEncCourse_id(String encCourse_id) {
        this.encCourse_id = encCourse_id;
    }
}
