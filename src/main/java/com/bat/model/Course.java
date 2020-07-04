package com.bat.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="course")
public class Course {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="title")
	private String title;

	@Transient
	private float rating;
	
	@ManyToOne(cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="instructor_id")
	private Instructor instructor;

	@ManyToMany(mappedBy = "courses")
	private List<Student> students;
	
	public Course() {}
	
	public Course(String title) {
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + ", instructor=" + instructor.getId() + "]";
	}
}
