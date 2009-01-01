package logica.piezas;

import java.awt.Point;
import java.util.Vector;

public class Rey extends Pieza
{
	private static final long serialVersionUID = 1077899422152787342L;
	
	public Rey() throws Exception
	{
		super();
	}
	
	@Override
	public int getTipo()
	{
		return Pieza.REY;
	}

	@Override
	public String getNombre()
	{
		return "Rey";
	}

	@Override
	public Vector<Point> getPosibles(Point p)
	{
		int calcX, calcY;
		Vector<Point> puntos = new Vector<Point>();
		
		for (int i=-1; i<2; i++)
			for (int j=-1; j<2; j++)
			{
				calcX = p.x+i;
				calcY = p.y+j;
				
				if ((i!=0 || j!=0) && calcX >= 0 && calcX < 8 && calcY >= 0 && calcY < 8)
				{
					puntos.add(new Point(calcX, calcY));
				}
			}
		
		return puntos;
	}
}
