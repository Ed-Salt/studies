import java.util.Date;
import java.util.List;

public interface NewsRSSParser {

	String getHeadlines() throws IllegalArgumentException;
	
	List<Date> getStoryDates();
	
}
