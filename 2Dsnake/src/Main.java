import java.awt.Color;

import javax.swing.JFrame;

public class Main {

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//create objects
		JFrame obj=new JFrame();//object from the JFrame class so that you can make the window
		Gameplay gameplay=new Gameplay();//object from the Gameplay class
		
		//Make J frame, the window by which the game works
		obj.setBounds(10, 10, 905, 700);//set size of window
		obj.setBackground(Color.DARK_GRAY);//background color
		obj.setResizable(false);//user cannot change size of the frame
		obj.setDefaultCloseOperation(obj.EXIT_ON_CLOSE);//window closes when you press x
		obj.add(gameplay);//add Gameplay object gameplay to the Jframe object obj
		obj.setVisible(true);//user can see window..its visible.....this should be at end of main, if not it doesnt work

	}

}
