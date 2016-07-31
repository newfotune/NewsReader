package com.model.objects;

import java.net.URL;
import java.util.Date;

public class Article {
	private String title;
	private URL path;
	private NewsWebsite website;
	private Date dateEntered;
	private String description;
	private boolean isBookmarked;

	public Article(final String title,final NewsWebsite website) {
			this(title, website.getUrl(), website);
	}

	public Article(final String title, final URL path,
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
	
	public URL getPath() {
		return path;
	}

	public void setPath(final URL path) {
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
