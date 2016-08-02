package com.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import listeners.ButtonListeners;

import com.controller.NewsReader;
import com.model.connections.DOMParser;
import com.model.objects.Article;
import com.model.objects.NewsWebsite;


public class HeadlinesPanel extends JPanel {
	private List<Article> newsFeed;
	private NewsPuller puller;
	private NewsWebsite site;
	private final String STARTER_HTML = "<html><center>";
	private final String ENDING_HTML = "</center></html>";
	
	public HeadlinesPanel(final NewsReader reader) {
		newsFeed = new ArrayList<>();
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		puller = new NewsPuller(this);
		
		add(new Card(new Article(STARTER_HTML+"Pulling News..."+ENDING_HTML, null, null)));
	}

	public void displayHeadlines(final NewsWebsite site) {
		this.site = site;
		puller.start();
	}
	
	private void sendBackArticle(Article article) {
		firePropertyChange("article", null, article);
	}
	
	private class Card extends JPanel {
		private Article article;
		private JButton b;// = new JButton();
		
		private Card(final Article article) {
			this.article = article;
			this.setPreferredSize(new Dimension(50, 50));
			setLayout(new BorderLayout());
			setBorder(BorderFactory.createDashedBorder(Color.BLACK, 5,1));
			
			b = createButton(article.getTitle());
			addMouseListener(new ButtonListeners(b));
			
			setBackground(Color.WHITE);
			b.setText(STARTER_HTML+article.getTitle()+ENDING_HTML);
			add(b);
		}
		
		private JButton createButton(String name) {
			final JButton button = new JButton(name);
				button.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						sendBackArticle(article);
					}
				});
				
			return button;
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
				add(new Card(new Article(a.getTitle(), a.getPath(), a.getWebsite())));
			}
			revalidate();
			repaint();
		}
	}
}
