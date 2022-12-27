package com.prajwal.entity;

import java.sql.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="comment")
public class Comment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="email")
	private String email;
	@Column(name="comment")
	private String comment;
	
	@ManyToOne
	@JoinColumn(name="post_id")
	private Post post;
	
	public Comment() {

	}
	public Comment(String name, String email, String comment, int post_id, Date created_at,
			Date updated_at) {
		super();
		this.name = name;
		this.email = email;
		this.comment = comment;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	@Column(name="created_at")
	@CreationTimestamp
	private Date created_at;
	@Column(name="updated_at")
	@UpdateTimestamp
	private Date updated_at;

	@Override
	public String toString() {
		return "Comment [id=" + id + ", name=" + name + ", email=" + email + ", comment=" + comment + ", post=" + post
				+ ", created_at=" + created_at + ", updated_at=" + updated_at + "]";
	}
}
