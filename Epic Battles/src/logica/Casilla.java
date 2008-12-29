package logica;

import graficos.Sprite;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import logica.piezas.Pieza;

import org.jdesktop.swingx.graphics.GraphicsUtilities;
import org.jdesktop.swingx.image.ColorTintFilter;

public class Casilla extends Sprite
{
	private static final long serialVersionUID = 2663916697888476162L;
	private Pieza pieza = null;
	private boolean color = true;
	private boolean marcada = false;
	private static final ColorTintFilter filtro = new ColorTintFilter(Color.red, 0.5f);
	private static BufferedImage cache = null;
	
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
		
		if (pieza != null)
		{
			if (marcada)
			{
				filtro.filter((BufferedImage) imagen, cache);
				g.drawImage(cache, 0, 0, this.getWidth(), this.getHeight(), this);
			}
			else
			{
				g.drawImage(imagen, 0, 0, this.getWidth(), this.getHeight(), this);
			}
		}
		else
		{
			if (marcada)
			{
				int mid = this.getWidth()/2;
				int tam = this.getWidth()/8;
				
				if (g.getColor().getRed() == 51)
				{
					g.setColor(new Color(142, 148, 219));
				}
				
				g.drawOval(mid - tam/2, mid - tam/2, tam, tam);
			}
		}
	}

	private void actualizarImagen() { actualizarImagen(false); }
	
	public void actualizarImagen(boolean marcada)
	{
		this.marcada = marcada;
		
		if (pieza != null)
		{
			imagen = pieza.getImagen(color);
			
			if (cache == null)
			{
				cache = GraphicsUtilities.createCompatibleImage((BufferedImage) imagen);
			}
		}
		
		repaint();
	}

	public boolean esMarcada()
	{
		return marcada;
	}
}
