package com.model.objects;

import java.net.URL;
import java.util.Date;
/**
 * Class representing a news article.
 * 
 * @author Nwoke Fortune Chiemeziem
 * @version 1.0
 */
public class Article {
	private String title;
	private URL path;
	private NewsWebsite website;
	private Date dateEntered;
	private String description;
	private boolean isBookmarked;

	/**
	 * Constructor that initializes an article.
	 * @param title the title of the Article
	 * @param website it parent website.
	 */
	public Article(final String title, final NewsWebsite website) {
		this(title, website.getUrl(), website);
	}
	/**
	 * Constructor that initializes an article.
	 * @param title the title of the Article
	 * @param website it parent website.
	 * @param path the url path.
	 */
	public Article(final String title, final URL path, final NewsWebsite website) {
		this.title = title;
		this.path = path;
		this.website = website;
	}
	/**
	 * Returns the description of the news article.
	 * @return the news article description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * Sets the description of the article.
	 * @param description the descrition of the article.
	 */
	public void setDescrption(final String description) {
		this.description = description;
	}
	/**
	 * return the URL of the article.
	 * @return the URL of the article.
	 */
	public URL getPath() {
		return path;
	}
	/**
	 * Sets the URL path of the article.
	 * @param path the URL path.
	 */
	public void setPath(final URL path) {
		this.path = path;
	}
	/**
	 * Returns the date the article was entered into the database.
	 * @return the date the article was entered into the database.
	 */
	public Date getDateEntered() {
		return dateEntered;
	}
	/**
	 * Sets the date the article was entered into the database.
	 * @param dateEntered the date the article was entered into the database.
	 */
	public void setDateEntered(final Date dateEntered) {
		this.dateEntered = dateEntered;
	}
	/**
	 * Returns the title of the article.
	 * @return the title of the article.
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * Returns the news website of the article.
	 * @return the News website of the article.
	 */
	public NewsWebsite getWebsite() {
		return website;
	}
	/**
	 * Returns the bookmark state of the article.
	 * @return true of this article has been bookmarked.
	 */
	public boolean isBookmarked() {
		return isBookmarked;
	}

	@Override
	public String toString() {
		return title;
	}
}
