package com.bat.model;

import com.bat.annotations.CustomEmail;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="instructor")
public class Instructor {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@NotNull(message = "First name is required")
	@Size(min = 1, max = 256, message = "First name must be at least 1 to maximum 256 characters")
	@Column(name="first_name")
	private String firstName;

	@NotNull(message = "Last name is required")
	@Size(min = 1, max = 256, message = "Last name must be at least 1 to maximum 256 characters")
	@Column(name="last_name")
	private String lastName;

	@NotNull(message = "Email is required")
	@CustomEmail
	@Column(name="email")
	private String email;

	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="instructor_detail_id")
	private InstructorDetails instructorDetails;
	
	@OneToMany(mappedBy = "instructor", 
			cascade= {CascadeType.DETACH, CascadeType.MERGE,
					CascadeType.PERSIST, CascadeType.REFRESH})
	private List<Course> courses;
	
	public Instructor() {}

	public Instructor(String fristName, String lastName, String email) {
		super();
		this.firstName = fristName;
		this.lastName = lastName;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public InstructorDetails getInstructorDetails() {
		return instructorDetails;
	}

	public void setInstructorDetails(InstructorDetails instructorDetails) {
		this.instructorDetails = instructorDetails;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public void addCourse(Course theCourse) {
		if(courses == null) {
			courses = new ArrayList<>();
		}
		courses.add(theCourse);
		theCourse.setInstructor(this);
	}

	public void addCourse(Course[] theCourses) {
		if(courses == null) {
			courses = new ArrayList<>();
		}
		for(Course c: theCourses) {
			courses.add(c);
			c.setInstructor(this);
		}
	}

	@Override
	public String toString() {
		return "Instructor [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", instructorDetails=" + instructorDetails + ", courses=" + courses + "]";
	}
}
