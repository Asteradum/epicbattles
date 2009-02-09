package logica.piezas;

import java.awt.Point;
import java.util.Vector;

import logica.Casilla;

public class Torre extends Pieza
{
	private static final long serialVersionUID = 4081511084636434630L;
	
	public Torre() throws Exception
	{
		super();
	}

	@Override
	public String getNombre()
	{
		return "Torre";
	}

	@Override
	public Vector<Point> getPosibles(Casilla[][] casillas, Point p)
	{
		boolean fin;
		int calcX, calcY, k;
		Casilla casTest;
		Vector<Point> puntos = new Vector<Point>();
		
		for (int i=-1; i<2; i+=2)
			for (int j=-1; j<2; j+=2)
			{
				calcX = p.x + (i==1 ? 0 : j);
				calcY = p.y + (i==1 ? j : 0);
				
				if (calcX >= 0 && calcX < 8 && calcY >= 0 && calcY < 8)
				{
					fin = false;
					casTest = casillas[calcX][calcY];
					k = 1;
					
					while (!fin)
					{
						if (casTest.getPieza() == null)
						{
							puntos.add(new Point(calcX, calcY));
							
							k++;
							calcX = p.x + (i==1 ? 0 : j) * k;
							calcY = p.y + (i==1 ? j : 0) * k;
							
							if (calcX < 0 || calcX > 7 || calcY < 0 || calcY > 7)
							{
								fin = true;
							}
							else
							{
								casTest = casillas[calcX][calcY];
							}
						}
						else if (casTest.getColor() != casillas[p.x][p.y].getColor())
						{
							puntos.add(new Point(calcX, calcY));
							fin = true;
						}
						else
						{
							fin = true;
						}
					}
				}
			}
		
		return puntos;
	}

	@Override
	public Es getTipo()
	{
		return Pieza.Es.Torre;
	}

	@Override
	public int getValor()
	{
		return Pieza.VAL_TORRE;
	}
}
