package com.controller;

import java.util.ArrayList;
import java.util.List;

import com.model.connections.DBManager;
import com.model.objects.NewsWebsite;
import com.model.objects.Subsection;

public class NewsReader {
	private List<NewsWebsite> websites;
	private boolean sitesPulled = false;
	
	public NewsReader() {
		websites = getWebsitesFromDB();
		sitesPulled = true;
		loadSubsectionsFromDB(websites);
		
		for (int i = 0; i< websites.size(); i++) {
			NewsWebsite site = websites.get(i);
			System.out.print(site+"  ");
			for (int j =0; j < site.subsections().size(); j++) {
				System.out.println(site.subsections().get(j));
			}
		}
	}
	
	public List<NewsWebsite> getWebsites() {
		if (!sitesPulled){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return websites;
	}
	
	public static synchronized void pullArticle() {
		
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
