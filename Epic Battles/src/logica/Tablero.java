package logica;

import graficos.Escenario;

import java.awt.Point;
import java.util.Vector;

import logica.piezas.Alfil;
import logica.piezas.Caballo;
import logica.piezas.Peon;
import logica.piezas.Pieza;
import logica.piezas.Reina;
import logica.piezas.Rey;
import logica.piezas.Torre;

public class Tablero
{
	private static final long serialVersionUID = -8743924243868541721L;
	private Vector<String> movimientos = null;
	private Escenario escenario = null;
	private Casilla[][] casillas = null;
	
	public Tablero() throws Exception
	{
		super();
		this.escenario = new Escenario();
		this.casillas = escenario.getCasillas();
		generarTablero();
		this.movimientos = new Vector<String>(10, 10);
	}
	
	public Tablero(Vector<String> movs) throws Exception
	{
		super();
		this.escenario = new Escenario();
		this.casillas = escenario.getCasillas();
		generarTablero();
		this.movimientos = movs;
		for (String mov: movs)
		{
			this.mover(mov);
		}
	}
	
	private void generarTablero() throws Exception
	{
		/* Casillas vacías */
		for (int i=2; i<6; i++)
			for (int j=0; j<8; j++)
				casillas[i][j].setCasilla(null, true, i, j);
		
		/* Peones */
		for (int i=0; i<8; i++)
		{
			casillas[1][i].setCasilla(new Peon(), true, 1, i);
			casillas[6][i].setCasilla(new Peon(), false, 6, i);
		}
		
		/* Torres */
		for (int i=0; i<8; i+=7)
		{
			casillas[0][i].setCasilla(new Torre(), true, 0, i);
			casillas[7][i].setCasilla(new Torre(), false, 7, i);
		}
		
		/* Caballos */
		for (int i=1; i<8; i+=5)
		{
			casillas[0][i].setCasilla(new Caballo(), true, 0, i);
			casillas[7][i].setCasilla(new Caballo(), false, 7, i);
		}
		
		/* Alfiles */
		for (int i=2; i<8; i+=3)
		{
			casillas[0][i].setCasilla(new Alfil(), true, 0, i);
			casillas[7][i].setCasilla(new Alfil(), false, 7, i);
		}
		
		/* Reinas */
		casillas[0][3].setCasilla(new Reina(), true, 0, 3);
		casillas[7][3].setCasilla(new Reina(), false, 7, 3);
		
		/* Reyes */
		casillas[0][4].setCasilla(new Rey(), true, 0, 4);
		casillas[7][4].setCasilla(new Rey(), false, 7, 4);
	}
	
	private boolean esJaque(boolean color) throws Exception
	{
		boolean jaque = false;
		int i=0, j=0;
		Casilla rey = buscarPieza(new Rey(), color), c = null;
		
		while (!jaque && i<8)
		{
			while (!jaque && j<8)
			{
				c = casillas[i][j];
				if (c.getColor() != color)
					for (Point p: posibles(c))
						if (p.equals(rey))
							jaque = true;
				j++;
			}
			i++;
		}
		return jaque;
	}
	
	private boolean esJaque(Point origen, Point destino)
	{
		return false;
		
	}
	
	public boolean mover(String mov)
	{
		return false;
		
	}
	
	/*public boolean makeMove(String mov)
	{
		boolean posible = false;
		/* Condición de jaque */	/*
		
			this.movimientos.add(mov);
			posible = true;
		
		return posible;
	}*/
	
	private Vector<Point> posibles(Casilla c)
	{
		Casilla test = null;
		Vector<Point> puntos = new Vector<Point>();
		
		switch (c.getPieza().getTipo())
		{
			case Pieza.PEON:
				int sentido = (c.getColor() ? 1 : -1);
				
				if (casillas[c.x+sentido][c.y].getPieza() == null && c.x+sentido >= 0 && c.x+sentido < 8)
					puntos.add(new Point(c.x+sentido, c.y));
				
				if (casillas[c.x+sentido*2][c.y].getPieza() == null && c.x+sentido*2 >= 0 && c.x+sentido*2 < 8)
					puntos.add(new Point(c.x+sentido*2, c.y));
				
				for (int i=-1; i<2; i+=2)
				{
					test = casillas[c.x+sentido][c.y+i];
					
					if (test.getPieza() != null)
					{
						if (test.getColor() != c.getColor())
							puntos.add(new Point(c.x+sentido, c.y+i));
					}
				}
				
				break;
			
			default:
				puntos = c.getPieza().getPosibles(c.getLocation());
				Casilla cs;
			
				for (int i=0; i<puntos.size();)
				{
					cs = casillas[puntos.get(i).x][puntos.get(i).y];
					
					if (cs.getPieza() != null && cs.getColor() == c.getColor())
					{
						puntos.remove(i);
					}
					else
					{
						i++;
					}
				}
				
				break;
		}
		
		return puntos;
	}
	
	public Point[] showMoves(Point p)
	{
		return null;
	}
	
	private Casilla buscarPieza(Pieza p, boolean color)
	{
		boolean enc = false;
		int i=0, j=0;
		Casilla c = null;
		
		while (!enc && i<8)
			while (!enc && j<8)
			{
				c = casillas[i][j];
				if (c.getPieza().getTipo() == p.getTipo()
						&& c.getColor() == color)
				{
					enc = true;
				}
			}
		
		return c;
	}

	public Vector<String> getMovimientos()
	{
		if (movimientos != null)
			return movimientos;
		else
			return null;
	}
	
	public Escenario getEscenario()
	{
		return escenario;
	}
}
