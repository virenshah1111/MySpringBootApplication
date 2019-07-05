/**
 * 
 */
package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author virens
 *
 */

@Entity
@Table(name = "TODO")
public class ToDo {

	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Long id;

	@NotBlank
	@Size(max = 255)
	@Column(name = "name", nullable = false)
	private String name;

	@NotNull
	@Column(name = "completed", nullable = false)
	private boolean completed;

	@JsonIgnore
	@ManyToOne(optional = false)
	@JoinColumn(name = "username")
	private User user;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
