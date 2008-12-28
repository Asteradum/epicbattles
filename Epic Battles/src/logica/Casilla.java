package logica;

import java.awt.Graphics;
import java.awt.Point;

import graficos.Sprite;
import logica.piezas.Pieza;

public class Casilla extends Sprite
{
	private static final long serialVersionUID = 2663916697888476162L;
	private Pieza pieza = null;
	private boolean color = true;
	
	public Casilla()
	{
		super();
	}
	
	public void setCasilla(Pieza p, boolean color, int x, int y)
	{
		this.pieza = p;
		this.color = color;
		this.x = x;
		this.y = y;
		actualizarImagen();
	}

	public Pieza getPieza()
	{
		return pieza;
	}
	
	public boolean getColor()
	{
		return color;
	}
	
	@Override
	public Point getLocation()
	{
		return new Point(this.x, this.y);
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		if (imagen != null)
			g.drawImage(imagen, 0,0,this.getWidth(),this.getHeight(),this);
	}

	public void actualizarImagen()
	{
		if (pieza != null)
		{
			imagen = pieza.getImagen(color);
			repaint();
		}
	}
}
