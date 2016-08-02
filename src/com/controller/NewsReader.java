package com.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLEditorKit;

import org.xml.sax.SAXException;

import com.model.connections.DBManager;
import com.model.objects.Article;
import com.model.objects.NewsWebsite;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.document.TextDocument;
import de.l3s.boilerpipe.extractors.ArticleExtractor;
import de.l3s.boilerpipe.sax.BoilerpipeSAXInput;
import de.l3s.boilerpipe.sax.HTMLDocument;
import de.l3s.boilerpipe.sax.HTMLFetcher;

public class NewsReader {
	private List<NewsWebsite> websites;
	private boolean sitesPulled = false;
	private static final String VOICE_NAME = "kevin";
	private final Voice voice;

	public NewsReader() {
		websites = getWebsitesFromDB();
		sitesPulled = true;
		loadSubsectionsFromDB(websites);
		voice = VoiceManager.getInstance().getVoice(VOICE_NAME);
		
		voice.allocate();
	}

	public List<NewsWebsite> getWebsites() {
		while (!sitesPulled) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return websites;
	}

	public String getArticleText(final Article article) {

		HTMLDocument htmlDoc = null;
		String articletext = "";
		try {
			htmlDoc = HTMLFetcher.fetch(article.getPath());
			final TextDocument doc = new BoilerpipeSAXInput(
					htmlDoc.toInputSource()).getTextDocument();
			articletext = ArticleExtractor.INSTANCE.getText(doc);
			
			int titleIndex = articletext.indexOf(article.getTitle());
			articletext = (titleIndex < 0) ? articletext : articletext
					.substring(titleIndex);// get from article to th end
		} catch (final IOException e) {
			e.printStackTrace();
		} catch (final BoilerpipeProcessingException e) {
			e.printStackTrace();
		} catch (final SAXException e) {
			e.printStackTrace();
		}
		return articletext;
	}
	
	public synchronized void read(final String article) {
		if (!voice.speak(article))
			voice.speak("Couldn't read the Document");
	}

	// public javax.swing.text.html.HTMLDocument getArticleHTML(final Article
	// article) {
	//
	// HTMLEditorKit kit = new HTMLEditorKit();
	// javax.swing.text.html.HTMLDocument doc =
	// (javax.swing.text.html.HTMLDocument) kit.createDefaultDocument();
	// doc.putProperty("IgnoreCharsetDirective", Boolean.TRUE);
	// Reader HTMLReader;
	// try {
	// HTMLReader = new
	// InputStreamReader(article.getPath().openConnection().getInputStream());
	// kit.read(HTMLReader, doc, 0);
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (BadLocationException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// return doc;
	// }

	/**
	 * Method called from the GUI to get all websites from database to be
	 * displayed on GUI.
	 * 
	 * @return a list of all news websites on database.
	 */
	private List<NewsWebsite> getWebsitesFromDB() {
		return DBManager.getWebsites();
	}

	private void loadSubsectionsFromDB(final List<NewsWebsite> website) {

		websites = DBManager.loadUpSubsections(website);
	}

}
