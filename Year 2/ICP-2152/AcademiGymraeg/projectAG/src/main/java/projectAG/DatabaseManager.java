package projectAG;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class DatabaseManager {
	
	Connection conn;
	Statement st;
	private static String url;
    private static String username;
    private static String password;
    private List<String> nouns;

	
	public DatabaseManager() throws ClassNotFoundException, IOException, SQLException {
		// Initialise data source using properties file
		InputStream stream =
				DatabaseManager.class.getResourceAsStream("/database.properties");
		//start connection
		initialiseConnection(stream);
		conn = getConnection();
		st = conn.createStatement();
		//get nouns from database into list
		nouns = new LinkedList<String>();
		for (Noun nu : retrieveNouns(st)) {
			nouns.add(nu.toString());
		}
	}
	
	//starts connection from properties
	public static void initialiseConnection(InputStream stream) throws IOException, ClassNotFoundException {
		Properties props = new Properties();
	      props.load(stream);

	      String driver = props.getProperty("jdbc.driver");
	      url = props.getProperty("jdbc.url");
	      username = props.getProperty("jdbc.username");
	      password = props.getProperty("jdbc.password");

	      Class.forName(driver);
	}
	
	//returns connection parameters
	public static Connection getConnection() throws SQLException {
	      return DriverManager.getConnection(url, 
	         username, password);
	   }
	
	//gets nouns from sql and creates a list of noun objects
	//each noun has it's own object
	private static List<Noun> retrieveNouns(Statement stmt) {
		List<Noun> toReturn = new LinkedList<Noun>();
		try {
			ResultSet results = stmt.executeQuery("SELECT * FROM Nouns");
			while (results.next()) {
				Noun nu = new Noun(results.getString("noun"),
								   results.getString("translation"),
								   results.getString("gender"));
				toReturn.add(nu);
			}
		} catch (SQLException e) {
		}
		return toReturn;		
	}
	
	//new update function
	//as of writing, doesn't appear to function correctly
	public void updateEntry(String noun, String translation, String gender) throws SQLException {
		for (Noun nu : retrieveNouns(st)) {
			if (nu.getNoun().equals(noun)) {
				nu.setTranslation(translation);
				nu.setGender(gender);
				st.executeUpdate(nu.toUpdateSQL());
			}
		}
	}
	
	//Older functions for update/delete/insert
	//for reference only
	
	/*public void updateEntry(String table, String key, String entryName, String entry) throws SQLException {
		try
		{
			String command = "UPDATE " +table+ " SET "
					+ entryName + " = ";
			if (entryName.equals("credits")) { 
				command = command + Integer.parseInt(entry);
			} else {
				command = command + "\"" + entry + "\"";
			}
			command = command +" WHERE ";
			if (!table.equals("registration") && !table.equals("teaches"))  
				command = command + table;
			else if (table.equals("registration")) 
				command = command +"student";
			else if (table.equals("teaches"))
				command = command +"staff";
			command = command +"_id = \""+key+"\";";
			st.execute(command);
			//st.executeUpdate(sql);
		} finally {
			
		}
	}
	
	public void delFromTable(String table, String key) throws SQLException {
		try
		{
			String command = "DELETE FROM " +table+ " WHERE ";
			if (!table.equals("registration") && !table.equals("teaches")) 
				command = command + table;
			else if (table.equals("registration")) 
				command = command +"student";
			else if (table.equals("teaches"))
				command = command +"staff";
			command = command +"_id = \""+key+"\";";
			st.execute(command);
			//st.executeUpdate(sql);
		} finally {
			
		}
	}
	
	public void addToTable(String table, String par1, String par2, String par3) throws SQLException {
		try
		{
			String command = "INSERT INTO " + table;
			if (table.equals("student")) { 
				command = command +"("+table+"_id, "+table+"_name, degree_scheme)" + 
						"VALUE ('" +par1+ "', '" +par2+ "', '" +par3+ "');";
			} else if (table.equals("staff")){ 
				command = command +"("+table+"_id, "+table+"_name, staff_grade)" + 
						"VALUE ('" +par1+ "', '" +par2+ "', '" +par3+ "');";
			} else if (table.equals("module")) {
				command = command +"("+table+"_id, "+table+"_name, credits)" + 
						"VALUE ('" +par1+ "', '" +par2+ "', '" +Integer.parseInt(par3)+ "');";
			} else if (table.equals("registration")) {
				command = command +"(student_id, module_id)" + 
						"VALUE ('" +par1+ "', '" +par2+ "');";
			} else if (table.equals("teaches")) {
				command = command +"(staff_id, module_id)" + 
						"VALUE ('" +par1+ "', '" +par2+ "');";
			}
			st.execute(command);
			//st.executeUpdate(sql);
		} finally {
			
		}
	}*/
	
	//returns nouns as a list of strings
	public List<String> getTable() throws SQLException {
		return nouns;
	}
	
	//returns data for the specified noun
	public String[] getEntry(String noun) throws SQLException {
		for (int i = 0; i < nouns.size(); i++) {
			String[] toReturn  = nouns.get(i).split("; ");
			if (toReturn[0].equals(noun)) {
				return toReturn;
			}
		}
		return null;
	}
	
}
