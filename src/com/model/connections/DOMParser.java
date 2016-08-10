package com.model.connections;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.model.objects.Article;
import com.model.objects.NewsWebsite;
import com.model.objects.Subsection;

/**
 * Abstract class to Manage my DOM XML parsing.
 * @author Nwoke Fortune Chiemeziem
 * @version 1.0
 */
public abstract class DOMParser {

	private DOMParser() {
	}
	/**
	 * Parses the RSS feed of the passed in website, and returns a list of the articles.
	 * @param newsWebsite the news website to be parsed
	 * @return a list of article from said website.
	 */
	public static List<Article> getArticles(final NewsWebsite newsWebsite) {
		List<Article> articles = new ArrayList<>();
		List<Subsection> sections = newsWebsite.subsections();
		DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();

		try {
			for (int index = 0; index < sections.size(); index++) {

				//URL url = new URL("http://rss.cnn.com/rss/cnn_topstories.rss");
				URL url = sections.get(index).getUrl();
				URLConnection conn = url.openConnection();

				// DocumentBuilder docBuilder = fac.newDocumentBuilder();
				// Document doc = docBuilder.parse("./test.xml");
				Document doc = parseXML(conn.getInputStream());

				NodeList items = doc.getElementsByTagName("item");

				for (int i = 0; i < items.getLength(); i++) {
					Node item = items.item(i);
					if (item.getNodeType() == Node.ELEMENT_NODE) {
						NodeList infos = item.getChildNodes();
						String title, description, link;
						title = description = link = "";
						for (int j = 0; j < infos.getLength(); j++) {
							Node data = infos.item(j);
							if (data.getNodeType() == Node.ELEMENT_NODE) {
								Element e = (Element) data;
								String tagName = e.getTagName();
								if (tagName.equals("title"))
									title = e.getTextContent();
								else if (tagName.equals("description"))
									description = getDescription(e.getTextContent());
								else if(tagName.equals("link"))
									link = e.getTextContent();				
							}
						}
						Article a = new Article(title, new URL(link), newsWebsite);
						a.setDescrption(description);
						articles.add(a);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return articles;
	}
	/**
	 * Helper method that trims out the unnecessary text from teh actual 
	 * description and returns the description.
	 * @param description the dirty description.
	 * @return the cleaned out description.
	 */
	private static String getDescription(String description) {
		return description.substring(0, description.indexOf('.')+1);
	}
	/**
	 * Helper merthod that parses the stream and returns teh document.
	 * @param stream the stream to be parsed
	 * @return the document.
	 */
	private static Document parseXML(InputStream stream) {

		DocumentBuilderFactory objDocBuilderFac = null;
		DocumentBuilder objDocBuilder = null;
		Document doc = null;
		try {
			objDocBuilderFac = DocumentBuilderFactory.newInstance();
			objDocBuilder = objDocBuilderFac.newDocumentBuilder();

			doc = objDocBuilder.parse(stream);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return doc;
	}
}
