package com.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
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
	private List<NewsWebsite> theSites; 
	private JScrollPane pane;
	private JSplitPane split;
	
	public MainFrame() {
		myFrame = new JFrame();
		contentPanel = new ContentPanel();
		reader = new NewsReader();
		headlinesPanel = new HeadlinesPanel(reader);
		toolBar = createToolBar();
		
		myFrame.setSize(800, 700);
		myFrame.getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		pane = new JScrollPane();
		pane.setPreferredSize(new Dimension(300, 700));
		myFrame.getContentPane().add(pane, BorderLayout.WEST);
		
		myFrame.add(toolBar, BorderLayout.NORTH);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setUpHeadLinesPanel();
		myFrame.setVisible(true);
		headlinesPanel.displayHeadlines(getSite(sites.getSelectedItem().toString()));
	}
	
	private NewsWebsite getSite(final String siteName) {
		for (int i = 0; i< theSites.size(); i++) {
			if(theSites.get(i).getName().equalsIgnoreCase(siteName))
				return theSites.get(i);
		}
		return null;
	}

	private void setUpHeadLinesPanel() {
		///headlinesPanel.setSize(new Dimension(200, 900));

		JPanel borderlaoutpanel = new JPanel();
        pane.setViewportView(borderlaoutpanel);
        borderlaoutpanel.setLayout(new BorderLayout(0, 0));
        
        borderlaoutpanel.add(headlinesPanel, BorderLayout.NORTH);
        headlinesPanel.setLayout(new GridLayout(0, 1, 0, 1));
        headlinesPanel.setBackground(Color.WHITE);
	}
	
	private JToolBar createToolBar() {
		final JToolBar bar = new JToolBar();
		sites = new JComboBox<>();
		
		theSites = reader.getWebsites(); //gets all websites from database.
		//sites.addItem("All");
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
