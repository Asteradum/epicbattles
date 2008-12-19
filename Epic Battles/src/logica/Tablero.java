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
			this.makeMove(mov);
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
			casillas[1][i] = new Casilla(new Peon(true));
			casillas[6][i] = new Casilla(new Peon(false));
		}
		
		/* Torres */
		for (int i=0; i<8; i+=7)
		{
			casillas[0][i] = new Casilla(new Torre(true));
			casillas[7][i] = new Casilla(new Torre(false));
		}
		
		/* Caballos */
		for (int i=1; i<8; i+=5)
		{
			casillas[0][i] = new Casilla(new Caballo(true));
			casillas[7][i] = new Casilla(new Caballo(false));
		}
		
		/* Alfiles */
		for (int i=2; i<8; i+=3)
		{
			casillas[0][i] = new Casilla(new Alfil(true));
			casillas[7][i] = new Casilla(new Alfil(false));
		}
		
		/* Reinas */
		casillas[0][3] = new Casilla(new Reina(true));
		casillas[7][3] = new Casilla(new Reina(false));
		
		/* Reyes */
		casillas[0][3] = new Casilla(new Rey(true));
		casillas[7][3] = new Casilla(new Rey(false));		
	}
	
	private boolean esJaque(Casilla c)
	{
		boolean jaque = false;
		
		
		
		return jaque;
	}
	
	public void makeMove(String mov)
	{
		this.movimientos.add(mov);
		
		/* Condición de jaque */
	}
	
	public Point[] showMoves(Point p)
	{
		return null;
	}
	
	private Casilla buscarPieza(Pieza p)
	{
		boolean enc = false;
		int i = 0, j = 0;
		Casilla c = null;
		
		while (!enc && i<8)
			while (!enc && j<8)
			{
				c = casillas[i][j];
			
				if (c.getPieza().getTipo() == p.getTipo()
						&& c.getPieza().getColor() == p.getColor())
				{
					enc = true;
				}
			}
		
		return c;		
	}
}
