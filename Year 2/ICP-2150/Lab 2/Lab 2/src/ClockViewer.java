/*
	Java Clock Program - Lab 2
	Edward Salt - dns18cch

*/
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class ClockViewer
{
   public static void main(String[] args)   
   {
	   //construct and size the content frame
	   JFrame frame = new JFrame();
	   //frame.setSize(420, 450);
	   frame.getContentPane().setPreferredSize(new Dimension(400,400));;
	   frame.pack();
	   frame.setTitle("Clock");
	   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	   //construct and insert the clock
	   ClockComponent component = new ClockComponent();
	   frame.add(component);      
	   frame.setVisible(true);   
   }
}