package com.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.controller.NewsReader;
import com.model.connections.DOMParser;
import com.model.objects.Article;
import com.model.objects.NewsWebsite;


public class HeadlinesPanel extends JPanel {
	private List<Article> newsFeed;
	private NewsPuller puller;
	private NewsWebsite site;

	public HeadlinesPanel(final NewsReader reader) {
		newsFeed = new ArrayList<>();
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		puller = new NewsPuller(this);
		
		add(new Card(new Article("<html><center>Pulling News...</center></html>", null, null)));
	}

	public void displayHeadlines(final NewsWebsite site) {
		this.site = site;
		puller.start();
	}
	
	private class Card extends JPanel {
		private Article article;
		private JButton b = new JButton();
		
		private Card(final Article article) {
			this.setPreferredSize(new Dimension(50, 50));
			setLayout(new BorderLayout());
			setBorder(BorderFactory.createDashedBorder(Color.BLACK, 5,1));
			
			setBackground(Color.WHITE);
			b.setText(article.getTitle());
			add(b);
		}
	}
	
	private class NewsPuller extends Thread {
		HeadlinesPanel panel;
		private NewsPuller(HeadlinesPanel panel) {this.panel = panel;}
		@Override
		public void run() {
			newsFeed = DOMParser.getArticles(site);
			removeAll();
			for (Article a : newsFeed) {
				System.out.println(a);
				add(new Card(new Article("<html><center>"+a.getTitle()+"</center></html>", a.getPath(), a.getWebsite())));
			}
			revalidate();
			repaint();
		}
	}
}
