package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.model.connections.DBManager;
import com.model.connections.DOMParser;
import com.model.objects.Article;
import com.model.objects.NewsWebsite;

public class NewsReader {
	private List<NewsWebsite> websites;
	private boolean sitesPulled = false;
	
	public NewsReader() {
		websites = getWebsitesFromDB();
		sitesPulled = true;
		loadSubsectionsFromDB(websites);
	}
	
	public List<NewsWebsite> getWebsites() {
		while (!sitesPulled){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return websites;
	}
	/**
	 * Method called from the GUI to get all websites from database to be displayed on GUI.
	 * @return a list of all news websites on database.
	 */
	private List<NewsWebsite> getWebsitesFromDB() {
		return DBManager.getWebsites();
	}
	
	private void loadSubsectionsFromDB(final List<NewsWebsite> website) {
		
		websites = DBManager.loadUpSubsections(website);
	}
	
}
