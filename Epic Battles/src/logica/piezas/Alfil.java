package logica.piezas;

import java.awt.Point;
import java.util.Vector;

public class Alfil extends Pieza
{
	private static final long serialVersionUID = 1646351736441866648L;

	@Override
	public int getTipo()
	{
		return Pieza.ALFIL;
	}

	@Override
	public String getNombre()
	{
		return "Alfil";
	}

	@Override
	public Vector<Point> getPosibles(Point p)
	{
		boolean fin = false;
		int calcX, calcY, k;
		Vector<Point> puntos = new Vector<Point>();
		
		for (int i=-1; i<2; i+=2)
			for (int j=-1; j<2; j+=2)
			{
				k = 1;
				calcX = p.x+i*k;
				calcY = p.y+j*k;
				
				if (calcX < 0 || calcX > 7 || calcY < 0 || calcY > 7)
				{
					fin = true;
				}
				
				while (!fin)
				{
					puntos.add(new Point(calcX, calcY));
					k++;
					calcX = p.x+i*k;
					calcY = p.y+j*k;
					
					if (calcX < 0 || calcX > 7 || calcY < 0 || calcY > 7)
					{
						fin = true;
					}
				}
			}
		return puntos;
	}
}
