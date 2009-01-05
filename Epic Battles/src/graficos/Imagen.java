package graficos;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Imagen extends Canvas
{
	private static final long serialVersionUID = -3829851499198293693L;
	private Image image = null;

	public Imagen()
	{
		super();
		this.setSize(new Dimension(80, 80));
	}
	
	public void setImagen(Image i) throws Exception
	{	
		image=i; 
		this.paint(this.getGraphics());
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		
		if (image != null)
		{
			g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
		}
		else
		{
			g.setColor(Color.darkGray);
			g.drawRect(0, 0, 79, 79);
		}
	}
}
