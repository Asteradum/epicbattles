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
	private boolean marcada = false;
	private boolean seleccionada  = false;
	private boolean enpassant = false;
	private boolean promocion = false;
	private boolean enrocable = false;
	
	public Casilla()
	{
		super();
	}
	
	private void actualizarImagen() { actualizarImagen(Casilla.Estado.Inactiva); }

	public void actualizarImagen(Estado modo)
	{
		this.marcada = (modo == Casilla.Estado.Marcada ? true : false);
		this.seleccionada = (modo == Casilla.Estado.Seleccionada ? true : false);
		this.enpassant = (modo == Casilla.Estado.EnPassant ? true : false);
		this.promocion = (modo == Casilla.Estado.Promocion ? true : false);
		this.enrocable = (modo == Casilla.Estado.Enrocable ? true : false);
		
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
	
	public boolean esPromocion()
	{
		return promocion;
	}
	
	public boolean esEnrocable()
	{
		return enrocable;
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
			else if (promocion)
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
			
			if (marcada)
			{
				tam = this.getWidth()/6;
				
				g.setColor(new Color(112, 119, 209));
				g.fillOval(mid - tam/2, mid - tam/2, tam, tam);
			}
			else if (promocion)
			{
				tam = this.getWidth()/6;
				
				g.setColor(new Color(235, 182, 20));
				g.fillOval(mid - tam/2, mid - tam/2, tam, tam);
			}
			else if (enrocable)
			{
				tam = this.getWidth()/4;
				
				g.setColor(new Color(124, 186, 69));
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
