package project4;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class OrderFormServlet
 */
public class OrderFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter pw = response.getWriter();
		pw.append("<html><head><title>Order Form</title></head><body>");
				
		
		response.getWriter().append("<h1>Testing doPost...</h1>");
		//retrieve description
		String description = request.getParameter("desc");
		response.getWriter().append("descrption = " + description);
		//retrieve product code
		String prodCode = request.getParameter("code");
		response.getWriter().append("<br/>productCode = " + prodCode);
		//retrieve quantity
		String quantity = request.getParameter("quant");
		response.getWriter().append("<br/>quantity = " + quantity);
		//retrieve delivery method
		String delivery = request.getParameter("deliv");
		response.getWriter().append("<br/>deliveryMode = " + delivery);
		//retrieve customer's id
		String customerId = request.getParameter("cid");
		response.getWriter().append("<br/>customerNumber = " + customerId);
				
		pw.append("</body></html>");	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
