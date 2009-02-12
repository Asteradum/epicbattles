package logica.piezas;

import java.awt.Point;
import java.util.Vector;

import logica.Casilla;

public class Rey extends Pieza
{
	private static final long serialVersionUID = 1077899422152787342L;
	
	public Rey() throws Exception
	{
		super();
	}
	
	@Override
	public String getNombre()
	{
		return "Rey";
	}

	@Override
	public Vector<Point> getPosibles(Casilla[][] casillas, Point p)
	{
		int calcX, calcY;
		Casilla casTest;
		Vector<Point> puntos = new Vector<Point>();
		
		for (int i=-1; i<2; i++)
			for (int j=-1; j<2; j++)
				if (i!=0 || j!=0)
				{
					calcX = p.x+i;
					calcY = p.y+j;
					
					if (calcX >= 0 && calcX < 8 && calcY >= 0 && calcY < 8)
					{
						casTest = casillas[calcX][calcY];
						if (casTest.getPieza() == null)
						{
							puntos.add(new Point(calcX, calcY));
						}
						else if (casillas[p.x][p.y].getColor() != casTest.getColor())
						{								
							puntos.add(new Point(calcX, calcY));
						}
					}
				}
		
		return puntos;
	}

	@Override
	public Es getTipo()
	{
		return Pieza.Es.Rey;
	}

	@Override
	public int getValor()
	{
		return Pieza.VAL_REY;
	}
}
