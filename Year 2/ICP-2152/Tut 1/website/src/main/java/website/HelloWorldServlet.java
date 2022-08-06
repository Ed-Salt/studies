package website;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class HelloWorldServlet
 */
public class HelloWorldServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public HelloWorldServlet() {
        // TODO Auto-generated constructor stub
    }
    
    // private int counter = 0;
    
    private static final Pattern ALLOWED_PATTEN = Pattern.compile("^[A-Za-z \\-']{2,50}$");
    private static final Pattern GREETINGS = Pattern.compile("^(?:Hello|Hi|Hey)$");

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sesh = request.getSession();
		if (sesh.getAttribute("counter") == null) {
			sesh.setAttribute("counter", 0);			
		}
		Integer counter = (Integer) sesh.getAttribute("counter");
		
		PrintWriter pw = response.getWriter();
		pw.append("<html><head><title>My First Servlet</title></head><body>");
		
		String sayHelloTo = request.getParameter("sayHelloTo");
		
		
		if (sayHelloTo != null) {
			Matcher m = ALLOWED_PATTEN.matcher(sayHelloTo);
			if (m.matches()) {
				String greeting = request.getParameter("greeting");
				if (greeting == null)
					greeting = "Hello";
				
				Matcher m1 = GREETINGS.matcher(greeting);
				if (!m1.matches()) 
					greeting = "Hello";
				
				
				response.getWriter().append("<h1>" + greeting + " " + sayHelloTo + "</h1>");
			} else {
				response.getWriter().append("<h1 style='color: red'>Type a name please!</h1>");
			}
		} else {
			response.getWriter().append("Served at: ").append(request.getContextPath());
			response.getWriter().append("<br />Current time: ").append(new Date().toString());
		}
		
		pw.append("<p>Current counter is: " + ++counter + "</p>");

		
		pw.append("</body></html>");
		
		sesh.setAttribute("counter", counter);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
