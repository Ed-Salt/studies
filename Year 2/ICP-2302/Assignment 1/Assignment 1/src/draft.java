import java.util.Scanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class draft {

     public static void main(String []args) throws MalformedURLException, IOException {
        String[] userPos = getPosition();
        System.out.println("Postcode: " + userPos[0]);
        System.out.println("Latitude: " + userPos[1]);
        System.out.println("Longitude: " + userPos[2]);

        String[] gpPos = getPosition();
        System.out.println("Postcode: " + gpPos[0]);
        System.out.println("Latitude: " + gpPos[1]);
        System.out.println("Longitude :" + gpPos[2]);

        double distance = calculateDifference(
        		Double.parseDouble(userPos[1]),
        		Double.parseDouble(userPos[2]),
        		Double.parseDouble(gpPos[1]),
        		Double.parseDouble(gpPos[2]));
        System.out.println("\nDistance: " + (distance) + " meters");
     }

	private static double calculateDifference(double lat1, double lon1, double lat2, double lon2) {
		double R = 6371e3; 
		double rLat1 = Math.toRadians(lat1);
		double rLat2 = Math.toRadians(lat2);
		double dLat = Math.toRadians(lat2-lat1);
		double dLon = Math.toRadians(lon2-lon1);

		double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
		        Math.cos(rLat1) * Math.cos(rLat2) *
		        Math.sin(dLon/2) * Math.sin(dLon/2);
		
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

		double d = R * c;
		
		return d;
	}

	public static String[] getPosition() {
		 
         boolean valid = false;
         String[] position = new String[3];
         while (!valid) {
        	valid = true;
        	System.out.print("\nInput postcode (AA11 1AA) > ");
         	Scanner in = new Scanner(System.in);
         	String pCode = in.nextLine().toUpperCase();
         
         	BufferedReader br = null;
    	 	try 
         	{
        	 	pCode = pCode.replace(" ", "%20");
             	URL url = new URL("http://sharedweb.cs.bangor.ac.uk/postcodeapi/?postcode=" + pCode);
             	br = new BufferedReader(new InputStreamReader(url.openStream()));

             	String line;

             	while ((line = br.readLine()) != null) 
             	{
            	 	position[0] = line;
             	}
             	br.close();

             	position[0] = position[0].split(":\"", 2)[1];
             	position[1] = position[0].split(":\"", 2)[1];
             	position[2] = position[1].split(":\"", 2)[1];
             	position[0] = position[0].split("\"", 2)[0];
             	position[1] = position[1].split("\",", 2)[0];
             	position[2] = position[2].split("\"}", 2)[0];

         	} 
         	catch (MalformedURLException e) {
        	 	System.out.println("Invalid postcode");
        	 	valid = false;
         	} 
         	catch (IOException e) {
        	 	System.out.println("Invalid postcode");
        	 	valid = false;
         	}
         }
		return position; 
     }

}