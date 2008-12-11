package graficos;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class Imagen extends Canvas
{
	private static final long serialVersionUID = -3829851499198293693L;
	private JComponent parent = null;
	private Image image = null;

	public Imagen(JComponent parent)
	{
		this.parent = parent;
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
