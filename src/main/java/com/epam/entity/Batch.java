package com.epam.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "batches")
@Getter
@NoArgsConstructor
public class Batch {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String practice;
	private Date startDate;
	private Date endDate;
	
	@OneToMany(mappedBy = "batch",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Associate> associates = new ArrayList<>();

	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPractice(String practice) {
		this.practice = practice;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public void setAssociates(List<Associate> associates) {
		associates.forEach(associate->associate.setBatch(this));
		this.associates = associates;
	}
}
