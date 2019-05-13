package com.bzahov.Entities;

import javax.persistence.*;

@MappedSuperclass
public abstract class BaseEntity {
	protected int id;
	protected String name;
	protected String info;

	public BaseEntity() {	}

	public BaseEntity(String name, String info) {
		this.name = name;
		this.info = info;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Basic
	@Column(name = "name", nullable = false, length = 255)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Basic
	@Column(name = "info", nullable = true, length = 255)
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}