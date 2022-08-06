
public class CommandLineReader {

	public static void main(String[] args) {
		WeatherRSSParser parser = new DOMXPathWeatherRSSParserImpl();
		
		try {
			System.out.println(parser.getHeadlines());
		} catch (IllegalArgumentException e) {
			System.err.println("An error occurred, " + e.getMessage());
		}
	}

}
