package com.likang.myutil.bean;

import java.util.Date;

/**
 *
 * @Author likang 2017年11月28日 下午4:29:00
 *
 */
public class Product {

	private Long id;
	private String name;
	private String description;
	private Date createTime;
	
	public Product() {
	}

	public Product(String name, String description, Date createTime) {
		super();
		this.name = name;
		this.description = description;
		this.createTime = createTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
}
