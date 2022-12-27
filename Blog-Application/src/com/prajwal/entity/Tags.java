package com.prajwal.entity;

import java.sql.Timestamp;
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
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
@Entity
@Table(name="tags")
public class Tags {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="created_at")
	@CreationTimestamp
	private Timestamp  created_at;
	@Column(name="updated_at")
	@CreationTimestamp
	private Timestamp  updated_at;
	
    @ManyToMany(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(
            name = "post_tags", 
            joinColumns = @JoinColumn(name = "tag_id") , 
            inverseJoinColumns =  @JoinColumn(name = "post_id")
            )
    private List<Post> posts;
	
    
    public void addPost(Post postList) {
		if(posts==null) {
			posts=new ArrayList<>();
			
			posts.add(postList);
		}
//		if(tagsNames)
		posts.add(postList);
    }
	public String getName() {
		return name;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Tags() {
		
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp  getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp  created_at) {
		this.created_at = created_at;
	}
	public Timestamp  getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Timestamp  updated_at) {
		this.updated_at = updated_at;
	}
	public Tags(String name, Timestamp  created_at, Timestamp  updated_at) {
		this.name = name;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	@Override
	public String toString() {
		return "Tags [id=" + id + ", name=" + name + ", created_at=" + created_at + ", updated_at=" + updated_at + "]";
	}
	
}
