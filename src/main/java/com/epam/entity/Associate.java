package com.epam.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "associates")
@Getter
@NoArgsConstructor
public class Associate {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private String gender;
	private String college;
	private String status;
	
	@ManyToOne
	Batch batch;
	
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setBatch(Batch batch) {
		this.batch = batch;
	}
	
}
