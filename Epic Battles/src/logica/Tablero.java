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
		Casilla rey = buscarRey(color), c = null;
		
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
	
	public boolean mover(Casilla ini, Casilla fin)
	{
		movimientos.add
		(
			String.valueOf((char)(ini.y+97)) +
			ini.x +
			(fin.getPieza() == null ? "-" : "x") +
			String.valueOf((char)(fin.y+97)) +
			fin.x
		);
		
		fin.setCasilla(ini.getPieza(), ini.getColor(), fin.x, fin.y);
		ini.setCasilla(null, true, ini.x, ini.y);
		
		/* Condición de jaque mate enemigo */
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
		Vector<Point> puntos;
		Casilla casTest;
		
		switch (c.getPieza().getTipo())
		{
			/* Los movimientos del peon dependen mucho de la situación otras piezas */
			case Pieza.PEON:
				int sentido = (c.getColor() ? 1 : -1);
				Point test = new Point(c.x+sentido, c.y);
				
				puntos = new Vector<Point>();
				
				if (test.x >= 0 && test.x < 8)
				{
					casTest = casillas[test.x][test.y];
					
					if (casTest.getPieza() == null)
					{
						puntos.add(test);
						test = new Point(c.x+sentido*2, c.y);
						
						if (test.x >= 0 && test.x < 8)
						{
							casTest = casillas[test.x][test.y];
							if (c.x == (c.getColor() ? 1 : 6) && casTest.getPieza() == null)
							{
								puntos.add(test);
							}
						}
					}
					
					test = new Point(c.x+sentido, c.y+1);
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
		
		/* Quitar posiciones bloqueadas */
		int tipo = c.getPieza().getTipo();
		
		if (tipo == Pieza.ALFIL || tipo == Pieza.TORRE || tipo == Pieza.REINA)
		{
			int prevX = puntos.get(0).x;
			int prevY = puntos.get(0).y;
			int thisX, thisY, deltaBCx, deltaBCy, deltaABx, deltaABy;
			
			for (int i=1; i<puntos.size(); i++)
			{
				thisX = puntos.get(i).x;
				thisY = puntos.get(i).y;
				deltaBCx = thisX - prevX;
				deltaBCy = thisY - prevY;
				deltaABx = prevX - c.x;
				deltaABy = prevY - c.y;
				
				casTest = casillas[puntos.get(i).x][puntos.get(i).y];
				
				if (Math.signum(deltaABx) == Math.signum(deltaBCx) && Math.signum(deltaABy) == Math.signum(deltaBCy) && (Math.abs(puntos.get(i).distance(puntos.get(i-1))) > 1.5 || casillas[prevX][prevY].getPieza() != null))
				{
					puntos.remove(i--);
				}
				
				prevX = thisX;
				prevY = thisY;
			}
		}
		
		/* Quitar posiciones ocupadas por piezas propias */
		for (int i=0; i<puntos.size();i++)
		{
			casTest = casillas[puntos.get(i).x][puntos.get(i).y];
			
			if (casTest.getPieza() != null && casTest.getColor() == c.getColor())
					puntos.remove(i--);
		}
		
		/* Quitar casillas que supongan jaque propio */
		{
		}
		
		return puntos;
	}
	
	public void marcarPosibles(Casilla c)
	{
		Casilla cs;
		Vector<Point> puntos = posibles(c);
		
		/* Condiciones de enroque *//*
		if (c.getPieza().getTipo() == Pieza.REY)
		{
			boolean noEnc = true;
			int i = 0;
			String[] torres =
			{
				"a" + String.valueOf(c.getColor() ? 0 : 7),
				"h" + String.valueOf(c.getColor() ? 0 : 7)
			};
			
			while (noEnc && i < movimientos.size())
			{
				if
				(
					movimientos.get(i).indexOf(torres[0]) != -1 ||
					movimientos.get(i).indexOf(torres[1]) != -1
					
				)
				{
					noEnc = false;
				}
			}
			
			if (!noEnc) ;
		}*/
		
		for (Point p: puntos)
		{
			cs = casillas[p.x][p.y];
			
			/* Promoción del peón */
			if (c.getPieza().getTipo() == Pieza.PEON && p.x == (c.getColor() ? 7 : 0))
				cs.actualizarImagen(Casilla.Estado.Promocion);
			
			/* Movimiento en passant */
	//		else if (c.getPieza().getTipo() == Pieza.PEON && movimientos.lastElement())
			
			/* Marcado normal */
			else
				cs.actualizarImagen(Casilla.Estado.Marcada);
		}
	}
	
	private Casilla buscarRey(boolean color)
	{
		boolean enc = false;
		int i=0, j=0;
		Casilla c = null;
		
		while (!enc && i<8)
			while (!enc && j<8)
			{
				c = casillas[i++][j++];
				if (c.getPieza().getTipo() == Pieza.REY && c.getColor() == color)
				{
					enc = true;
				}
			}
		
		return c;
	}
	
	private int contarPuntos(boolean color)
	{
		int suma = 0;
		Casilla c;
		
		for (int i=0; i<8; i++)
			for (int j=0; j<8; j++)
			{
				c = casillas[i][j];
				
				if (c.getColor() == color)
					suma += c.getPieza().getTipo();
			}
		
		return suma;
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
				casillas[i][j].actualizarImagen(Casilla.Estado.Inactiva);
	}
	
	public void setSeleccionada(Casilla c)
	{
		c.actualizarImagen(Casilla.Estado.Seleccionada);
	}
	
	public void girarTablero()
	{
		for (int i=0; i<8; i++)
			for (int j=0; j<8; j++)
				escenario.getTablero().add(casillas[7-i][7-j], 8*i+j);
	}
}
