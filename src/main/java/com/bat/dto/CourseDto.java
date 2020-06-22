package com.bat.dto;

import java.io.Serializable;
import java.util.List;

public class CourseDto extends BaseDto implements Serializable {
    private int id;
    private String encId;
    private String title;
    private float rating;
    private int instructor_id;
    private String encInstructor_id;
    private String name;
    private String email;
    private List<ReviewDto> reviewDtoList;

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

    public int getInstructor_id() {
        return instructor_id;
    }

    public void setInstructor_id(int instructor_id) {
        this.instructor_id = instructor_id;
    }

    public String getEncInstructor_id() {
        return encInstructor_id;
    }

    public void setEncInstructor_id(String encInstructor_id) {
        this.encInstructor_id = encInstructor_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ReviewDto> getReviewDtoList() {
        return reviewDtoList;
    }

    public void setReviewDtoList(List<ReviewDto> reviewDtoList) {
        this.reviewDtoList = reviewDtoList;
    }
}
