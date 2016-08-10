package com.model.objects;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a NewsWebsite.
 * @author Nwoke Fortune Chiemezem.
 * @version 1.0.
 */
public class NewsWebsite {
	private String name;
	private URL url;
	private int rank;
	private int ID;
	private List<Subsection> subsections;
	
	/**
	 * Initializes the field variables.
	 * @param ID the unique ID of this website.
	 * @param name the name of this website.
	 * @param url this website's URL
	 * @param rank the rank of this website -- this rank value changes as the user visits each website
	 * 				More frequently visited websites have a higher rank.
	 */
	public NewsWebsite(final int ID, final String name, final URL url, final int rank) {
		this.name = name;
		this.url = url;
		this.rank = rank;
		this.ID = ID;
		subsections = new ArrayList<>();
	}
	/**
	 * This gets the subsections attached to this website.
	 * @return a list of the subections attached to this website.
	 */
	public List<Subsection> subsections() {
		return subsections;
	}
	/**
	 * This adds a subsection to the website.
	 * @param subsection the subsecton to be added to the website.
	 */
	public void addSubsection(final Subsection subsection) {
		subsections.add(subsection);
	}
	/**
	 * This gets the Unique ID of this website.
	 * @return the Unique ID of this website.
	 */
	public int getID() {
		return ID;
	}
	/**
	 * Returns the name of the website.
	 * @return the name of the website.
	 */
	public String getName() {
		return name;
	}
	/**
	 * Sets the name of the website.
	 * @param name the name of teh website.
	 */
	public void setName(final String name) {
		this.name = name;
	}
	/**
	 * Returns the URL of the website.
	 * @return the URL of the website.
	 */
	public URL getUrl() {
		return url;
	}
	/**
	 * Sets the URL of the website.
	 * @param url the URL of the website.
	 */
	public void setUrl(final URL url) {
		this.url = url;
	}
	/**
	 * Returns the Rank of the website.
	 * @return the Rank of the website.
	 */
	public int getRank() {
		return rank;
	}
	/**
	 * Sets the rank of the website.
	 * @param rank the rank of the website.
	 */
	public void setRank(final int rank) {
		this.rank = rank;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
