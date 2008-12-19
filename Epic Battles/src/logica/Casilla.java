package logica;

import piezas.Pieza;
import graficos.Sprite;

public class Casilla extends Sprite
{
	private static final long serialVersionUID = 2663916697888476162L;	
	private Pieza pieza = null;
	
	public Casilla()
	{
		super();
	}
	
	public Casilla(Pieza p)
	{
		super();
		this.setPieza(p);
	}

	public void setPieza(Pieza pieza)
	{
		this.pieza = pieza;
	}

	public Pieza getPieza()
	{
		return pieza;
	}
}
