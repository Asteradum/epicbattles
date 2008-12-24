package logica.piezas;

import java.awt.Point;
import java.util.Vector;

public class Caballo extends Pieza
{
	private static final long serialVersionUID = 3449768679721979235L;

	@Override
	public int getTipo()
	{
		return Pieza.CABALLO;
	}

	@Override
	public String getNombre()
	{
		return "Caballo";
	}

	@Override
	public Vector<Point> getPosibles(Point p)
	{
		int calcX, calcY;
		Vector<Point> puntos = new Vector<Point>();
		
		for (int i=-1; i<2; i+=2)
			for (int j=-1; j<2; j+=2)
			{
				calcX = p.x+2*i;
				calcY = p.y+j;
				
				if (calcX >= 0 && calcX < 8 && calcY >= 0 && calcY < 8)
				{
					puntos.add(new Point(calcX, calcY));
				}
				
				calcX = p.x+i;
				calcY = p.y+2*j;
				
				if (calcX >= 0 && calcX < 8 && calcY >= 0 && calcY < 8)
				{
					puntos.add(new Point(calcX, calcY));
				}
			}
		
		return puntos;
	}		
}
