import static org.junit.Assert.*;

import java.awt.List;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Test;

public class XMLParserTest {

	@Test
	public void testDOMParser() {		
		testParserClass(new DOMXMLParserImpl());
		
	}
	
	@Test
	public void testSAXParser() {		
		testParserClass(new SAXXMLParserImpl());
		
	}

	private void testParserClass(XMLParser domxmlParserImpl) {
		List<Employee> result = null;
		
		try {
		result = domxmlParserImpl.parse(null);
		fail("Should have thrown a IllegalArgumentException.");
		} catch (Throwable t) {
			assertTrue(t instanceof IllegalArgumentException);
		}
		
		ByteArrayInputStream bias = new ByteArrayInputStream(new byte[0]);
		try {
			result = domxmlParserImpl.parse(bias);
			assertNotNull(result);
			assertTrue(result.size() == 0);
		} catch (Throwable t) {
			fail("No exception should be thrown");
		}
		
		InputStream is = XMLParserTest.class.getResourceAsStream("/employees-test.xml");
		assertNotNull(is);
		result = null;
		try {
			result = domxmlParserImpl.parse(is);
			assertNotNull(result);
			assertTrue(result.size() == 3);
			
			assertTrue(result.get(0) instanceof Employee);
			
			Employee e = result.get(0);
			assertEquals("Lebron", e.getFirstName());
			assertEquals("1", e.getId());
			assertEquals("James", e.getLastName());
			assertEquals(30, e.getAge());
			assertEquals(2500.0, e.getSalary(), 0);
		} catch (Throwable t) {
			fail("No exception should be thrown");
		}
	}
	
}
