import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
  public static void main(String args[]) throws IOException 
  {
     Socket socket;
     System.out.println(
        "Establishing connection. Please wait ...");
     try
      {
         String server = args[0];
         int serverPort = Integer.parseInt(args[1]);
         
         socket = new Socket(server, serverPort);
         System.out.println(
            "Client socket created: " + socket);
         
	   		OutputStream out = socket.getOutputStream( );
	   		InputStream in = socket.getInputStream();
	   		
	   		// Turn streams into readers and writers
	   		Scanner read = new Scanner(System.in);
	   		
			PrintWriter writer 
				= new PrintWriter(out);  
			
			String input = "";
			while (!input.equals(".bye"))  {
				input = read.next();
				//System.out.println(input);
				writer.println(input);
			}
         
         socket.close();
      }      
      catch(IOException ioe) {}
      finally {  
    	  System.out.println("Connection closed...");
      }
   }
}
