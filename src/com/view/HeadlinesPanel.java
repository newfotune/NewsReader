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

/**
 * This class represents the left panel that displays the headlines.
 * @author Nwoke Fortune Chiemeziem
 * @version 1.0
 */
public class HeadlinesPanel extends JPanel {
	private List<Article> newsFeed;
	private NewsPuller puller;
	private NewsWebsite site;
	private final String STARTER_HTML = "<html><center>";
	private final String ENDING_HTML = "</center></html>";
	
	/**
	 * Initializes all the fields.
	 * @param reader the reader attached to the headlines panel.
	 */
	public HeadlinesPanel(final NewsReader reader) {
		newsFeed = new ArrayList<>();
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		puller = new NewsPuller(this);
		
		add(new Card(new Article(STARTER_HTML+"Pulling News..."+ENDING_HTML, null, null)));
		revalidate();
		repaint();
	}
	/**
	 * Starts another thread that pulls all the articles from the passed in site.
	 * @param site the news website to be pulled from.
	 */
	public void displayHeadlines(final NewsWebsite site) {
		this.site = site;
		puller.start();
	}
	
	/**
	 * Helper method to send the article that has been selected to be read.
	 * @param article the article that has been selected to be read.
	 */
	private void sendBackArticle(Article article) {
		firePropertyChange("article", null, article);
	}
	/**
	 * JPanel that represents a news article card.
	 * @author Nwoke Fortune Chiemeziem
	 * @version 1.0
	 */
	private class Card extends JPanel {
		private Article article;
		private JButton b;
		/**
		 * Initializes the fields.
		 * @param article the article to be displayed on the card.
		 */
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
		/**
		 * Creates and returns the button with the specified title.
		 * @param name the title of the article.
		 * @return the Button created.
		 */
		private JButton createButton(final String title) {
			final JButton button = new JButton(title);
				button.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						sendBackArticle(article);
					}
				});
				
			return button;
		}
	}
	/**
	 * News Puller thread to handle pulling the news articles and displaying it on the card. 
	 * @author Nwoke Fortune Chiemeziem
	 * @version 1.0
	 */
	private class NewsPuller extends Thread {

		private NewsPuller(HeadlinesPanel panel) {}
		@Override
		public void run() {
			newsFeed = DOMParser.getArticles(site);
			removeAll();
			for (final Article a : newsFeed) { //go through newsfeed of articles and create article.
				add(new Card(new Article(a.getTitle(), a.getPath(), a.getWebsite())));//add card to JPanel
			}
			revalidate();
			repaint();
		}
	}
}
