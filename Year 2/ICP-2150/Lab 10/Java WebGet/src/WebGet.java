import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
/**
  Demonstrates how to use a socket to communicate with a web server. Uses command-line parameters. 
 */

public class WebGet
{
	public static void main(String[] args) 
			throws IOException
		{
		  // Check and get command-line arguments      
			if (args.length != 2)
		  {
		     System.out.println("usage:java WebGet <host>  <resource>");
		     System.exit(0);
		  }
		  
		  String host = args[0];	// Get domain name
		  String resource = args[1];	// Get resource name
		  
		  	// Open socket to a Web Server
			final int HTTP_PORT = 80;
			Socket s = new Socket(host, HTTP_PORT);
		 
			// Get streams
			InputStream in = s.getInputStream( );
			OutputStream out = s.getOutputStream( );

			// Turn streams into readers and writers
			Scanner reader 
				= new Scanner(in);
			
			PrintWriter writer 
				= new PrintWriter(out);      
		 
			// Send HTTP command
			
			String command = "GET /" + resource + " HTTP/1.0\n\n";
			
			writer.print(command);
			writer.flush( );
			
			// Read and display server response
			// Terminate when end of file
			boolean stop = false;
			while (reader.hasNextLine())
			{
			   String input = reader.nextLine( );
			   if (input.contains("<!DOCTYPE")) {
				   stop = true;
			   }
			   if (!stop) {
				   System.out.println(input);
			   }
			}
	 
			// Always close the socket at the end
			s.close( ); 
	   	
		}
}
