package juego.graficos;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.swing.JPanel;

public class Escenario extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3141654143505676034L;
	private Image image = null;

	public Escenario() {
		// TODO Auto-generated constructor stub
		try
	    {
	      image = javax.imageio.ImageIO.read(new File("BanzaiBot-icon.gif"));
	    }
	    catch (Exception e) { /*handled in paintComponent()*/ }
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g); 
	    if (image != null)
	    g.drawImage(image, 0,0,this.getWidth(),this.getHeight(),this);
	}

}
