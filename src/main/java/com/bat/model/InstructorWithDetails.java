package com.bat.model;

public class InstructorWithDetails {
    private Instructor instructor;
    private InstructorDetails instructorDetails;

    public InstructorWithDetails() {
        this.instructor = new Instructor();
        this.instructorDetails = new InstructorDetails();
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public InstructorDetails getInstructorDetails() {
        return instructorDetails;
    }

    public void setInstructorDetails(InstructorDetails instructorDetails) {
        this.instructorDetails = instructorDetails;
    }
}
