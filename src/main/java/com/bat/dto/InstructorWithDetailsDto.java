package com.bat.dto;

import com.bat.annotations.CustomEmail;
import com.bat.annotations.Unique;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Component
public class InstructorWithDetailsDto extends BaseDto implements Serializable {
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
    @Unique(table = "instructor", column = "email",
            message = "Email already taken")
    private String email;

    private int instructor_detail_id;

    @NotNull(message = "Channel is required")
    @Size(min = 3, message = "Channel can not be less than three characters")
    @Pattern(regexp = "^((https):\\/\\/)?(www.)?[a-z0-9]+(\\.[a-z]+)+(\\/[a-zA-Z0-9#]+\\/?)*$", message = "Invalid channel url")
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
