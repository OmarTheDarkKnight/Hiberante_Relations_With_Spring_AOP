package com.bat.dto;

import java.io.Serializable;

public class InstructorDto implements Serializable {
    private int id;
    private String first_name;
    private String last_name;
    private String email;

    public InstructorDto() { }

    public InstructorDto(int id, String first_Name, String lastName, String email) {
        this.id = id;
        this.first_name = first_Name;
        this.last_name = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
