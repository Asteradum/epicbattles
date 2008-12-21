package logica;

import piezas.Pieza;
import graficos.Sprite;

public class Casilla extends Sprite
{
	private static final long serialVersionUID = 2663916697888476162L;	
	private Pieza pieza = null;
	private boolean color = true;
	
	public Casilla()
	{
		super();
	}
	
	public Casilla(Pieza p, boolean color, int x, int y)
	{
		super();
		this.setPieza(p);
		this.color = color;
		this.x = x;
		this.y = y;
	}

	public void setPieza(Pieza pieza)
	{
		this.pieza = pieza;
	}

	public Pieza getPieza()
	{
		return pieza;
	}
	
	public boolean getColor()
	{
		return color;
	}
}
