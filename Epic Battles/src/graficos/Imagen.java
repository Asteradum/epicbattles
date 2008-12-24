package graficos;

import gui.Fondo;
import gui.Principal;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

public class Imagen extends Canvas
{
	private static final long serialVersionUID = -3829851499198293693L;
	private Image image = null;

	public Imagen()
	{
		super();
		this.setSize(new Dimension(80, 80));
		image = Fondo.cargar(Fondo.Pantalla.Escenario);
	}	
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g); 
	    if (image != null)
	    g.drawImage(image, 0,0,this.getWidth(),this.getHeight(),this);
	}
}
