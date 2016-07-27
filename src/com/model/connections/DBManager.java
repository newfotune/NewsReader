package com.model.connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.objects.NewsWebsite;
import com.model.objects.Subsection;

public class DBManager {
	private static Connection conn; 
	
	public DBManager() {
	}
	
	public static List<NewsWebsite> loadUpSubsections(final List<NewsWebsite> websites) {
		
		for (int i =0; i< websites.size(); i++) {
			websites.set(i, loadUpSubsections(websites.get(i)));
		}
		
		return websites;
	}
	
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
				Subsection sec = new Subsection(website, rs.getURL("URL"), rs.getString("topic"));
				website.addSubsection(sec);
			}
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return website;
	}
	
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
