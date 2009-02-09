package logica.piezas;

import java.awt.Point;
import java.util.Vector;

import logica.Casilla;

public class Peon extends Pieza
{
	private static final long serialVersionUID = -5218659293609981905L;

	public Peon() throws Exception
	{
		super();
	}
	
	@Override
	public String getNombre()
	{
		return "Peon";
	}

	@Override
	public Vector<Point> getPosibles(Casilla[][] casillas, Point p)
	{
		boolean color = casillas[p.x][p.y].getColor();
		int sentido = (color ? 1 : -1);
		Casilla casTest;
		Point test = new Point(p.x+sentido, p.y);
		Vector<Point> puntos = new Vector<Point>();
		
		if (test.x >= 0 && test.x < 8)
		{
			casTest = casillas[test.x][test.y];
			
			if (casTest.getPieza() == null)
			{
				puntos.add(test);
				test = new Point(p.x+sentido*2, p.y);
				
				if (test.x >= 0 && test.x < 8)
				{
					casTest = casillas[test.x][test.y];
					if (p.x == (color ? 1 : 6) && casTest.getPieza() == null)
					{
						puntos.add(test);
					}
				}
			}
			
			test = new Point(p.x+sentido, p.y+1);
			if (test.y < 8)
			{
				casTest = casillas[test.x][test.y];
				
				if (casTest.getPieza() != null && casTest.getColor() != color)
					puntos.add(test);
			}
			
			test = new Point(p.x+sentido, p.y-1);
			if (test.y >= 0)
			{
				casTest = casillas[test.x][test.y];
				
				if (casTest.getPieza() != null && casTest.getColor() != color)
					puntos.add(test);
			}
		}
		
		return puntos;
	}

	@Override
	public Es getTipo()
	{
		return Pieza.Es.Peon;
	}

	@Override
	public int getValor()
	{
		return Pieza.VAL_PEON;
	}
}
