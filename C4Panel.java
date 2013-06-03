import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import java.io.*;
import java.applet.Applet;
import java.net.URL;
import javax.sound.sampled.*;


public class C4Panel extends JPanel
{
	private JLabel label,pop,gen;
	private URL mediaURL;
	private static Applet music;
	public static JLabel[][] life;
	private Clip clip;
	private boolean allow,isPaused,hasBorder,musicPlaying,playerOne,canMove;
	private final int r,c;
	public static int r2,c2;
	private int wins;
	private static AudioInputStream abc;
	private JButton clear,sound;
	
	public C4Panel(){

 		URL mediaURL = this.getClass().getResource("theme.wav");
 		musicPlaying = false;

		wins = 0;
		r = 20;
		c = 15;
		r2 = r;
		c2 = c;
		life = new JLabel[r][c];		
		setLayout(new BorderLayout());
		playerOne = true;
		canMove = true;

		

		JPanel buttonz = new JPanel();
		sound = new JButton("Toggle Sound");
		sound.addActionListener(new SListener());
		buttonz.add(sound);			
		clear = new JButton("Clear Board");
		clear.addActionListener(new CListener());
		buttonz.add(clear);					
		add(buttonz,BorderLayout.SOUTH);					

		
					
		allow = true;
		JPanel timing = new JPanel();
		timing.setLayout(new FlowLayout());
		

		
		JPanel lifecont = new JPanel();
				
		lifecont.setLayout(new GridLayout(r,c));
		for(int x = 0; x<life.length;x++)
			for(int y = 0; y<life[0].length;y++)
			{
				life[x][y] = new JLabel("");
	 			life[x][y].setFont(new Font("Serif",Font.BOLD,20));
 				life[x][y].setForeground(Color.blue);
				life[x][y].setOpaque(true);
				life[x][y].setBackground(Color.white);
				
 				lifecont.add(life[x][y]);				
				final JLabel cell = life[x][y];
				
				
				cell.addMouseListener(new MouseAdapter() {  
            public void mousePressed(MouseEvent me){ 

             } 
	        });  
			  
			  add(lifecont);
			}
			
		for(int x = 0; x<life.length;x++)
			for(int y = 0; y<life[0].length;y++)
			{
				
				if(x%2 == 1 && x > 5)
				life[x][y].setBackground(Color.blue);
				if(x == 6 && y % 2 == 1)
				{
				final int b = y;
				
				life[x][y].setBackground(Color.green);
				life[x][y].addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me){ 
					if(canMove)
					fillSlot(b);
					
             } 
	        }); 				
				}
				
			
			}
			
		for(int y = 0; y<life[0].length;y++)
			for(int x = 0; x<life.length;x++)
			{
				
				if(y%2 == 0 && x > 5)
				life[x][y].setBackground(Color.blue);
			
			}	
			for(int y = 0; y<life[0].length; y++)
			{
				life[2][y].setBackground(Color.gray);
			}				
			
			updateBar(!playerOne);	

			
						
			try{
        clip = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream(mediaURL);
			clip.open(ais);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
		  clip.stop();			
		  }
		  catch(Exception e){}
		  

			  
		
		setFocusable(true);
		
	}
private class SListener implements ActionListener
      {
          public void actionPerformed(ActionEvent e)
         {
  				if(musicPlaying)
  				clip.stop();
  				else{
 				clip.loop(-1);
 				clip.start();
 				}
 				musicPlaying = !musicPlaying;
			}
		}	
		
		
private class CListener implements ActionListener
      {
          public void actionPerformed(ActionEvent e)
         {
				clearBoard();

			}
		}		
		
		public void fillSlot(int a)
		{

			Color col = null;
			if(playerOne)
			col = Color.black;
			else
			col = Color.red;
			for(int x = life.length - 1; x>5;x--) 
			{
				if(life[x][a].getBackground() == Color.white)
				{
					life[x][a].setBackground(col);
					check4(playerOne);
					updateBar(playerOne);
					
					playerOne = !playerOne;
					return;
					
				
				}
			
			}
			System.out.println("No more room in this column!");
		}
		
		public void check4(boolean one)
		{
		int ct = 0;
		Color col = Color.red;
		String player = "Red";
		if(one)
		{
		col = Color.black;
		player = "Black";
		}

		for(int x = life.length - 2; x>5;x-=2) 
		for(int y = 1; y<life[0].length - 1;y+=2) 
		{
// 		if(life[x][y].getBackground() == col)
// 		life[x][y].setBackground(Color.green);
		ct = 0;
		for(int a = 0; a<=6; a+=2)
		{
		try{
		if(life[x - a][y + a].getBackground() == col)
		{
		ct++;
		}		
		}
		catch(IndexOutOfBoundsException ef)
		{
		ct = ct + 0;
		}
		}
		if(ct >= 4)
		{
		System.out.println("You win, " + player + "!");	
		canMove = false;
		updateWins(wins);
		return;
		}		
		
	
		
		ct = 0;
		for(int a = 0; a<=6; a+=2)
		{
		try{
		if(life[x - a][y - a].getBackground() == col)
		{
		ct++;
		}		
		}
		catch(IndexOutOfBoundsException ef)
		{
		ct = ct + 0;
		}
		}
		if(ct >= 4)
		{
		System.out.println("You win, " + player + "!");	
		canMove = false;
		updateWins(wins);
		return;
		}		

		ct = 0;
		for(int a = 0; a<=6; a+=2)
		{
		try{
		if(life[x + a][y].getBackground() == col)
		{
		ct++;
		}
		}
		catch(IndexOutOfBoundsException ef)
		{
		ct = ct + 0;
		}
		}
		if(ct >= 4)
		{
		System.out.println("You win, " + player + "!");	
		canMove = false;
		updateWins(wins);
		return;
		}		
				
		
		ct = 0;
		for(int a = 0; a<=6; a+=2)
		{
		try{
		if(life[x][y + a].getBackground() == col)
		{
		ct++;
		}
		}
		catch(IndexOutOfBoundsException ef)
		{
		ct = ct + 0;
		}
		}
		if(ct >= 4)
		{
		System.out.println("You win, " + player + "!");	
		canMove = false;
		updateWins(wins);
		return;
		
		}		
		}		
		

		
		
		}
		
		public void updateBar(boolean one)
		{
			Color col = Color.black;
			if(one)
			col = Color.red;
			for(int y = 0; y<life[0].length; y++)
			{
				life[0][y].setBackground(col);
				life[1][y].setBackground(col);
			}			
		}
		
		public void updateWins(int win)
		{
			Color col = Color.red;
			if(playerOne)
			col = Color.black;
			for(int x = 3; x<=5;x++)
			life[x][win].setBackground(col);
			wins++;
			wins = wins % c;
		
		}
		
		public void clearBoard()
		{
			for(int x = 8; x<life.length -1; x+=2)
			for(int y = 1; y<life[0].length - 1; y+=2)
			life[x][y].setBackground(Color.white);
			canMove = true;
		
		}
	
	
}
		
