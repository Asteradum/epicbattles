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
	private static final ColorTintFilter filtroMarcada = new ColorTintFilter(Color.red, 0.5f);
	private static final ColorTintFilter filtroSeleccionada = new ColorTintFilter(Color.blue, 0.5f);
	private static BufferedImage cache = null;
	
	public static final int INACTIVA = 0;
	public static final int MARCADA = 1;
	public static final int SELECCIONADA = 2;
	
	private Pieza pieza = null;
	private boolean color = true;
	private boolean marcada = false;
	private boolean seleccionada  = false;
	
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
	
	public Point getPosicion()
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
				filtroMarcada.filter((BufferedImage) imagen, cache);
				g.drawImage(cache, 0, 0, this.getWidth(), this.getHeight(), this);
			}
			else if (seleccionada)
			{
				filtroSeleccionada.filter((BufferedImage) imagen, cache);
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
				int tam = this.getWidth()/7;
				
				if (g.getColor().getRed() == 51)
				{
					g.setColor(new Color(112, 119, 209));
				}
				
				g.fillOval(mid - tam/2, mid - tam/2, tam, tam);
			}
		}
	}

	private void actualizarImagen() { actualizarImagen(Casilla.INACTIVA); }
	
	public void actualizarImagen(int modo)
	{
		this.marcada = (modo == Casilla.MARCADA ? true : false);
		this.seleccionada = (modo == Casilla.SELECCIONADA ? true : false);
		
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
