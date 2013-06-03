import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JTextArea;
import javax.swing.*;
	
public class Driver
{
	private static JMenuBar mainMenuBar;
	private static JMenu menu1;
	private static JMenuItem rules,about;
	private static JFrame frame;
		
	public static void main(String[] args) throws Exception
	{
		
		frame = new JFrame("Connect 4");
		frame.setSize(350,450);
		frame.setLocation(0,0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new C4Panel());
		frame.setVisible(true);
		mainMenuBar = new JMenuBar();
		menu1 = new JMenu("Info");
		menu1.setMnemonic(KeyEvent.VK_I);
		mainMenuBar.add(menu1);	
		rules = new JMenuItem("Rules", KeyEvent.VK_R);
		menu1.add(rules);		
		rules.addActionListener(new MCListener());
		
		about = new JMenuItem("About", KeyEvent.VK_A);
		menu1.add(about);		
		about.addActionListener(new ACListener());
				
		frame.setJMenuBar(mainMenuBar);
	}
	
private static class MCListener implements ActionListener
      {
          public void actionPerformed(ActionEvent e)
         {
				JOptionPane.showMessageDialog(frame, "<html><body width='300'><p>To win the game, the player must connect four markers together. To place a piece, click a green block. This will place a marker in its column. Four markers of the same color in a row wins the player the game. Upon completing a game, hit the clear board button to reset the board. A stripe will be added to denote each win. The color at the top of the screen indicates whose turn is next.</p></body></html>");

			}
		}		
		
		
private static class ACListener implements ActionListener
      {
          public void actionPerformed(ActionEvent e)
         {
				JOptionPane.showMessageDialog(frame, "<html><body width='300'><p>Created by Matthew Kaufer.</p></body></html>");

			}
		}			
		

	
}