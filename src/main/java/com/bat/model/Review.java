package com.bat.model;

import javax.persistence.*;

@Entity
@Table(name="review")
public class Review {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="comment")
	private String comment;
	
	@Column(name="rating")
	private double rating;

	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="course_id")
	private Course course;
	
	public Review() {}
	
	public Review(String comment, double rating) {
		this.comment = comment;
		this.rating = rating;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public Course getCourse() { return course; }

	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "Review[" + "id=" + id + ", comment='" + comment + '\'' + ", rating=" + rating + ", course=" + course + "]";
	}
}
