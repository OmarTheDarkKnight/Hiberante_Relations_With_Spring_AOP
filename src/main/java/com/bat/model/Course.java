package com.bat.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

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
	
	@ManyToOne(cascade= {CascadeType.DETACH, CascadeType.MERGE,
						CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="instructor_id")
	private Instructor instructor;
	
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="course_id", nullable = false)
	private List<Review> reviews;
	
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

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
	public void addReview(Review theReview) {
		if(reviews == null) {
			reviews = new ArrayList<Review>();
		}
		
		reviews.add(theReview);
	}
	
	public void addReview(Review[] theReviews) {
		if(reviews == null) {
			reviews = new ArrayList<>();
		}
		
		for(Review r: theReviews) {
			reviews.add(r);
		}
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + ", instructor=" + instructor.getId()
			+ ", reviews=" + reviews + "]";
	}
}
