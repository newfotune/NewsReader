package com.view;

import java.util.concurrent.ExecutionException;

import javax.swing.JPanel;
import javax.swing.SwingWorker;

import com.controller.NewsReader;
import com.model.objects.NewsWebsite;

public class NewsPuller extends SwingWorker<Long, Object>{
	
	private JPanel panel;
	private String site;
	
	protected NewsPuller(final JPanel panel, final String site) {
		this.panel = panel;
		this.site = site;
	}
	
	@Override
	protected Long doInBackground() throws Exception {
		NewsReader.pullArticle();
		return null;
	}
	
	@Override
	protected void done() {
//		try {
//			resultJLabel.setText(get().toString());
//		}catch (InterruptedException ex) {
//			resultJLabel.setText("Interrupted while waiting for results");
//		}catch (ExecutionException ex) {
//			resultJLabel.setText("Error encountered while performing calculatons. ");
//		}
	}
	
}
