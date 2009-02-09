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
	public static enum Estado
	{
		Inactiva, Marcada, Seleccionada, EnPassant, Promocion, Enrocable
	}
	
	private static final long serialVersionUID = 2663916697888476162L;
	
	private static final ColorTintFilter filtroMarcada = new ColorTintFilter(Color.red, 0.5f);
	private static final ColorTintFilter filtroSeleccionada = new ColorTintFilter(Color.blue, 0.5f);
	private static final ColorTintFilter filtroPromo = new ColorTintFilter(Color.orange, 0.5f);
	private static BufferedImage cache = null;
	
	private Pieza pieza = null;
	private boolean color = true;
	private Estado estado;
	
	public Casilla()
	{
		super();
	}
	
	private void actualizarImagen() { actualizarImagen(Casilla.Estado.Inactiva); }

	public void actualizarImagen(Estado modo)
	{
		estado = modo;
		
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
	
	public Casilla clon()
	{
		Casilla clon = new Casilla();
		
		clon.pieza = this.pieza;
		clon.color = this.color;
		clon.x = this.x;
		clon.y = this.y;
		
		return clon;
	}
	
	public boolean esEnpassant()
	{
		return (estado == Estado.EnPassant);
	}
	
	public boolean esEnrocable()
	{
		return (estado == Estado.Enrocable);
	}
	
	public boolean esMarcada()
	{
		return (estado == Estado.Marcada);
	}
	
	public boolean esPromocion()
	{
		return (estado == Estado.Promocion);
	}
	
	public boolean esSeleccionada()
	{
		return (estado == Estado.Seleccionada);
	}
	
	public boolean getColor()
	{
		return color;
	}
	
	public Pieza getPieza()
	{
		return pieza;
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
			if (estado == Estado.Marcada)
			{
				filtroMarcada.filter((BufferedImage) imagen, cache);
				g.drawImage(cache, 0, 0, this.getWidth(), this.getHeight(), this);
			}
			else if (estado == Estado.Seleccionada)
			{
				filtroSeleccionada.filter((BufferedImage) imagen, cache);
				g.drawImage(cache, 0, 0, this.getWidth(), this.getHeight(), this);
			}
			else if (estado == Estado.Promocion)
			{
				filtroPromo.filter((BufferedImage) imagen, cache);
				g.drawImage(cache, 0, 0, this.getWidth(), this.getHeight(), this);
			}
			else
			{
				g.drawImage(imagen, 0, 0, this.getWidth(), this.getHeight(), this);
			}
		}
		else
		{
			int mid = this.getWidth()/2, tam;
			
			if (estado == Estado.Marcada)
			{
				tam = this.getWidth()/6;
				
				g.setColor(new Color(112, 119, 209));
				g.fillOval(mid - tam/2, mid - tam/2, tam, tam);
			}
			else if (estado == Estado.Promocion)
			{
				tam = this.getWidth()/5;
				
				g.setColor(new Color(235, 182, 20));
				g.fillOval(mid - tam/2, mid - tam/2, tam, tam);
			}
			else if (estado == Estado.Enrocable)
			{
				tam = this.getWidth()/5;
				
				g.setColor(new Color(124, 186, 69));
				g.fillOval(mid - tam/2, mid - tam/2, tam, tam);
			}
			else if (estado == Estado.EnPassant)
			{
				tam = this.getWidth()/5;
				
				g.setColor(Color.red);
				g.fillOval(mid - tam/2, mid - tam/2, tam, tam);
			}
		}
	}

	public void setCasilla(Pieza p, boolean color, int x, int y)
	{
		this.pieza = p;
		this.color = color;
		this.x = x;
		this.y = y;
		actualizarImagen();
	}
}
