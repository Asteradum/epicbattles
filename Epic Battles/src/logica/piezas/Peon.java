package logica.piezas;

import java.awt.Point;
import java.util.Vector;

public class Peon extends Pieza
{
	private static final long serialVersionUID = -5218659293609981905L;

	@Override
	public int getTipo()
	{
		return Pieza.PEON;
	}

	@Override
	public String getNombre()
	{
		return "Peón";
	}

	@Override
	public Vector<Point> getPosibles(Point p) {
		// TODO Auto-generated method stub
		return null;
	}
}
