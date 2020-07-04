package com.bat.dto;

import com.bat.annotations.Exists;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public class CourseDto extends BaseDto implements Serializable {
    private int id;
    private String encId;

    @NotNull(message = "Title can not be null")
    private String title;
    private float rating;
    private int instructor_id;
    private String encInstructor_id;
    private String name;

    @NotNull(message = "Email can not be null")
    @Exists(table = "instructor", column = "email",
            message = "No instructor found associated with this email")
    private String email;
    private List<ReviewDto> reviewDtoList;
    private List<StudentWithCourseDto> students;

    public CourseDto() {};

    public CourseDto(String encId, String title, float rating) {
        this.encId = encId;
        this.title = title;
        this.rating = rating;
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

    public List<StudentWithCourseDto> getStudents() {
        return students;
    }

    public void setStudents(List<StudentWithCourseDto> students) {
        this.students = students;
    }
}
