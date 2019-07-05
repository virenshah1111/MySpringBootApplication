/**
 * 
 */
package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

/**
 * @author virens
 *
 */
@Entity
@Table(name = "role")
public class Role {

	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Integer id;

	@Enumerated(EnumType.STRING)
	@NaturalId
	@Column(length = 60, nullable = false)
	private RoleName rolename;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RoleName getRolename() {
		return rolename;
	}

	public void setRolename(RoleName rolename) {
		this.rolename = rolename;
	}

}
