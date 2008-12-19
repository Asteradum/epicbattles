package piezas;

import graficos.Sprite;

public abstract class Pieza extends Sprite
{
	private static final long serialVersionUID = 7607215079770662403L;
	public static boolean BLANCAS = true;
	public static boolean NEGRAS = false;
	public static int PEON = 1;
	public static int ALFIL = 2;
	public static int CABALLO = 3;
	public static int TORRE = 5;
	public static int REINA = 3;
	public static int REY = 0;
	private boolean color = true;	

	public Pieza(boolean color)
	{		
		super();
		this.color = color;
	}

	public boolean getColor()
	{
		return color;
	}
	
	public abstract int getTipo();
}
