package logica.piezas;

import java.awt.Point;
import java.util.Vector;

import logica.Casilla;

public class Alfil extends Pieza
{
	private static final long serialVersionUID = 1646351736441866648L;
	
	public Alfil() throws Exception
	{
		super();
	}
	
	@Override
	public String getNombre()
	{
		return "Alfil";
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
				calcX = p.x+i;
				calcY = p.y+j;
				
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
							calcX = p.x+i*k;
							calcY = p.y+j*k;
							
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
		return Pieza.Es.Alfil;
	}

	@Override
	public int getValor()
	{
		return Pieza.VAL_ALFIL;
	}
}
