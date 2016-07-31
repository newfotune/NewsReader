package com.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class ContentPanel extends JPanel {
	public ContentPanel() {
		setPreferredSize(new Dimension(490, 700));
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setBackground(Color.WHITE);
	}
	
}
