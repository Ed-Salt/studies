package projectAG;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseManager db;
	private String act = "";
	private String noun = "";
	private String translation = "";
	private String gender = "";
	

    public EditorServlet() throws ClassNotFoundException, IOException, SQLException {
    	db = new DatabaseManager();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//contains the noun to edit or 'ADD' for creating a new noun
		act = request.getParameter("edit");
		PrintWriter pw = response.getWriter();
		pw.append("<html><head><title>Noun Editor</title>"
				+ "<style>"
				+ "form label {"
				+ "  display: inline-block;"
				+ "  width: 150px;"
				+ "}"
				+ "</style>"
				+ "</head><body>");
		pw.append("<form action=\"NounServlet\" method=\"post\">");
		boolean valid = true;
		
		if (act.equals("ADD")) {
			//layout for adding a new noun
			noun = "";
			translation = "";
			gender = "feminine";
			pw.append("<h1>Enter details for your new noun</h1>");
			pw.append("<label for=\"noun\">Welsh noun:</label>" +  
					"<input type=\"text\" name=\"noun\" value=\""+noun+"\"/><br /><br />");
		} else {
			//layout for editing an existing noun
			act = act.toLowerCase();
			try {
				String[] data = db.getEntry(act);
				if (data == null) {
					valid = false;
				} else {
					noun = data[0];
					translation = data[1];
					gender = data[2];
					pw.append("<h1>Edit details of '"+act+"'</h1>");
					//hidden element used to pass noun value for editing and updating when 
					//returned to NounServlet
					pw.append("<input type=\"text\" name=\"noun\" value=\""+noun+"\" hidden/>");
					
					pw.append("<label for=\"welsh\">Welsh noun:</label>" +  
							"<label name=\"welsh\" value=\""+noun+"\">"+noun+"</label><br /><br />");
					
				}				
			} catch (SQLException e) {
				valid = false;
			}
		}
		if (valid) {
			//if the noun passed through exists in the database or isn't already 'ADD'
			pw.append("<label for=\"translation\">English translation:</label>" +  
					"<input type=\"text\" name=\"translation\" value=\""+translation+"\"/><br /><br />");
			
			pw.append("<label for=\"gender\">Gender:</label>" +  
					"<select name=\"gender\" style=\"width:200px;\">" +
					"  <option selected value=\"" +gender+ "\">"+gender+"</option>");
			
			if (gender.equals("feminine")) 
				pw.append("  <option value=\"masculine\">masculine</option>");
			else
				pw.append("  <option value=\"feminine\">feminine</option>");
				pw.append("</select>");		
			
			if (act.equals("ADD")) {
				//changed action value for adding a new noun
				pw.append("<br/><br/>" +
					"<button name=\"action\" " +
					"  type=\"submit\" " +
					"  value=\"add\">Save" +
					"</button></form>");
			
			} else {
				//changed action value for Save and also a delete option for editing current nouns
				pw.append("<br/><br/>" +
						"<button name=\"action\" " +
						"  type=\"submit\" " +
						"  value=\"update\">Save" +
						"</button>");
				
				pw.append("<br/><br/>" +
					"<button name=\"action\" " +
					"  type=\"submit\" " +
					"  value=\"delete\">Delete" +
					"</button></form>");
			}			
		} else {
			//if the noun passed doesn't exist in the database, provides option to add a new noun
			pw.append("<h1>Sorry, we couldn't find '"+act+"'</h1>");
			pw.append("Did you want to add a new noun?");
			pw.append("</form>");
			pw.append("<br/>"
					+ "<form action=\"EditorServlet\" method=\"get\">"
					+ "<button name=\"edit\" "
					+ "  type=\"submit\" "
					+ "  value=\"ADD\">Add"
					+ "</button></form>");
		}
		
		//returns to NounServlet without saving any changes
		pw.append("<br/>"
				+ "<form action=\"NounServlet\" method=\"get\">"
				+ "<input type=\"submit\" value=\"Cancel\"/></form>");

		pw.append("</body></html>");
	}
	
	//unused
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
