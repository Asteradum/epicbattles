package logica;

import graficos.Escenario;

import java.awt.Color;
import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JPanel;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import logica.Casilla.Estado;
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
	private Casilla[][] casillas = null;
	private Casilla enJaque = null;
	private Escenario escenario = null;
	private Vector<String> movimientos = null;
	private int nivelJaques = 0;
	
	public Tablero(boolean red) throws Exception
	{
		super();
		this.escenario = new Escenario(red);
		this.casillas = escenario.getCasillas();
		generarTablero();
		this.movimientos = new Vector<String>(10, 10);
		GameSave.vs = movimientos;
	}
	
	public Tablero(Vector<String> movs, boolean red) throws Exception
	{
		super();
		this.escenario = new Escenario(red);
		this.casillas = escenario.getCasillas();
		generarTablero();
		this.movimientos = movs;
		GameSave.vs = movimientos;
		for (String mov: movs)
		{
			this.mover(mov);
		}
	}
	
	private Casilla buscarRey(boolean color)
	{
		boolean enc = false;
		Casilla c = null;
		
		for (int i=0; !enc && i<8; i++)
			for (int j=0; !enc && j<8; j++)
			{
				c = casillas[i][j];
				if (c.getPieza() != null)
					if (c.getPieza().getTipo() == Pieza.REY && c.getColor() == color)
						enc = true;
			}
		
		return c;
	}
	
	public boolean comprobarJaques(boolean color)
	{		
		if (enJaque  == null)
		{
			Casilla rey = esJaque(color);
			
			if (rey != null)
			{
				rey.setBackground(Color.red);
				enJaque = rey;
				return esJaqueMate(color);
			}
		}
		else
		{
			int calcX = (enJaque.x>4 ? enJaque.x-1 : enJaque.x+1);
			int calcY = (enJaque.y>4 ? enJaque.y-1 : enJaque.y+1);
			
			enJaque.setBackground(casillas[calcX][calcY].getBackground());
			enJaque = null;
		}
		
		return false;
	}
	
	public int contarPuntos(boolean color)
	{
		int suma = 0;
		Casilla c;
		
		for (int i=0; i<8; i++)
			for (int j=0; j<8; j++)
			{
				c = casillas[i][j];
				
				if (c.getPieza() != null && c.getColor() == color)
					suma += c.getPieza().getTipo();
			}
		
		return suma;
	}
	
	public void dameListeners(Partida p)
	{
		for (int i=0; i<8; i++)
			for (int j=0; j<8; j++)
				casillas[i][j].addMouseListener(p);
	}
	
	public void enpassant(Casilla ini, Casilla fin)
	{
		int sentido = (ini.getColor() ? -1 : 1);
		
		mover(ini, fin, true);
		casillas[fin.x+sentido][fin.y].setCasilla(null, true, fin.x+sentido, fin.y);
	}
	
	public void enrocar(Casilla rey, Casilla fin)
	{
		int sentido = (fin.y > rey.y ? 1 : -1);
		Casilla limpia = new Casilla();
		
		limpia.x = fin.x;
		limpia.y = fin.y+sentido;
		
		mover(rey, fin, true);
		mover(casillas[fin.x][fin.y+sentido], casillas[fin.x][fin.y-sentido], false);
	}
	
	private boolean esAmenazado(Casilla c, boolean color)
	{
		boolean esAmenazado = false;
		Casilla temp;
		
		for (int i=0; !esAmenazado && i<8; i++)
			for (int j=0; !esAmenazado && j<8; j++)
			{
				temp = casillas[i][j];
				if (temp.getPieza() != null && temp.getColor() == color)
					for (Point p: posibles(temp))
						if (p.x == c.x && p.y == c.y)
							esAmenazado = true;
			}
		
		return esAmenazado;
	}
	
	private Casilla esJaque(boolean color)
	{
		boolean jaque = false;
		Casilla rey = buscarRey(color), c;
		
		for (int i=0; !jaque && i<8; i++)
			for (int j=0; !jaque && j<8; j++)
			{
				c = casillas[i][j];
				if (c.getPieza() != null && c.getColor() != color)
					for (Point p: posibles(c))
						if (p.x == rey.x && p.y == rey.y)
							jaque = true;
			}
		
		if (jaque) return rey;
		else return null;
	}
	
	private boolean esJaque(Casilla rey)
	{
		boolean jaque = false, color = rey.getColor();
		Casilla c;
		
		for (int i=0; !jaque && i<8; i++)
			for (int j=0; !jaque && j<8; j++)
			{
				c = casillas[i][j];
				if (c.getPieza() != null && c.getColor() != color)
					for (Point p: posibles(c))
						if (p.x == rey.x && p.y == rey.y)
							jaque = true;
			}
		
		return jaque;
	}
	
	private boolean esJaqueMate(boolean color)
	{
		boolean jaqueMate = true;
		Casilla c;
		
		for (int i=0; jaqueMate && i<8; i++)
			for (int j=0; jaqueMate && j<8; j++)
			{
				c = casillas[i][j];
				if (c.getPieza() != null && c.getColor() == color)
					if (!posibles(c).isEmpty())
						jaqueMate = false;
			}
		
		return jaqueMate;
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
	
	public Escenario getEscenario()
	{
		return escenario;
	}
	
	public Vector<String> getMovimientos()
	{
		if (movimientos != null)
			return movimientos;
		else
			return null;
	}
	
	public int getNumMovs(boolean color)
	{
		int suma = 0;
		
		for (int i=0; i<movimientos.size(); i++)
		{
			suma+=i%2;
		}
		
		suma = (color ? movimientos.size()-suma : suma );
		
		return suma;
	}
	
	public void girarTablero(boolean color)
	{
		JPanel tablero = escenario.getTablero();
		
		tablero.setVisible(false);
		tablero.removeAll();
		
		if (color)
			for (int i=0; i<8; i++)
				for (int j=0; j<8; j++)
					tablero.add(casillas[7-i][7-j], 8*i+j);
		else
			for (int i=0; i<8; i++)
				for (int j=0; j<8; j++)
					tablero.add(casillas[i][j], 8*i+j);
		
		tablero.setVisible(true);
	}

	public void limpiarPosibles()
	{
		Casilla c;
		for (int i=0; i<8; i++)
			for (int j=0; j<8; j++)
			{
				c = casillas[i][j];
				if (c.esMarcada() || c.esSeleccionada() || c.esEnrocable() || c.esPromocion() || c.esEnpassant())
					casillas[i][j].actualizarImagen(Casilla.Estado.Inactiva);
			}
	}
	
	public void marcarPosibles(Casilla c)
	{
		boolean color = c.getColor();
		Casilla cs;
		Vector<Point> puntos = posibles(c);
		
		for (Point p: puntos)
		{
			cs = casillas[p.x][p.y];
			
			/* Marcado normal */
			cs.actualizarImagen(Casilla.Estado.Marcada);
		}
		
		if (c.getPieza().getTipo() == Pieza.PEON)
		{
			/* Promoción del peón */
			for (Point p: puntos)
			{
				cs = casillas[p.x][p.y];
				if (p.x == (color ? 7 : 0))
					cs.actualizarImagen(Casilla.Estado.Promocion);
			}
			
			/* Movimiento en passant */
			if (c.x == (color ? 4 : 3))
			{
				int numIni = (color ? 7 : 2), numFin = (color ? 5 : 4), indice = (color ? 5 : 2);
				String[] letras = {String.valueOf((char)(c.y+96)), String.valueOf((char)(c.y+98))};
				String[] movs =
				{
						letras[0]+numIni+"-"+letras[0]+numFin,
						letras[1]+numIni+"-"+letras[1]+numFin
				};
				
				for (int i=0; i<2; i++)
					if (movimientos.lastElement().equals(movs[i]))
						casillas[indice][c.y+i*2-1].actualizarImagen(Casilla.Estado.EnPassant);
			}
		}
		
		/* Condiciones de enroque */
		else if (c.getPieza().getTipo() == Pieza.REY)
		{
			boolean reyNoEnc = true;
			String origenRey = (color ? "d1" : "d8");
			
			for (int i=0; reyNoEnc && i<movimientos.size(); i++)
				if (movimientos.get(i).indexOf(origenRey) != -1)
					reyNoEnc = false;
			
			if (reyNoEnc && !esAmenazado(c, !color))
			{
				boolean torreEnc[] = {false, false};
				int sentidoTorre[] = {-1, 1};
				String[] origenTorres =
				{
					"a" + String.valueOf(color ? 1 : 8),
					"h" + String.valueOf(color ? 1 : 8)
				};
			
				for (int i=0; i<2; i++)
					for (int j=0; j<movimientos.size() && !torreEnc[i]; j++)
					{
						if (movimientos.get(j).indexOf(origenTorres[i]) != -1)
						{
							torreEnc[i] = true;
							sentidoTorre[i] = 0;
						}
					}
				
				for (int i=0; i<2; i++)
					if (!torreEnc[i])
					{
						boolean fin = false, despejado = true;
						Casilla temp = c;
						
						for (int j=c.y+sentidoTorre[i];	!fin && despejado; j+=sentidoTorre[i])
						{
							temp = casillas[c.x][j];
							if (temp.getPieza() == null)
							{
								if (esAmenazado(temp, !color))
									despejado = false;
							}
							else if (temp.getPieza().getTipo() == Pieza.TORRE && temp.getColor() == color)
								fin = true;
							else
								despejado = false;
						}
						
						if (despejado)
							casillas[c.x][temp.y-sentidoTorre[i]].actualizarImagen(Estado.Enrocable);
					}
			}
		}
	}

	public void mover(Casilla ini, Casilla fin, boolean consta)
	{
		try
		{
			AudioPlayer.player.start(new AudioStream(new FileInputStream("sonidos/can-to-table-1.wav")));
		}
		catch (FileNotFoundException fnfe)
		{ }
		catch (IOException ioe)
		{ }
		
		if (consta)
			movimientos.add
			(
				String.valueOf((char)(ini.y+97)) +
				(ini.x+1) +
				(fin.getPieza() == null ? "-" : "x") +
				String.valueOf((char)(fin.y+97)) +
				(fin.x+1)
			);
		
		fin.setCasilla(ini.getPieza(), ini.getColor(), fin.x, fin.y);
		ini.setCasilla(null, true, ini.x, ini.y);
	}

	private void mover(String mov)
	{
		char[] letras = mov.toCharArray();
		
		Casilla ini = casillas[Character.getNumericValue(letras[1])-1][letras[0]-97];
		Casilla fin = casillas[Character.getNumericValue(letras[4])-1][letras[3]-97];
		
		fin.setCasilla(ini.getPieza(), ini.getColor(), fin.x, fin.y);
		ini.setCasilla(null, true, ini.x, ini.y);
	}
	
	private Vector<Point> posibles(Casilla c)
	{
		boolean color = c.getColor();
		Casilla casTest;
		Vector<Point> puntos;
		
		switch (c.getPieza().getTipo())
		{
			/* Los movimientos del peon dependen mucho de la situación de otras piezas */
			case Pieza.PEON:
				int sentido = (color ? 1 : -1);
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
							if (c.x == (color ? 1 : 6) && casTest.getPieza() == null)
							{
								puntos.add(test);
							}
						}
					}
					
					test = new Point(c.x+sentido, c.y+1);
					if (test.y < 8)
					{
						casTest = casillas[test.x][test.y];
						
						if (casTest.getPieza() != null && casTest.getColor() != color)
							puntos.add(test);
					}
					
					test = new Point(c.x+sentido, c.y-1);
					if (test.y >= 0)
					{
						casTest = casillas[test.x][test.y];
						
						if (casTest.getPieza() != null && casTest.getColor() != color)
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
		for (int i=0; i<puntos.size(); i++)
		{
			casTest = casillas[puntos.get(i).x][puntos.get(i).y];
			if (casTest.getPieza() != null && casTest.getColor() == color)
				puntos.remove(i--);
		}
		
		/* Quitar posiciones inválidas por jaque propio */
		if (nivelJaques == 0)
		{
			Casilla iniTemp = c.clon(), limpia = new Casilla(), rey = buscarRey(color);
			
			limpia.x = c.x;
			limpia.y = c.y;
			nivelJaques++;
			
			for (int i=0; i<puntos.size(); i++)
			{
				casTest = casillas[puntos.get(i).x][puntos.get(i).y];
				
				iniTemp.x = casTest.x;
				iniTemp.y = casTest.y;
				
				casillas[casTest.x][casTest.y] = iniTemp;
				casillas[c.x][c.y] = limpia;
				
				if (c.getPieza().getTipo() == Pieza.REY)
					rey = iniTemp;
				
				if (esJaque(rey))
					puntos.remove(i--);
				
				casillas[c.x][c.y] = c;
				casillas[casTest.x][casTest.y] = casTest;
			}
			
			nivelJaques--;
		}
		
		return puntos;
	}
	
	public void promocionarPeon(Casilla ini, Casilla fin, Pieza p)
	{
		try
		{
			AudioPlayer.player.start(new AudioStream(new FileInputStream("sonidos/can-to-table-1.wav")));
		}
		catch (FileNotFoundException fnfe)
		{ }
		catch (IOException ioe)
		{ }
		
		fin.setCasilla(p, ini.getColor(), fin.x, fin.y);
		ini.setCasilla(null, true, ini.x, ini.y);
	}
	
	public void quitaListeners(Partida p)
	{
		for (int i=0; i<8; i++)
			for (int j=0; j<8; j++)
				casillas[i][j].removeMouseListener(p);
	}
	
	public void setSeleccionada(Casilla c)
	{
		c.actualizarImagen(Casilla.Estado.Seleccionada);
	}
}
