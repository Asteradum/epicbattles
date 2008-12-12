package graficos;

import gui.Principal;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

public class Imagen extends Canvas
{
	private static final long serialVersionUID = -3829851499198293693L;
	private Principal parent = null;
	private Image image = null;

	public Imagen(Principal parent)
	{
		super();
		this.parent = parent;
		this.setSize(new Dimension(80, 80));
		try
	    {
	      image = javax.imageio.ImageIO.read(new File("imagenes/BanzaiBot-icon.gif"));
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
