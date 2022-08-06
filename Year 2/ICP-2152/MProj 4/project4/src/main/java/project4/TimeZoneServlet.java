package project4;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TimeZoneServlet
 */
public class TimeZoneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DateFormat formatter = DateFormat.getTimeInstance();
	private String city = null;
	private TimeZone zone;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TimeZoneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		pw.append("<html><head><title>I've got time</title></head><body>");
		
		//retrieve user's inputed city
		city = request.getParameter("city");
		if (city.equals("")) {
			//get host current time zone
			Calendar now = Calendar.getInstance();
			zone = now.getTimeZone();
		} else {
			//check if city exists
			String[] ids = TimeZone.getAvailableIDs();
			for(int i = 0; i < ids.length; i++) {
				String id = ids[i].substring(ids[i].indexOf('/') + 1).replace('_', ' ');
				if (id.toLowerCase().equals(city.toLowerCase())) {
					city = id;
					zone = TimeZone.getTimeZone(ids[i]);
					i = ids.length;
				} else {
					zone = null;
				}
			}
		}
		if (zone == null) {
			//if it doesn't exist, ask to try again
			response.getWriter().append("<h1>The city entered is invalid.</h1>");
			response.getWriter().append("Please go back.");
		} else {
			//if it does exist display the time in the city
			response.getWriter().append("<h1>The current time in " + city + " is:</h1>");
			formatter.setTimeZone(zone);
			Date time = new Date();
			String timeString = formatter.format(time);
			response.getWriter().append(timeString);
		}
		//back button to return to the time zone selector
		response.getWriter().append("<br/><br/>"
				+ "<form action=\"TimeZone.html\">"
				+ "<input type=\"submit\" value=\"Back\"/></form>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
