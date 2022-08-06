import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class SAXXMLParserImpl implements XMLParser {

	@Override
	public List<Employee> parse(InputStream in) {
		List<Employee> toReturn = new LinkedList<Employee>();
		
		try {
			SAXParserFactory parserFactory = SAXParserFactory.newInstance();
			SAXParser parser = parserFactory.newSAXParser();
			SAXEmployeeParser empParser = new SAXEmployeeParser();
			
			parser.parse(in, empParser);
			
			toReturn.addAll(empParser.getEmployees());
		} catch (Throwable t) {}
		
		return toReturn;
	}
}
