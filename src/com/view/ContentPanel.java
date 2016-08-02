package com.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;



public class ContentPanel extends JPanel {
	private JTextPane textArea;
	private String articleText;
	private JScrollPane pane;
	
	public ContentPanel() {
		textArea = new JTextPane();
		Dimension d = new Dimension(490, 700);
		setPreferredSize(d);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setLayout(new BorderLayout());
		
		pane = new JScrollPane(textArea);
		textArea.setEditable(false);
		textArea.setPreferredSize(d);
		add(pane);
		setBackground(Color.BLACK);
	}
	
	public void loadArticleText(final String  article) {
		//textArea.setText(article);
		articleText = article;
		textArea.setText(articleText);
	}
//	public void loadArticleHTML(final HTMLDocument  article) {
//			textArea.setDocument(article);
//	}
	
	public void loadArticleHTML(final URL  article) {
		try {
			textArea.setPage(article);
		} catch (IOException e) {
			e.printStackTrace();
		}
}
}
