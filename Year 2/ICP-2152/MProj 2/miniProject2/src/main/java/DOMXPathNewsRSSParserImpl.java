import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMXPathNewsRSSParserImpl implements NewsRSSParser {

	private static final String URL_TO_READ = "http://feed.bbc.co.uk/news/world/europe/rss.xml";
	private static final DocumentBuilderFactory FACTORY = DocumentBuilderFactory.newInstance();
	private DocumentBuilder builder;
	private static final XPathFactory PATH_FACTORY = XPathFactory.newInstance();
	private XPath path;
	
	public DOMXPathNewsRSSParserImpl() {
		try {
			builder = FACTORY.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new IllegalStateException("Unable to create DocumentBuilder.");
		}
		path = PATH_FACTORY.newXPath();
	}
	
	public String getHeadlines() throws IllegalArgumentException {
		try {
			Document document = builder.parse(new URL(URL_TO_READ).openStream());
			return path.evaluate("/rss/channel/item/title", document);
		} catch (Exception e) {
			throw new IllegalArgumentException("Unable to read from news site.");
		}
	}

	public List<Date> getStoryDates() {
		return null;
	}

}
