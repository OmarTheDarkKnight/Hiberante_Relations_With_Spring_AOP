package com.bat.dto;

import com.bat.annotations.CustomEmail;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

public class StudentWithCourseDto extends BaseDto implements Serializable {
    private int id;
    private String encId;

    @NotNull(message = "First name is required")
    @Size(min = 1, max = 256, message = "First name must be at least 1 to maximum 256 characters")
    private String first_name;

    @NotNull(message = "Last name is required")
    @Size(min = 1, max = 256, message = "Last name must be at least 1 to maximum 256 characters")
    private String last_name;

    @NotNull(message = "Email is required")
    @CustomEmail
    private String email;

    private List<CourseDto> courses;

    public StudentWithCourseDto(){}

    public StudentWithCourseDto(String first_name, String last_name, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
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

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<CourseDto> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseDto> courses) {
        this.courses = courses;
    }
}
