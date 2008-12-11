package graficos;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.swing.JPanel;

public class Escenario extends Canvas
{
	private static final long serialVersionUID = -3141654143505676034L;
	private JPanel parent = null;
	private Image image = null;

	public Escenario(JPanel parent)
	{
		this.parent = parent;
		this.setSize(500, 500);
		
		try
	    {
	      image = javax.imageio.ImageIO.read(new File("BanzaiBot-icon.gif"));
	    }
	    catch (Exception e) { /*handled in paintComponent()*/ }
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g); 
	    if (image != null)
	    g.drawImage(image, 0,0,this.getWidth(),this.getHeight(),this);
	}
}
