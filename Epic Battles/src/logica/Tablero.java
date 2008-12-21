package logica;

import java.awt.Point;
import java.util.Vector;

import piezas.Alfil;
import piezas.Caballo;
import piezas.Peon;
import piezas.Pieza;
import piezas.Reina;
import piezas.Rey;
import piezas.Torre;

public class Tablero
{	
	private Vector<String> movimientos = null;
	private Casilla[][] casillas = null;
	
	public Tablero()
	{
		super();
		this.movimientos = new Vector<String>(10, 10);
		generarTablero();		
	}
	
	public Tablero(Vector<String> movs)
	{
		super();
		this.movimientos = movs;
		generarTablero();
		for (String mov: movs)
		{
			this.mover(mov);
		}
	}
	
	private void generarTablero()
	{
		casillas = new Casilla[8][8];
		
		/* Casillas vacías */
		for (int i=2; i<6; i++)
			for (int j=0; j<8; j++)
				casillas[i][j] = new Casilla();
		
		
		/* Peones */
		for (int i=0; i<8; i++)
		{
			casillas[1][i] = new Casilla(new Peon(), true, 1, i);
			casillas[6][i] = new Casilla(new Peon(), false, 6, i);
		}
		
		/* Torres */
		for (int i=0; i<8; i+=7)
		{
			casillas[0][i] = new Casilla(new Torre(), true, 0, i);
			casillas[7][i] = new Casilla(new Torre(), false, 7, i);
		}
		
		/* Caballos */
		for (int i=1; i<8; i+=5)
		{
			casillas[0][i] = new Casilla(new Caballo(), true, 0, i);
			casillas[7][i] = new Casilla(new Caballo(), false, 7, i);
		}
		
		/* Alfiles */
		for (int i=2; i<8; i+=3)
		{
			casillas[0][i] = new Casilla(new Alfil(), true, 0, i);
			casillas[7][i] = new Casilla(new Alfil(), false, 7, i);
		}
		
		/* Reinas */
		casillas[0][3] = new Casilla(new Reina(), true, 0, 3);
		casillas[7][3] = new Casilla(new Reina(), false, 7, 3);
		
		/* Reyes */
		casillas[0][4] = new Casilla(new Rey(), true, 0, 4);
		casillas[7][4] = new Casilla(new Rey(), false, 7, 4);		
	}
	
	private boolean esJaque(boolean color)
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
	
	private boolean esJaque()
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
		boolean fin = false;
		int calcX, calcY, k;
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
					else
					{
						String enpassant = String.valueOf((char)(c.y+97+i)) + String.valueOf(c.x+sentido*2);
						
						if (movimientos.lastElement().split("-")[0].equals(enpassant))
							puntos.add(new Point(c.x+sentido*2, c.y+i));
					}
				}
				break;
				
			case Pieza.ALFIL:
				for (int i=-1; i<2; i+=2)
					for (int j=-1; j<2; j+=2)
					{
						k = 1;
						calcX = c.x+i*k;
						calcY = c.y+j*k;
						
						if (calcX< 0 || calcX > 7 || calcY < 0 || calcY > 7)
						{
							fin = true;
						}
						else
						{
							fin = false;
							test = casillas[calcX][calcY];
						}
						
						while (!fin)
						{							
							if (test == null)
							{
								puntos.add(new Point(calcX, calcY));
								k++;
								calcX = c.x+i*k;
								calcY = c.y+j*k;
								
								if (calcX < 0 || calcX > 7 || calcY < 0 || calcY > 7)
								{
									fin = true;
								}
								else
								{
									test = casillas[calcX][calcY];
								}
							}
							else if (test.getColor() != c.getColor())
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
				break;
				
			case Pieza.CABALLO:
				for (int i=-1; i<2; i+=2)
					for (int j=-1; j<2; j+=2)
					{
						calcX = c.x+2*i;
						calcY = c.y+j;
						
						if (calcX >= 0 && calcX < 8 && calcY >= 0 && calcY < 8)
						{
							test = casillas[calcX][calcY];
							if (test.getPieza() == null)
							{
								puntos.add(new Point(calcX, calcY));
							}
							else if (c.getColor() != test.getColor())
							{
								puntos.add(new Point(calcX, calcY));
							}
						}
						
						calcX = c.x+i;
						calcY = c.y+2*j;
						
						if (calcX >= 0 && calcX < 8 && calcY >= 0 && calcY < 8)
						{
							test = casillas[calcX][calcY];
							if (test.getPieza() == null)
							{
								puntos.add(new Point(calcX, calcY));
							}
							else if (c.getColor() != test.getColor())
							{
								puntos.add(new Point(calcX, calcY));
							}
						}
					}
				break;
				
			case Pieza.TORRE:
				for (int i=-1; i<2; i+=2)
					for (int j=-1; j<2; j+=2)
					{
						k = 1;
						calcX = c.x + (i==1 ? 0 : j) * k;
						calcY = c.y + (i==1 ? j : 0) * k;
						
						if (calcX< 0 || calcX > 7 || calcY < 0 || calcY > 7)
						{
							fin = true;
						}
						else
						{
							fin = false;
							test = casillas[calcX][calcY];
						}
						
						while (!fin)
						{							
							if (test == null)
							{
								puntos.add(new Point(calcX, calcY));
								k++;
								calcX = c.x + (i==1 ? 0 : j) * k;
								calcY = c.y + (i==1 ? j : 0) * k;
								
								if (calcX< 0 || calcX > 7 || calcY < 0 || calcY > 7)
								{
									fin = true;
								}
								else
								{
									test = casillas[calcX][calcY];
								}
							}
							else if (test.getColor() != c.getColor())
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
				break;
				
			case Pieza.REINA:
				for (int i=-1; i<2; i+=2)
					for (int j=-1; j<2; j+=2)
					{
						k = 1;
						calcX = c.x+i*k;
						calcY = c.y+j*k;
						
						if (calcX< 0 || calcX > 7 || calcY < 0 || calcY > 7)
						{
							fin = true;
						}
						else
						{
							fin = false;
							test = casillas[calcX][calcY];
						}
						
						while (!fin)
						{							
							if (test == null)
							{
								puntos.add(new Point(calcX, calcY));
								k++;
								calcX = c.x+i*k;
								calcY = c.y+j*k;
								
								if (calcX < 0 || calcX > 7 || calcY < 0 || calcY > 7)
								{
									fin = true;
								}
								else
								{
									test = casillas[calcX][calcY];
								}
							}
							else if (test.getColor() != c.getColor())
							{
								puntos.add(new Point(calcX, calcY));
								fin = true;								
							}
							else
							{
								fin = true;
							}
						}
						
						k = 1;
						calcX = c.x + (i==1 ? 0 : j) * k;
						calcY = c.y + (i==1 ? j : 0) * k;
						
						if (calcX< 0 || calcX > 7 || calcY < 0 || calcY > 7)
						{
							fin = true;
						}
						else
						{
							fin = false;
							test = casillas[calcX][calcY];
						}
						
						while (!fin)
						{							
							if (test == null)
							{
								puntos.add(new Point(calcX, calcY));
								k++;
								calcX = c.x + (i==1 ? 0 : j) * k;
								calcY = c.y + (i==1 ? j : 0) * k;
								
								if (calcX< 0 || calcX > 7 || calcY < 0 || calcY > 7)
								{
									fin = true;
								}
								else
								{
									test = casillas[calcX][calcY];
								}
							}
							else if (test.getColor() != c.getColor())
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
				break;
				
			case Pieza.REY:
				for (int i=-1; i<2; i++)
					for (int j=-1; j<2; j++)
					{
						calcX = c.x+i;
						calcY = c.y+j;
						
						if (i!=0 && j!=0 && calcX >= 0 && calcX < 8 && calcY >= 0 && calcY < 8)
						{
							test = casillas[calcX][calcY];
							if (test.getPieza() == null)
							{
								puntos.add(new Point(calcX, calcY));
							}
							else if (c.getColor() != test.getColor() && !esJaque())
							{								
								puntos.add(new Point(calcX, calcY));
							}
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
}
