import java.util.LinkedList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXEmployeeParser extends DefaultHandler {

	private String firstName = null, lastName = null, id = null, text = null;
	private int age = 0;
	private double salary= 0.0;
	
	private List<Employee> employees = new LinkedList<Employee>();
	private Employee empl = null;
	
	@Override
	public void startDocument() throws SAXException {
		employees.clear();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) {
		switch (qName) {
		case  "Employee":
			firstName = null; lastName = null; age = 0; salary = 0.0;
			id = attributes.getValue("ID");
			break;
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (qName) {
		case "Employee":
			empl = new Employee(age, firstName, lastName, id, salary);
			employees.add(empl);
			break;
		case "FirstName":
			firstName = text;
		case "LastName":
			lastName = text;
		case "Age":
			try {
				age = Integer.parseInt(text);
			} catch (Throwable t) {}
			break;
		case "Salary":
			try {
				salary = Double.parseDouble(text);
			} catch (Throwable t) {}
			break;
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		text = String.copyValueOf(ch, start, length).trim();
	}
	
	public List<Employee> getEmployees() {
		return employees;
	}
	
}
