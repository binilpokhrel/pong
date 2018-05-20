/**
 * @(#)PongGame.java
 *
 * PongGame application
 *
 * @Binil Pokhrel 
 * @version 1.00 2017/7/7
 */

import java.awt.*;
import javax.swing.*;
 
public class PongGame 
{
	public static final int CONTENT_WIDTH = 700, CONTENT_HEIGHT = 600;
    
    public static void main(String[] args)
    {
    	JFrame f = new JFrame("Pong Game");
    	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	Dimension d = new Dimension(CONTENT_WIDTH,CONTENT_HEIGHT);
    	f.getContentPane().setPreferredSize(d);
    	
    	Pong p = new Pong(f);
    	
    	f.add(p);
    	f.pack();
    	f.setLocationRelativeTo(null);
    	f.setVisible(true);
    	
    }
}
