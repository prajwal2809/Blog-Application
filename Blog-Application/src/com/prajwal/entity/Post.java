package com.prajwal.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="post")
public class Post {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="title")
	private String title;
	@Column(name="excerpt")
	private String excerpt;
	@Column(name="content")
	private String content;
	@Column(name="author")
	private String author;
	@Column(name="published_at")
	@CreationTimestamp
	private Date published_at;
	@Column(name="is_published")
	private boolean is_published;
	@Column(name="created_at")
	@CreationTimestamp
	private Date created_at;
	@Column(name="updated_at")
	@UpdateTimestamp
	private Date updated_at;


	@OneToMany(mappedBy="post",fetch = FetchType.EAGER,cascade= {CascadeType.ALL})
	private List<Comment> comments;
	
//    @ManyToOne
//    @JoinColumn(name="post_id")
//    private User user;
    
    @ManyToMany(fetch=FetchType.LAZY,cascade = CascadeType.ALL ) 
    @JoinTable(name = "post_tags", 
        joinColumns = @JoinColumn(name = "post_id"), 
        inverseJoinColumns = @JoinColumn(name = "tag_id") 
        )
    private List<Tags> tags;
	
	 
	public List<Tags> getTags() {
		return tags;
	}

	public void setTags(List<Tags> tags) {
		this.tags = tags;
	}

	public Post() {
		
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIs_published(boolean is_published) {
		this.is_published = is_published;
	}

	public Date getPublished_at() {
		return published_at;
	}

	public void setPublished_at(Date publishes_at) {
		this.published_at = publishes_at;
	}

	public Boolean getIs_published() {
		return is_published;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public void addTags(Tags tagNames) {
		if(tags==null) {
			tags=new ArrayList<>();
			tags.add(tagNames);
		}
//		if(tagsNames)
		tags.add(tagNames);
//		tags=tagNames;
	}
	
	public Post(String title, String excerpt, String content, String author, Date published_at , Boolean is_published, Date created_at, Date updated_at) {
		this.title = title;
		this.excerpt = excerpt;
		this.content = content;
		this.author = author;
		this.published_at=published_at;
		this.is_published=is_published;
		this.created_at=created_at;
		this.updated_at=updated_at;
		
	}
	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", excerpt=" + excerpt + ", content=" + content + ", author="
				+ author + ", published_at=" + published_at + ", is_published=" + is_published + ", created_at="
				+ created_at + ", updated_at=" + updated_at + "]";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getExcerpt() {
		return excerpt;
	}

	public void setExcerpt(String excerpt) {
		this.excerpt = excerpt;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}


}
