package com.model.connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.objects.NewsWebsite;
import com.model.objects.Subsection;
/**
 * The main interation point to my database.
 * @author Nwoke Fortune Chiemeziem
 * @Version 1.0
 */
public class DBManager {
	private static Connection conn; 
	
	public DBManager() {
	}
	/**
	 * Loads up a list of  websites with the required subsections.
	 * @param websites the websites to be loaded up.
	 * @return the websites with all its subsections.
	 */
	public static List<NewsWebsite> loadUpSubsections(final List<NewsWebsite> websites) {
		
		for (int i =0; i< websites.size(); i++) {
			websites.set(i, loadUpSubsections(websites.get(i)));
		}
		
		return websites;
	}
	
	/**
	 * Takes a website and loads in all its subsections.
	 * @param website the website
	 * @return the websites loaded with its subsections.
	 */
	private static NewsWebsite loadUpSubsections(final NewsWebsite website) {
		conn = ConnectionManager.getInstance();
		PreparedStatement pStatement = null;
		String statement = "Select * from SUBSECTIONS where website_name = ?";
		ResultSet rs = null;
		
		try {
			pStatement = conn.prepareStatement(statement);
			pStatement.setString(1, website.getName());
			
			rs = pStatement.executeQuery();
			while (rs.next()) {
				Subsection sec = new Subsection(rs.getURL("URL"), rs.getString("topic"));
				website.addSubsection(sec);
			}
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return website;
	}
	
	/**
	 * Gets all the websites available in the database.
	 * @return a list of all the websites.
	 */
	public static List<NewsWebsite> getWebsites() {
		conn = ConnectionManager.getInstance();
		PreparedStatement selectWebsites = null;
		String stateMent = "Select * from NEWS_WEBSITE";
		ResultSet rs = null;
		
		List<NewsWebsite> websites = new ArrayList<>();
		try {
			selectWebsites = conn.prepareStatement(stateMent);
			rs = selectWebsites.executeQuery();
			
			while(rs.next()) {
				NewsWebsite site = new NewsWebsite(rs.getInt("website_id"),rs.getString("website_name"), rs.getURL("URL"), rs.getInt("rank"));
				websites.add(site);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return websites;
	}
}
