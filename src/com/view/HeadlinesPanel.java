package com.view;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.model.objects.Article;

public class HeadlinesPanel extends JPanel{
	private List<SingleNews> newsFeed;
	private GridLayout grid = new GridLayout(10, 1);
	
	public HeadlinesPanel() {
		newsFeed = new ArrayList<>();
		this.setLayout(grid);
	}
	
	public void displayHeadlines(final String site) {
		NewsPuller puller = new NewsPuller(this, site);
		puller.execute();
	}
	

	private static class SingleNews extends JPanel {
		
		private Article article;
		
		private SingleNews(final Article article) {
			this.article = article;
		}
		
		@Override
		public void paintComponent(final Graphics graphics) {
			
		}
	}
}
