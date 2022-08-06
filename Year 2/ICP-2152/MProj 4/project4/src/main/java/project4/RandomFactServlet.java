package project4;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedReader;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RandomFactServlet
 */
public class RandomFactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RandomFactServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesh = request.getSession();
		//if a counter hasn't been initialised, start one
		if (sesh.getAttribute("counter") == null) {
			sesh.setAttribute("counter", 0);			
		}
		Integer counter = (Integer) sesh.getAttribute("counter");
		
		PrintWriter pw = response.getWriter();
		pw.append("<html><head><title>FOTD</title></head><body>");
		
		response.getWriter().append("<h1>Fact of the Day</h1>");
		
		List<String> facts = new LinkedList<String>();
		//locate fact file
		String filename = "/WEB-INF/facts.txt";		
		ServletContext context = getServletContext();
		//read facts from the file
		InputStream is = context.getResourceAsStream(filename);
		if (is != null) {
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader reader = new BufferedReader(isr);
			String text;
			while ((text = reader.readLine()) != null) {
				facts.add(text);
			}
		}
		
		//randomly select a fact
		Random rand = new Random();
		int n = rand.nextInt(facts.size());
		
		response.getWriter().append(facts.get(n) + "<br/>");
		
		//display site hits
		response.getWriter().append("<br/>Site hits = " + ++counter);

		
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
