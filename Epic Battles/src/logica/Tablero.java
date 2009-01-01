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
		casillas[0][4].setCasilla(new Reina(), true, 0, 4);
		casillas[7][4].setCasilla(new Reina(), false, 7, 4);
		
		/* Reyes */
		casillas[0][3].setCasilla(new Rey(), true, 0, 3);
		casillas[7][3].setCasilla(new Rey(), false, 7, 3);
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
	
	private void mover(String mov)
	{
		char[] letras = mov.toCharArray();
		Casilla ini = casillas[Integer.valueOf(letras[1])][Integer.valueOf(letras[0]-97)];
		Casilla fin = casillas[Integer.valueOf(letras[4])][Integer.valueOf(letras[3]-97)];
		
		fin.setCasilla(ini.getPieza(), ini.getColor(), fin.x, fin.y);
		ini.setCasilla(null, true, ini.x, ini.y);
	}
	
	public void mover(Casilla ini, Casilla fin)
	{
		movimientos.add
		(
			String.valueOf((char)(ini.y+97)) +
			ini.x +
			(fin.getPieza() == null ? "-" : "x") +
			String.valueOf((char)(fin.y+97)) +
			fin.x +
			","
		);
		
		fin.setCasilla(ini.getPieza(), ini.getColor(), fin.x, fin.y);
		ini.setCasilla(null, true, ini.x, ini.y);
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
		Vector<Point> puntos;
		Casilla casTest;
		
		switch (c.getPieza().getTipo())
		{
			/* Los movimientos del peon dependen mucho de la situación otras piezas */
			case Pieza.PEON:
				int sentido = (c.getColor() ? 1 : -1);
				Point test = new Point(c.x+sentido, c.y);
				
				puntos = new Vector<Point>();
				casTest = casillas[test.x][test.y];
				
				if (test.x >= 0 && test.x < 8 && casTest.getPieza() == null)
				{
					puntos.add(test);
					
					test = new Point(c.x+sentido*2, c.y);
					casTest = casillas[test.x][test.y];
					if (c.x == (c.getColor() ? 1 : 6) && casTest.getPieza() == null)
					{
						puntos.add(test);
					}
				}
				
				test = new Point(c.x+sentido, c.y+1);
				if (test.x >= 0 && test.x < 8)
				{
					if (test.y < 8)
					{
						casTest = casillas[test.x][test.y];
						
						if (casTest.getPieza() != null && casTest.getColor() != c.getColor())
							puntos.add(test);
					}
					
					test = new Point(c.x+sentido, c.y-1);
					if (test.y >= 0)
					{
						casTest = casillas[test.x][test.y];
						
						if (casTest.getPieza() != null && casTest.getColor() != c.getColor())
							puntos.add(test);
					}
				}
				
				break;
			
			default:
				puntos = c.getPieza().getPosibles(c.getPosicion());
				break;
		}
		
		/* Quitar puntos invalidos */
		for (int i=0; i<puntos.size();i++)
		{
			casTest = casillas[puntos.get(i).x][puntos.get(i).y];
			
			if (casTest.getPieza() != null && casTest.getColor() == c.getColor())
					puntos.remove(i--);
			
			else
			{
				int tipo = c.getPieza().getTipo();
				
				if (i>0 && tipo == Pieza.ALFIL && tipo == Pieza.TORRE && tipo == Pieza.REINA)
				{
					if (puntos.get(i).distance(puntos.get(i-1)) > 1)
					{
						int oldX = puntos.get(i-1).x;
						int oldY = puntos.get(i-1).y;
						int newX = puntos.get(i).x;
						int newY = puntos.get(i).y;
						
						if (oldX == newX || oldY == newY || oldY == 0 || newY == 0)
						{
								puntos.remove(i--);
						}
						else if (oldX/oldY == newX/newY)
							puntos.remove(i--);
					}
				}
			}
		}
		
		return puntos;
	}
	
	public void marcarPosibles(Casilla c)
	{
		Casilla cs;
		Vector<Point> puntos = posibles(c);
		
		for (Point p: puntos)
		{
			cs = casillas[p.x][p.y];
			cs.actualizarImagen(Casilla.MARCADA);
		}
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

	public void dameListeners(Partida p)
	{
		for (int i=0; i<8; i++)
			for (int j=0; j<8; j++)
				casillas[i][j].addMouseListener(p);
	}

	public void limpiarPosibles()
	{
		for (int i=0; i<8; i++)
			for (int j=0; j<8; j++)
				casillas[i][j].actualizarImagen(Casilla.INACTIVA);
	}
	
	public void setSeleccionada(Casilla c)
	{
		c.actualizarImagen(Casilla.SELECCIONADA);
	}
	
	public void girarTablero()
	{
		for (int i=0; i<8; i++)
			for (int j=0; j<8; j++)
			{
				escenario.getTablero().add(casillas[7-i][7-j], 8*i+j);
			}
	}
}
