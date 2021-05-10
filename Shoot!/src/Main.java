import java.awt.*;

import javax.swing.*;

import Surfaces.DrawingSurface;
import Surfaces.Map1;
import processing.awt.PSurfaceAWT;
import processing.core.PApplet;

public class Main extends JFrame{
	/**
	 * executes the program
	 * 
	 * @param args argument for the main method
	 */
	public static void main(String[] args) {
		DrawingSurface drawing = new DrawingSurface();
		PApplet.runSketch(new String[]{"Demo"}, drawing);
		PSurfaceAWT surf = (PSurfaceAWT) drawing.getSurface();
		PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
		JFrame window = (JFrame)canvas.getFrame();

		window.setLocation(300,150);
		window.setSize(800, 800);
		window.setMinimumSize(new Dimension(100,100));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);

		window.setVisible(true);
		canvas.requestFocus();
	}
	
	
	
	/*
	private JPanel cardPanel;
	
	private Menu panel1;    
	private DrawingSurface panel2;
	
	public Main(String title) {
		super(title);
		setBounds(100, 100, 800, 600);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    cardPanel = new JPanel();
	    CardLayout cl = new CardLayout();
	    cardPanel.setLayout(cl);
	    
		panel1 = new Menu(this);    
	    panel2 = new DrawingSurface();
	    panel2.init();
	    
	    cardPanel.add(panel1,"1");
	    cardPanel.add(panel2,"2");
	    
	    add(cardPanel);
	
	    setVisible(true);
	    
	}

	public static void main(String[] args)
	{
		Main w = new Main("AP Animation Demo");
	}
  
	public void changePanel() {
		((CardLayout)cardPanel.getLayout()).next(cardPanel);
		panel2.requestFocus();
	}
	*/
  
}
