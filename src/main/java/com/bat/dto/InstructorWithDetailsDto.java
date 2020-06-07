package com.bat.dto;

import java.io.Serializable;

public class InstructorWithDetailsDto extends BaseDto implements Serializable {
    private int id;
    private String encId;
    private String first_name;
    private String last_name;
    private String email;
    private int instructor_detail_id;
    private String youtube_channel;
    private String hobby;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEncId(String id) {
        this.encId = id;
    }

    public String getEncId() {
        return encId;
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

    public int getInstructor_detail_id() {
        return instructor_detail_id;
    }

    public void setInstructor_detail_id(int instructor_detail_id) {
        this.instructor_detail_id = instructor_detail_id;
    }

    public String getYoutube_channel() {
        return youtube_channel;
    }

    public void setYoutube_channel(String youtube_channel) {
        this.youtube_channel = youtube_channel;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}
