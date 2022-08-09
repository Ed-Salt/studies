import java.io.InputStream;
import java.util.List;

import javax.swing.text.Document;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMXMLParserImpl implements XMLParser {
	
	@Override
	public List<Employee> employeeList = parse(InputStream in) {
		
		
		NodeList nodeList = null;
		
		Node node = nodeList.item(i);
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element elem = (Element) node;
			
			String id = node.getAttributes().getNamedItem("ID").getNodeValue();
			String firstName = null, lastName = null;
			int age = 0;
			double salary = 0.0;
			
			
			
			Employee e = new Employee(age, firstName, lastName, id, salary);
		}
	}

}
