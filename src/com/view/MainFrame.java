package com.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JToolBar;

import com.controller.NewsReader;
import com.model.objects.NewsWebsite;

public class MainFrame{
	
	private final JFrame myFrame;
	private final ContentPanel contentPanel;
	private final HeadlinesPanel headlinesPanel;
	private JToolBar toolBar;
	private NewsReader reader;
	private JComboBox<String> sites;
	
	public MainFrame() {
		myFrame = new JFrame();
		contentPanel = new ContentPanel();
		headlinesPanel = new HeadlinesPanel();
		reader = new NewsReader();
		toolBar = createToolBar();
		
		myFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		myFrame.add(headlinesPanel, BorderLayout.WEST);
		myFrame.add(contentPanel, BorderLayout.CENTER);
		myFrame.add(toolBar, BorderLayout.NORTH);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		myFrame.setVisible(true);
		headlinesPanel.displayHeadlines(sites.getSelectedItem().toString());
	}
	
	private JToolBar createToolBar() {
		final JToolBar bar = new JToolBar();
		sites = new JComboBox<>();
		
		List<NewsWebsite> theSites = reader.getWebsites(); //gets all websites from database.
		sites.addItem("All");
		for (final NewsWebsite n : theSites)
			sites.addItem(n.getName());
		
		
		bar.add(sites);
		bar.add(createPlayButton());
		
		return bar;
	}
	
	private JButton createPlayButton() {
		final ImageIcon icon = new ImageIcon("./play.png");
		final JButton button = new JButton(icon);
		button.setToolTipText("Read Article");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//read text out
			}	
		});
		return button;
	}
}
