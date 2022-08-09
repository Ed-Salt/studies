import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DiceViewer {
	
	private static int roll1 = 6;
	private static int roll2 = 6;
	//private static JFrame frame;

	public static void main(String[] args)   
	   {
		   JFrame frame = new JFrame();
		   frame.setSize(720, 450);
		   frame.getContentPane().setSize(700, 400);
		   frame.setTitle("Dice");
		   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		   Dice die1 = null;
		   Dice die2 = null;
		   JButton btn = addBtn(frame, die1, die2);
		   addRadio(frame, btn);
		   DiceComponent component = new DiceComponent(roll1, roll2, die1, die2);
		   frame.add(component);  
		   frame.setVisible(true);   
	   }
	
	public static JButton addBtn(JFrame frame, Dice die1, Dice die2) {
		   JButton btnThrow = new JButton("Throw dice");
		   btnThrow.setSize(160, 50);
		   btnThrow.setLocation(360, 314);
		   //ActionListener listener = new ClickListener(frame);
		   ActionListener listener = (new ActionListener()
		   {
		     public void actionPerformed(ActionEvent e)
		     {
		    	System.out.println("Clicked Button!");
		    	int roll = DiceComponent.throwDice();
		    	System.out.println(roll); /*
		 		int roll1 = die1.throwDice();
		 		int roll2 = die2.throwDice();
		 		System.out.println(roll1 + roll2); */
		 		frame.repaint();
		     }
		   });		   		   
		   btnThrow.addActionListener(listener);
		   btnThrow.setEnabled(false);
		   frame.add(btnThrow);
		return btnThrow;
	}
	
	public static void addRadio(JFrame frame, JButton btn) {
		JRadioButton rbtUpper = new JRadioButton("High");
		JRadioButton rbtLower = new JRadioButton("Low");
		JRadioButton rbtSeven = new JRadioButton("Sevens");
		rbtUpper.setSize(80, 40);
		rbtLower.setSize(80, 40);
		rbtSeven.setSize(80, 40);
		rbtUpper.setLocation(250, 290);
		rbtLower.setLocation(250, 320);
		rbtSeven.setLocation(250, 350);
		ActionListener listener = (new ActionListener()
		   {
		     public void actionPerformed(ActionEvent e)
		     {
		    	System.out.println("Clicked Radio!");
		    	rbtUpper.setSelected(false);
		    	rbtLower.setSelected(false);
		    	rbtSeven.setSelected(false);
		    	((JRadioButton) e.getSource()).setSelected(true);;
			    btn.setEnabled(true);
		     }
		   });		   		   
		rbtUpper.addActionListener(listener);
		rbtLower.addActionListener(listener);
		rbtSeven.addActionListener(listener);
		frame.add(rbtUpper);
		frame.add(rbtLower);
		frame.add(rbtSeven);
		
	}
	/*
	private class Listener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
	    {  
			DiceComponent component = new DiceComponent(roll1, roll2);
			frame.add(component);  
			frame.setVisible(true);
	    }
	}
	*/
}
