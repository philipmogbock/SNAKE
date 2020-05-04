import java.awt.Color;//import color class so you can set the colors when you are drawing in the paint method
import java.awt.Graphics;
import java.util.ArrayList;	
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;//package to allow us to use imported pictures

import javax.swing.JPanel;
import javax.swing.Timer;//built in class for movement (manages speed of snake)

//This is a typical snake game with a GUI. Uses Jpanel class and implements Key Listener and Action Listener Interfaces

public class Gameplay extends JPanel implements KeyListener, ActionListener{
//Declare fields
	
	private int[] snakeXPos=new int[750]; // array for x position of snake
	private int[] snakeYPos=new int[750]; //array for y position of snake
	
	
	//movement directions of the snake
	private boolean left=false;
	private boolean right=false;
	private boolean up=false;
	private boolean down=false;

	//face of the snake
	private ImageIcon rightFace;
	private ImageIcon upFace;
	private ImageIcon downFace;
	private ImageIcon leftFace;
	
	//body of snake
	private ImageIcon snakeBody;
	

	
	private int[] foodXPos= {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
	
	private int[] foodYPos= {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};
	
	

	//scores
	private int score=0;
	
	
	//enemy or snakefood
	private ImageIcon foodImage;
	
	//enemy will pop up randomly at any of the possible predetermined positions, so we need the random function
	private Random random= new Random();
	
	//number of random numbers generated??
	private int xPos=random.nextInt(34);
	private int yPos=random.nextInt(24);
	
	//default position of snake
	private int moves=0;
	
	//default length of snake
	private int lengthOfSnake=3;
	
	
	//speed and delay of snake
	
	private Timer timer; //create Timer object to use Timer class
	private int delay=100; //speed of snake
	
	

	private  ImageIcon titleImage;//built in java class ImageIcon paints icons from images...so you can put images in the game as long as they are listed as type  ImageIcon
	
	//create constructor
	
	Gameplay(){
		//set initial position of snake??
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer=new Timer(delay,this); //speed and context
		timer.start();
	}
	
	
	//paint is the built in method that draws everything on the panel, it takes in an object from the Graphics Class
	//you use the g object from the Graphics class to draw everything
	
	//sizes of borders/images and rectangle are predetermined throught trial and error
	public void paint(Graphics g) 
	{	
		//if game has just started, the default position of snake will be set to value in if statement
		//if the game has already started (aka moves!=0) then it wont check the if statement because we will increment the moves variable
		
		if (moves==0) {
			//
			snakeXPos[2]=50;
			snakeXPos[1]=75;
			snakeXPos[0]=100;
			
			snakeYPos[2]=100;
			snakeYPos[1]=100;
			snakeYPos[0]=100;
			
		}
	
		//draw title image border
		g.setColor(Color.white);//set color of the border surrounding the image
		g.drawRect(24, 10, 851, 55);//draw the rectangle within which the image will be
		
		
		
	
		//draw the title image
		
		titleImage=new ImageIcon("snaketitle.jpg");//instantiate titleImage so you can use functionality of the ImageIcon class
		//titleImage.paintIcon(c, g, x, y);
		titleImage.paintIcon(this, g, 25, 11);//paints the icon/image
	
	
	
		
		//draw border for the gameplay(border of the playing area)
		g.setColor(Color.white);//set color of border within which the playing area will be
		g.drawRect(24, 74, 851, 577); //set size of the border of the playing area
		
		
		//draw background for the gameplay (the actual playing area)
		g.setColor(Color.black); //set color of the background/playing area
		g.fillRect(25, 75, 850, 575);//fill in the playing area with a color...this is the actual inside of the rectangle and not just a border
		//width and height need to be multiples of 25 because that is the size of the circles in the snake (to fit)
		
		
		//draw the score and scoreboard
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Scores: "+ score, 780, 30);
		
		//draw the score of the final length of the snake
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Length: "+ lengthOfSnake, 780, 50);
		
		
		
		//instantiate snake image
		rightFace=new ImageIcon("rightface.png"); 	//instantiate snake image
		rightFace.paintIcon(this, g, snakeXPos[0], snakeYPos[0]); //store the snakes head position in the first index of the snakexlength and snakeylength array
		
		
		//loop to detect direction of the snake
		//first index of the lengthofsnake array is the head
		for (int a=0; a < lengthOfSnake;a++) {
			
			if(a==0 && right) //if right is true direction of snake is on the right side etc etc
			{
			
				rightFace=new ImageIcon("rightface.png"); 	//instantiate snake image
				rightFace.paintIcon(this, g, snakeXPos[a], snakeYPos[a]); //store the snakes head position in the first index of the snakexlength and snakeylength array
			}
		
			
			if(a==0 && left) //if left is true direction of snake is on the left side etc etc
			{
			
				leftFace=new ImageIcon("leftface.png"); 	//instantiate snake image
				leftFace.paintIcon(this, g, snakeXPos[a], snakeYPos[a]); //store the snakes head position in the first index of the snakexlength and snakeylength array
			}
			
			if(a==0 && down) //if down is true direction of snake is down
			{
			
				downFace=new ImageIcon("downface.png"); 	//instantiate snake image
				downFace.paintIcon(this, g, snakeXPos[a], snakeYPos[a]); //store the snakes head position in the first index of the snakexlength and snakeylength array
			}
			
			if(a==0 && up) //if up is true direction of snake is up
			{
			
				upFace=new ImageIcon("upface.png"); 	//instantiate snake image
				upFace.paintIcon(this, g, snakeXPos[a], snakeYPos[a]); //store the snakes head position in the first index of the snakexlength and snakeylength array
			}
			
			//IF a doesnt equal 0, head has been drawn and we need to draw the body
			
			if(a!=0) 
			{
			
				snakeBody=new ImageIcon("snakebody.png"); 	//instantiate snake body image
				snakeBody.paintIcon(this, g, snakeXPos[a], snakeYPos[a]); //store the snakes body position (each ball) in the a'th index of the snakexlength and snakeylength array
			}
		}
		
		//draw  food
		
		
		foodImage= new ImageIcon("food.png");

		foodImage.paintIcon(this, g, foodXPos[xPos], foodYPos[yPos]);
		
		
		//if the head of the snake colides with the food add 1 length to the snake
		if(foodXPos[xPos]==snakeXPos[0] && foodYPos[yPos]==snakeYPos[0]) {
			
			//increment the length of the snake
			lengthOfSnake++;
			
			//increment the score
			score++;
			
			//regenerate food in another location
			xPos=random.nextInt(34);
			yPos=random.nextInt(23);
			
			
		}
		
		
		
		
		g.dispose();
		
	}

//built in ActionListener and KeyListener methods
	
	@Override
	//this method is automatically called when the timer starts
	public void actionPerformed(ActionEvent e) {
		timer.start();
	
	if(right) {
		
//		loop through length of snake array aka each position/ball that makes the snake from tail to head
		for(int r=lengthOfSnake-1; r>=0;r--) {
			
			//shift the position of the head to the next index..so that the body follows the head...fills in position head just vacated
			snakeYPos[r+1]=snakeYPos[r];
		}
		
		for(int r=lengthOfSnake; r>=0;r--) {
			
			//not sure what this if does???
			if(r==0) {
				snakeXPos[r]=snakeXPos[r]+25;
			}
			
			else
			{
				snakeXPos[r]=snakeXPos[r-1];
			}	
			//if it goes through right wall, make it come out of left wall
			if(snakeXPos[r]>850)
			{
				snakeXPos[r]=25;
			}
			
		}
		repaint();//automatically calls paint method and runs everything in it
		
	}
		
		if(left) {
			
//			loop through length of snake array aka each position/ball that makes the snake
			for(int r=lengthOfSnake-1; r>=0;r--) {
				
				//shift the position of the head to the next index..so that the body follows the head...fills in position head just vacated
				snakeYPos[r+1]=snakeYPos[r];
			}
			
			for(int r=lengthOfSnake; r>=0;r--) {
				
				if(r==0) {
					//subtract 25 instead of adding like above
					snakeXPos[r]=snakeXPos[r]-25;
				}
				
				else
				{
					snakeXPos[r]=snakeXPos[r-1];
				}
				
				//opposite of the other one above...if it goes through left wall, make it come out of right wall
				if(snakeXPos[r]<25)
				{
					snakeXPos[r]=850;
				}
			}
			repaint();//automatically calls paint method and runs everything in it
			
		}
		
		if(up) {
//			loop from tail to head
			for(int r=lengthOfSnake-1; r>=0;r--) {

				//use the x length instead of the y length for the down motion
				snakeXPos[r+1]=snakeXPos[r];
			}
			
			for(int r=lengthOfSnake; r>=0;r--) {
				
				//use the y length here instead of the x....all the way down
				if(r==0) {
					snakeYPos[r]=snakeYPos[r]-25;
				}
				
				else
				{
					snakeYPos[r]=snakeYPos[r-1];
				}	
				//if its less than 75...make the position 625???
				if(snakeYPos[r]<75)
				{
					snakeYPos[r]=625;
				}
				
			}
			repaint();//automatically calls paint method and runs everything in it
			
		}
			
		
		
		if(down) {
			
			for(int r=lengthOfSnake-1; r>=0;r--) {

					//use the x length instead of the y length for the down motion
				snakeXPos[r+1]=snakeXPos[r];
			}
			
			for(int r=lengthOfSnake; r>=0;r--) {
				
				if(r==0) {
					//use the y length here instead of the x....all the way down
					
					snakeYPos[r]=snakeYPos[r]+25;
				}
				
				else
				{
					snakeYPos[r]=snakeYPos[r-1];
				}	
				
				//if its greater than 625...make the position 75???
				if(snakeYPos[r]>625)
				{
					snakeYPos[r]=75;
				}
				
			}
			repaint();//automatically calls paint method and runs everything in it
			
		}
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	//this method will allow us to detect the arrow keys on the keyboard that control snake
	//we will also make sure the snake cant crash into itself by not allowing it to move in the opposite direction of its current motion
	public void keyPressed(KeyEvent e) {
//		right arrow key is pressed
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			
			moves++;//increment moves so it is no longer in default position
			//since right key is pressed, the right variable is changed to true so the head faces that direction and the others are false so it doesnt face that direction
			right=true;
			
//			loop so that it doesnt crash into itself when moving in one direction and opposite key is pressed
			if(!left) {
				right=true;
			}
			else {
				right=false;
				left=true;
			}
			
			up=false;
			down=false;
			
		}
		
//		left arrow key is pressed
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			
			moves++;
			left=true;
			

			if(!right) {
				left=true;
			}
			else {
				left=false;
				right=true;
			}
			
			up=false;
			down=false;
			
		}
		
		
//		up arrow key is pressed
		if(e.getKeyCode()==KeyEvent.VK_UP) {
			
			moves++;
			up=true;
			

			if(!down) {
				up=true;
			}
			else {
				up=false;
				down=true;
			}
			
			right=false;
			left=false;
			
		}
		
//		down arrow key is pressed
		if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			
			moves++;
			down=true;
			

			if(!up) {
				down=true;
			}
			else {
				down=false;
				up=true;
			}
			
			right=false;
			left=false;
			
		}
		
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
