package com.model.objects;

import java.util.Date;

public class Article {
	private String title;
	private String path;
	private NewsWebsite website;
	private Date dateEntered;
	private String description;
	private boolean isBookmarked;

	public Article(final String title,final NewsWebsite website) {
			this(title, "./"+title, website);
	}

	public Article(final String title, final String path,
			final NewsWebsite website) {
		this.title = title;
		this.path = path;
		this.website = website;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescrption(final String description) {
		this.description = description;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(final String path) {
		this.path = path;
	}

	public Date getDateEntered() {
		return dateEntered;
	}

	public void setDateEntered(final Date dateEntered) {
		this.dateEntered = dateEntered;
	}

	public String getTitle() {
		return title;
	}

	public NewsWebsite getWebsite() {
		return website;
	}

	public boolean isBookmarked() {
		return isBookmarked;
	}
	
	@Override
	public String toString() {
		return title;
	}
}
