import java.awt.*;
import javax.swing.*;

import Surfaces.DrawingSurface;

import java.awt.event.*;


public class Menu extends JPanel implements ActionListener {

	
	Main w;
	private DrawingSurface panel;
	private JButton button;
	
	public Menu(Main w) {
		this.w = w;
		button = new JButton("Demo");
		button.addActionListener(this);
		add(button);
	}
	
	public void actionPerformed(ActionEvent e) {
		button.remove(button);	
	}
	
}
