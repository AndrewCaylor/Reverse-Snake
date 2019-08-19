import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.text.*;

public class Movecursortest
{
	public static void writehs (String towrite)
	{
    	String fileName = "/Users/Drew/Desktop/test1.txt";

    	try {
            FileWriter fileWriter =
                new FileWriter(fileName);
            
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);
            
            bufferedWriter.write(towrite);
            bufferedWriter.close();
        }
        catch(IOException ex){}
	}
	
	public static ArrayList<String> readhs ()
	{
		String line = null;
		
		ArrayList<String> toreturn = new ArrayList<String>();
		
		try 
		{
			FileReader fileReader = 
                new FileReader("/Users/Drew/Desktop/test1.txt");

            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) 
            {
                toreturn.add(line);
            }   

            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {}
        catch(IOException ex) {}
		
		System.out.println(toreturn);
		
		return toreturn;
	}
	
	public static String arraytogui(String [][] array)
	{
		String toreturn = "";
		
		for (int row = 0; row < guiarraylen; row ++)
		{
			String toadd = "";
			for (int col = 0; col < guiarraylen; col ++)
			{
				toadd = toadd + array[col][row];
			}
			toreturn = toreturn + toadd + "<br>";
		}
		return "<html><p>" + toreturn + "</html></p>";
	}

	public static String keypressed = "";
	
	public static boolean play = false;
	
	public static int [][] tailarray;
	
	public static String [][] guiarray;
	
	public static int headlocationx = 0;
	
	public static int headlocationy = 0;
	
	public static int score = 0;

	public static int tarraylen = 3;
	
	public static int sleeptime = 50;
	
	public static int guiarraylen = 40;
	
	public static int powerupx = 25;
	
	public static int powerupy = 25;
	
	public static String [][] poweruparray = new String[3][3];

	public static boolean bigwin = false;
	
	public static boolean medwin = false;
	
	public static int headlocationincrementx = 1;
	
	public static int headlocationincrementy = 0;
	
	public static int spacesmoved = 0;
	
	public static boolean testloose(int headlocationx, int headlocationy, String [][] guiarray, String teststring)
	{
		if (guiarray[headlocationx][headlocationy] == teststring)
		{
			return true;
		}
		return false;
	}
	
	public static void changedirection ()
	{
		
		if (keypressed.contains("D"))
		 {
			 if (headlocationincrementx == 1)
			 {
				 headlocationincrementx = 0;
				 headlocationincrementy = 1;
			 }
			 else if (headlocationincrementx == -1)
			 {
				 headlocationincrementx = 0;
				 headlocationincrementy = -1;
			 }
			 else if (headlocationincrementy == 1)
			 {
				 headlocationincrementx = -1;
				 headlocationincrementy = 0;
			 }
			 else if (headlocationincrementy == -1)
			 {
				 headlocationincrementx = 1;
				 headlocationincrementy = 0;
			 }
		 }
		 if (keypressed.contains("A"))
		 {
			 if (headlocationincrementx == 1)
			 {
				 headlocationincrementx = 0;
				 headlocationincrementy = -1;
			 }
			 else if (headlocationincrementx == -1)
			 {
				 headlocationincrementx = 0;
				 headlocationincrementy = 1;
			 }
			 else if (headlocationincrementy == 1)
			 {
				 headlocationincrementx = 1;
				 headlocationincrementy = 0;
			 }
			 else if (headlocationincrementy == -1)
			 {
				 headlocationincrementx = -1;
				 headlocationincrementy = 0;
			 }
		 }
		 
		 headlocationx += headlocationincrementx;
		 headlocationy += headlocationincrementy;
		 
		 keypressed = "";
	}

	public static void deincrementsleeptime ()
	{
		if (sleeptime >= 50)
		{
			if (spacesmoved % 5 ==  0) {sleeptime -= 1;}
		}
		if (sleeptime < 50 & sleeptime >= 40)
		{
			if (spacesmoved % 7 ==  0) {sleeptime -= 1;}
		}
		if (sleeptime < 40 & sleeptime >= 30)
		{
			if (spacesmoved % 10 ==  0) {sleeptime -= 1;}
		}
		if (sleeptime < 30 & sleeptime > 20)
		{
			if (spacesmoved % 15 ==  0) {sleeptime -= 1;}
		}
	}
	
	public static void replacetailarray ()
	{
		 int [][] newtailarray = new int [2][tarraylen];
		 
		 for (int set = 0; set < 2; set ++)
		 {
			 for( int data = tarraylen - 2; data > 0; data --)
			 {
				 newtailarray [set][data] = tailarray[set][data];
			 }
		 }
		 tailarray = newtailarray;
		 tailarray[0][0] = headlocationx;
		 tailarray[1][0] = headlocationy;
	}
	
	public static void newPU ()
	{
		Random rand = new Random();
		
		int breakif = 0;

		powerupx = rand.nextInt(guiarraylen - 2) + 1;
		powerupy = rand.nextInt(guiarraylen - 2) + 1;
		
		while (breakif < 9)
		{
			powerupx = rand.nextInt(guiarraylen - 2) + 1;
			powerupy = rand.nextInt(guiarraylen - 2) + 1;

			for (int x = 0; x <= 2; x ++)
			{
				for (int y = 0; y <= 2; y ++)
				{
					if (guiarray[powerupx + x - 1][powerupy + y - 1] == " ~ ")
					{
						breakif += 1;
					}
				}
			}
		}
	}
	
	public static void main (String [] args) throws Exception
	{		
		readhs();
		JFrame frame = new JFrame("Input");

		Container contentPane = frame.getContentPane();
		KeyListener listener = new KeyListener() 
		
		{

		@Override

		public void keyPressed(KeyEvent event) 
		{
			getinfo(event);
		}

		public void getinfo (KeyEvent e) 
		{
			int code = e.getKeyCode();
			keypressed = KeyEvent.getKeyText(code);
		}
		
		@Override
		public void keyReleased(KeyEvent event) {}

		@Override
		public void keyTyped(KeyEvent event) {}
		};

		JTextField textField = new JTextField();

		textField.addKeyListener(listener);

		contentPane.add(textField, BorderLayout.NORTH);

		frame.pack();

		frame.setVisible(true);
		  		  
		guiarray = new String[guiarraylen][guiarraylen];
		tailarray = new int[2][tarraylen];
		
		for (int row = 0; row < guiarraylen; row ++)
		{
			for (int col = 0; col < guiarraylen; col ++)
			{
				guiarray[row][col] = " ~ ";
			}
		}
		
		guiarray[0][0] = " 0 ";

		Robot bot = new Robot();
		
		JFrame popup = new JFrame("R E V E R S E   S N A K E"); 
		popup.setLocationRelativeTo(null);
		 
		JLabel poplabel = new JLabel ( arraytogui(guiarray),SwingConstants.CENTER );
		popup.getContentPane().add(poplabel, BorderLayout.NORTH); 
		 		 
		JPanel retryinfo = new JPanel(new BorderLayout());
		retryinfo.setBounds(50, 50, 250, 50);
		popup.getContentPane().add(retryinfo);
		
		JLabel poptime = new JLabel ("Score: 0", SwingConstants.CENTER);
		retryinfo.add(poptime, BorderLayout.NORTH); 
		
		JButton button2 = new JButton("Info");
		button2.setBounds(50,50,100,30);
		retryinfo.add(button2, BorderLayout.CENTER);
		button2.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		   {
			  
		    	JFrame info = new JFrame("Info"); 
		    	JLabel textLabel = new JLabel( "<HTML> "
		    			+ "<h2>About: </h2><br> "
		    			+ "This is REVERSE SNAKE.<br> "
		    			+ "Your tail reduces in size when you eat 'food' and your tail increases in size every other space moved<br>"
		    			+ "The faster your snake goes, the faster the score increases<br>"
		    			+ "<br>"
		    			+ "<h2>How to play: </h2><br> "
		    			+ "1) <b> Press A to turn left, press D to turn right </b><br> "
		    			+ "2) Hitting your tail or wall causes the game to end <br>"
		    			+ "3) Running into the 3 by 3 square of #'s causes your tail to reduce size; this is the food. <br>"
		    			+ "4) The faster you go, the more the tail decreases in size when you eat food<br>"
		    			+ "5) Once you hit MAX speed, eating food reduces the tail length to 20<br>"
		    			,SwingConstants.CENTER);
		    	 info.getContentPane().add(textLabel, BorderLayout.CENTER); 
		    	 info.pack();
		    	 info.setLocationRelativeTo(null); 
		    	 info.setVisible(true); 
		   
		   }
		});
		
		JButton button1 = new JButton("Play");
		button1.setBounds(50,50,100,30);
		retryinfo.add(button1, BorderLayout.SOUTH);
		button1.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		   {
				 play = true;
				 tarraylen = 3;
				 keypressed = "";
				 sleeptime = 50;

				 PointerInfo MLI = MouseInfo.getPointerInfo();
				 Point ML = MLI.getLocation();
				 int MLx = (int) ML.getX();
				 int MLy = (int) ML.getY();
				 
				 
				for (int row = 0; row < guiarraylen; row ++)
					{
						for (int col = 0; col < guiarraylen; col ++)
						{
							guiarray[row][col] = " ~ ";
						}
					}
				 
					int [][] newtailarray = new int[2][tarraylen];
					
					tailarray = newtailarray;

					guiarray[0][0] = " 0 ";
					
					headlocationx = 0;
					headlocationy = 0;
					headlocationincrementx = 1;
					headlocationincrementy = 0;
					score = 0;
					spacesmoved = 0;
					
				 bot.mouseMove(10, 50);
				 bot.mousePress(InputEvent.BUTTON1_MASK);
				 bot.mouseRelease(InputEvent.BUTTON1_MASK);
				 bot.mouseMove(MLx, MLy);

				 button1.setText("Restart");
		   }
		});
		
		popup.pack();
		popup.setVisible(true);
		popup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		DecimalFormat df = new DecimalFormat("##.#");
				 
		
		 while (true)
		 {
			 
			 Thread.sleep(sleeptime);
			 
			 if (play == true)
			 {
				spacesmoved += 1;

				Double d = new Double(100 / (1.0 * sleeptime));
				score += d.intValue();
				 
			
				 deincrementsleeptime();

				 if (spacesmoved % 2 == 0)
				 {
					 tarraylen += 1;
					 replacetailarray();	
				 }
			 
				 changedirection();
				 
			 
				 for (int set = 0; set < 2; set ++)
				 {
					 for( int data = tarraylen - 1; data > 0; data --)
					 {
						 tailarray[set][data] = tailarray [set][data - 1];
					 }
				 }	
			tailarray[0][0] = headlocationx;
			tailarray[1][0] = headlocationy;
			 
			if (headlocationx < 0 || headlocationy < 0 || headlocationy > guiarraylen - 1 || headlocationx > guiarraylen - 1)
			{
				play = false;
			}
			else
			{	
				if (powerupx - headlocationx >= -1 & powerupx - headlocationx <= 1 & powerupy - headlocationy >= -1 & powerupy - headlocationy <= 1 )
				{
					bigwin = false;
					if (sleeptime >= 50)
					{
						sleeptime += 1;
					}
					if (sleeptime < 50 & sleeptime >= 40)
					{
						sleeptime += 1;
					}
					if (sleeptime < 40 & sleeptime >= 30)
					{
						sleeptime += 2;
					}	
					if (sleeptime < 30 & sleeptime > 20)
					{
						sleeptime += 5;
					}
					if (sleeptime == 20)
					{
						sleeptime = 50;
						bigwin = true;
					}
					
					score += tarraylen;
					
					for (int x = -1; x <= 1; x++)
					{
						for (int y = -1; y <= 1; y++)
						{
						guiarray [powerupx + x][powerupy + y] = " ~ ";
						}
					}		
					
					newPU();
										
					for (int data = 1; data < tarraylen ; data ++)
					{
						guiarray [tailarray[0][data]] [tailarray[1][data]] = " ~ ";	 
					}

					tarraylen -= 5 * d.intValue();

					if (bigwin == true)
					{
						tarraylen = guiarraylen;
					}
					
					replacetailarray();
				}	
					guiarray [tailarray[0][tarraylen - 1]] [tailarray[1][tarraylen - 1]] = " ~ ";	 
					guiarray [tailarray[0][0]] [tailarray[1][0]] = " 0 ";
					
					for (int data = 1; data < tarraylen - 1 ; data ++)
					{
						guiarray [tailarray[0][data]] [tailarray[1][data]] = " o ";	
					}
					
					for (int x = -1; x <= 1; x++)
					{
						for (int y = -1; y <= 1; y++)
						{
							guiarray [powerupx + x][powerupy + y] = " # ";
						}
					}		
				
					poplabel.setText(arraytogui(guiarray));
					
					if (sleeptime == 20)
					{
						poptime.setText("Score: "+ Integer.toString(score) + "   moves / s: MAX");
					}
					else
					{
						poptime.setText("Score: "+ Integer.toString(score) + "   moves / s: " + df.format(1000 / (1.0 * sleeptime)));
					}
					if (testloose(headlocationx, headlocationy, guiarray, " o ") == true)
					{
						play = false;
					}
				}
			}
		}
	}
}