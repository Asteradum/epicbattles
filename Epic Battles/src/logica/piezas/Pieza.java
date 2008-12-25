package logica.piezas;

import java.awt.Point;
import java.util.Vector;

import graficos.Sprite;

public abstract class Pieza extends Sprite
{
	private static final long serialVersionUID = 7607215079770662403L;
	public static final boolean BLANCAS = true;
	public static final boolean NEGRAS = false;
	public static final int PEON = 1;
	public static final int ALFIL = 2;
	public static final int CABALLO = 3;
	public static final int TORRE = 5;
	public static final int REINA = 9;
	public static final int REY = 10;

	public Pieza()
	{
		super();
	}
	
	public abstract int getTipo();
	
	public abstract String getNombre();
	
	public abstract Vector<Point> getPosibles(Point p);
}
