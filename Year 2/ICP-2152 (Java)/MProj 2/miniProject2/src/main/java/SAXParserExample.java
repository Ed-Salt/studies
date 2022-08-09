// Demonstration of a SAXParser - Simple API XML (SAX)

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import org.xml.sax.helpers.DefaultHandler;

public class SAXParserExample extends DefaultHandler{

	ArrayList<Employee> employees;
	
	// temporary storage of parser character data 
	private String pcdata;
	
	// temporary storage for employee details during parsing
	private Employee tempEmp;
	
	
	public SAXParserExample(){
		employees = new ArrayList<Employee>();
	}
	
	private void parseDocument() {
		
		//get a factory
		SAXParserFactory spf = SAXParserFactory.newInstance();
		try {
		
			//get a new instance of parser using a factory method
			SAXParser sp = spf.newSAXParser();
			
			//parse the file using the specified default handler
			sp.parse("employees.xml", this);
			
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch (IOException ie) {
			ie.printStackTrace();
		}
	}

	
	 // Iterate through the array list and print contents
	 private void printData(){
		
		System.out.println("No of Employees '" + employees.size() + "'.");
		
		Iterator<Employee> it = employees.iterator();
		while(it.hasNext()) {
			System.out.println(it.next().toString());
		}
	}
	

	// event handler which receives notification of the start of an XML element
	
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		//reset
		pcdata = "";
		if(qName.equalsIgnoreCase("Employee")) {
			//create a new instance of employee
			tempEmp = new Employee();
			// determine employee type from element attribute
			tempEmp.setType(attributes.getValue("type")); 
		}
	}
	
   // event handler to recieve notification of character data within tags
	public void characters(char[] ch, int start, int length) throws SAXException {
		
		pcdata = new String(ch,start,length); // store character data from element
	}
	
	// event handler which recieves notification of the end of an XML element
	
	public void endElement(String uri, String localName, String qName) throws SAXException {

		// determine element type
		
		if(qName.equalsIgnoreCase("Employee")) {
			//add an employee to the list
			employees.add(tempEmp);
		}
		// set appropriate instance variable in tempEmp
		else if (qName.equalsIgnoreCase("Name")) {
			tempEmp.setName(pcdata);
		}else if (qName.equalsIgnoreCase("Id")) {
			tempEmp.setId(Integer.parseInt(pcdata));
		}else if (qName.equalsIgnoreCase("Age")) {
			tempEmp.setAge(Integer.parseInt(pcdata));
		}
		
	}
	
	public static void main(String[] args){
		SAXParserExample spe = new SAXParserExample();
		spe.parseDocument();
		spe.printData();
	}
	
}
