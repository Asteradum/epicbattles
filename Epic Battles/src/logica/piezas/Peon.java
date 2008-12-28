package logica.piezas;

import java.awt.Point;
import java.util.Vector;

public class Peon extends Pieza
{
	private static final long serialVersionUID = -5218659293609981905L;

	public Peon() throws Exception
	{
		super();
	}
	
	@Override
	public int getTipo()
	{
		return Pieza.PEON;
	}

	@Override
	public String getNombre()
	{
		return "Peon";
	}

	@Override
	public Vector<Point> getPosibles(Point p)
	{
		return null;
	}
}
