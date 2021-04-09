package com.hxzy.boot.bean;

import java.io.Serializable;

public class SolrBean implements Serializable{

	private static final long serialVersionUID = -2481758691488362932L;

	private String id;
	
	private String type;
	
	private String title;
	
	private String context;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	@Override
	public String toString() {
		return "SolrBean [id=" + id + ", type=" + type + ", title=" + title
				+ ", context=" + context + "]";
	}

}
