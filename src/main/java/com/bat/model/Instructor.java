package com.bat.model;

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

@Entity
@Table(name="instructor")
public class Instructor {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	private Name name;

	@Column(name="email")
	private String email;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="instructor_detail_id")
	private InstructorDetails instructorDetails;
	
	@OneToMany(mappedBy = "instructor", 
			cascade= {CascadeType.DETACH, CascadeType.MERGE,
					CascadeType.PERSIST, CascadeType.REFRESH})
	private List<Course> courses;
	
	public Instructor() {}

	public Instructor(Name name, String email) {
		super();
		this.name = name;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Name getName() {
		return name;
	}

	public void setFirstName(Name name) {
		this.name = name;
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
		return "Instructor [id=" + id + ", firstName=" + name.getFirstName() + ", lastName=" + name.getLastName()
				+ ", email=" + email + ", instructorDetails=" + instructorDetails + ", courses=" + courses + "]";
	}
}
