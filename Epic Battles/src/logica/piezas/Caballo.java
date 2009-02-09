package logica.piezas;

import java.awt.Point;
import java.util.Vector;

import logica.Casilla;

public class Caballo extends Pieza
{
	private static final long serialVersionUID = 3449768679721979235L;
	
	public Caballo() throws Exception
	{
		super();
	}
	
	@Override
	public String getNombre()
	{
		return "Caballo";
	}

	@Override
	public Vector<Point> getPosibles(Casilla[][] casillas, Point p)
	{
		int calcX, calcY;
		Casilla casTest, c = casillas[p.x][p.y];
		boolean color = c.getColor();
		Vector<Point> puntos = new Vector<Point>();
		
		for (int i=-1; i<2; i+=2)
			for (int j=-1; j<2; j+=2)
			{
				calcX = p.x+2*i;
				calcY = p.y+j;
				
				if (calcX >= 0 && calcX < 8 && calcY >= 0 && calcY < 8)
				{
					casTest = casillas[calcX][calcY];
					
					if (!(casTest.getPieza() != null && casTest.getColor() == color))
						puntos.add(new Point(calcX, calcY));
				}
				
				calcX = p.x+i;
				calcY = p.y+2*j;
				
				if (calcX >= 0 && calcX < 8 && calcY >= 0 && calcY < 8)
				{
					casTest = casillas[calcX][calcY];
					
					if (!(casTest.getPieza() != null && casTest.getColor() == color))
						puntos.add(new Point(calcX, calcY));
				}
			}
		
		return puntos;
	}

	@Override
	public Es getTipo()
	{
		return Pieza.Es.Caballo;
	}

	@Override
	public int getValor()
	{
		return Pieza.VAL_CABALLO;
	}
}
