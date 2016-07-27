package com.model.objects;

import java.net.URL;
/**
 * Class Representing a site's news subsection.
 * @author Nwoke Fortune Chiemezeim
 * @version 1.0
 */
public class Subsection {
	private NewsWebsite website;
	private URL url;
	private String topic;
	
	/**
	 * Initialized the field variables.
	 * @param website the parent website of this subsection.
	 * @param url the url of this subsection.
	 * @param topic the topic of this subsection.
	 */
	public Subsection(final NewsWebsite website, final URL url, final String topic) {
		this.website = website;
		this.url = url;
		this.topic = topic;
	}
	/**
	 * Getter for the website of this subsection.
	 * @return the parent website of this subsection.
	 */
	public NewsWebsite getWebsite() {
		return website;
	}
	/**
	 * Gets the url of this subsection.
	 * @return the url of this subsection.
	 */
	public URL getUrl() {
		return url;
	}
	/**
	 * Gets the topic of this subsection.
	 * @return the topic of this subsection.
	 */
	public String getTopic() {
		return topic;
	}
	
	@Override
	public String toString() {
		return website.toString() +"/"+topic;
	}
}
