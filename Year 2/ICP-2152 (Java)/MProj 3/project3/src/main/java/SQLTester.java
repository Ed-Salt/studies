
import java.sql.*;
import java.io.*;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;


public class SQLTester {
	
		public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {

			
			// This is an amended version of Horstmann’s test program.
			//SimpleDataSource.init("/database.properties");
			InputStream stream =
					SQLTester.class.getResourceAsStream("/database.properties");
			SimpleDataSource.init(stream);
			Connection conn = SimpleDataSource.getConnection();

			Statement st = conn.createStatement();

			try {///*
				st.execute(
					"CREATE TABLE IF NOT EXISTS accounts (balance DECIMAL(5,2))");

				st.execute("INSERT INTO accounts VALUES (999.99)");
				st.execute("INSERT INTO accounts VALUES (666.66)");

				ResultSet rs = st.executeQuery("SELECT * FROM accounts");

				while(rs.next()) {
					System.out.println(rs.getString("balance"));
				}
				st.execute("DROP TABLE accounts");
				/*
				List<String> query = new LinkedList<String>();

				String filename = "/1-destroy.sql";		

				InputStream ins =
						SQLTester.class.getResourceAsStream(filename);
				if (ins != null) {
					InputStreamReader isr = new InputStreamReader(ins);
					BufferedReader reader = new BufferedReader(isr);
					String text;
					while ((text = reader.readLine()) != null) {
						query.add(text);
					}
				}
				
				st.execute("1");
				*/
			} finally {
				System.out.println("Table created and then dropped!");
				st.close();
				conn.close();
			}
		}
}
