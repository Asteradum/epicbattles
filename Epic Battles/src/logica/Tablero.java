package logica;

import graficos.Animar;
import gui.Escenario;

import java.awt.Color;
import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JPanel;

import logica.Casilla.Estado;
import logica.piezas.Alfil;
import logica.piezas.Caballo;
import logica.piezas.Peon;
import logica.piezas.Pieza;
import logica.piezas.Reina;
import logica.piezas.Rey;
import logica.piezas.Torre;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * Almacena las Casillas y provee de métodos a nivel de tablero para la Partida.
 * @author Alberto y Alvaro
 */
public class Tablero
{
	private static final long serialVersionUID = -8743924243868541721L;
	private Casilla[][] casillas = null;
	private Casilla enJaque = null;
	private Escenario escenario = null;
	private Vector<String> movimientos = null;
	private int nivelJaques = 0;
	
	/**
	 * Crea un tablero nuevo.
	 * @param red Especifica si es un juego en red o no.
	 * @throws Exception
	 */
	public Tablero(boolean red) throws Exception
	{
		super();
		this.escenario = new Escenario(red);
		this.casillas = escenario.getCasillas();
		generarTablero();
		this.movimientos = new Vector<String>(10, 10);
		GameSave.vs = movimientos;
	}
	
	/**
	 * Crea un tablero a partir de un Vector de movimientos en el tablero.
	 * @param movs Vector de movimientos.
	 * @param red Especifica si es un juego en red o no.
	 * @throws Exception
	 */
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
	
	/**
	 * Busca y devuelve el rey del color indicado.
	 * @param color Color del rey a buscar.
	 * @return Casilla en la que se encuentra.
	 */
	private Casilla buscarRey(boolean color)
	{
		boolean enc = false;
		Casilla c = null;
		
		for (int i=0; !enc && i<8; i++)
			for (int j=0; !enc && j<8; j++)
			{
				c = casillas[i][j];
				if (c.getPieza() != null)
					if (c.getPieza().getTipo() == Pieza.Es.Rey && c.getColor() == color)
						enc = true;
			}
		
		return c;
	}
	
	/**
	 * Comprueba si se produce jaque y en caso afirmativo marca la Casilla con un fondo rojo.
	 * @param color Color del rey que se debe buscar.
	 * @return True si hay jaque, False si no lo hay.
	 */
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
	
	/**
	 * Cuenta los puntos por piezas dado un color.
	 * @param color Color del cual se desea conocer los puntos.
	 * @return Número de puntos de ese color.
	 */
	public int contarPuntos(boolean color)
	{
		int suma = 0;
		Casilla c;
		
		for (int i=0; i<8; i++)
			for (int j=0; j<8; j++)
			{
				c = casillas[i][j];
				
				if (c.getPieza() != null && c.getColor() == color)
					suma += c.getPieza().getValor();
			}
		
		return suma;
	}
	
	/**
	 * Hace que la Partida sea Listener de todas las Casillas.
	 * @param p Una referencia a la Partida.
	 */
	public void dameListeners(Partida p)
	{
		for (int i=0; i<8; i++)
			for (int j=0; j<8; j++)
				casillas[i][j].addMouseListener(p);
	}
	
	/**
	 * Se encarga de mover las piezas de forma que que se produzca el movimiento "en passant". 
	 * @param ini La Casilla inicial.
	 * @param fin La Casilla final.
	 */
	public void enpassant(Casilla ini, Casilla fin)
	{
		mover(casillas[fin.x+(ini.getColor() ? -1 : 1)][fin.y], fin, false);
		mover(ini, fin, true);
	}
	
	/**
	 * Se encarga de mover las piezas de forma que que se produzca el enroque.
	 * @param rey Casilla correspondiente al rey que enroca.
	 * @param fin Casilla final del rey.
	 */
	public void enrocar(Casilla rey, Casilla fin)
	{
		int sentido = (fin.y > rey.y ? 1 : -1);
		
		mover(rey, fin, true);
		mover(casillas[fin.x][fin.y+sentido], casillas[fin.x][fin.y-sentido], false);
	}
	
	/**
	 * Comprueba si una Casilla está amenazada por un color dado.
	 * @param c La Casilla dada.
	 * @param color Especifica el color que puede amenazar la posición.
	 * @return True si está amenazada, False si no lo está.
	 */
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
	
	/**
	 * Comprueba si cierto color tiene a su rey amenazado (jaque).
	 * @param color Color del rey que recibe el jaque.
	 * @return La Casilla del rey si está amenazado, null en caso contrario.
	 */
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
	
	/**
	 * Comprueba si cierto rey está amenazado por alguna pieza del rival.
	 * @param rey Casilla que contiene al rey.
	 * @return True si está amenazado, False en caso contrario.
	 */
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
	
	/**
	 * Comprueba si cierto color está en jaque mate (no tiene movimientos validos en ninguna de sus piezas.)
	 * @param color Color del bando a probar.
	 * @return True si es jaque mate, False en caso contrario.
	 */
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
	
	/**
	 * Llena el array de Casillas de las posiciones iniciales en un ajedrez normal (partida nueva).
	 * @throws Exception
	 */
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
	
	/**
	 * @return Una referencia a Escenario.
	 */
	public Escenario getEscenario()
	{
		return escenario;
	}
	
	/**
	 * @return Si el Vector de movimientos tiene algun elemento, devuelve una referencia a él. En caso contrario devuelve null.
	 */
	public Vector<String> getMovimientos()
	{
		if (movimientos != null)
			return movimientos;
		else
			return null;
	}
	
	/**
	 * Cuenta el numero de movimientos hechos por una faccion dada.
	 * @param color Color de la faccion.
	 * @return Número de movimientos hechos.
	 */
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
	
	/**
	 * Reorganiza las Casillas en su Escenario de tal forma que el color dado tenga al enemigo de frente.
	 * @param color Color de la faccion.
	 */
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
	
	/**
	 * Recorre todas las Casillas para dejarlas en Estado Inactivo
	 */
	public void limpiarPosibles()
	{
		Casilla c;
		for (int i=0; i<8; i++)
			for (int j=0; j<8; j++)
			{
				c = casillas[i][j];
				if (c.esMarcada() || c.esSeleccionada() || c.esEnrocable() || c.esPromocion() || c.esEnpassant())
					casillas[i][j].actualizarImagen();
			}
	}
	
	/**
	 * Dada una Casilla, obtiene sus movimientos válidos y marca en el tablero el tipo de Estado que tienen.
	 * @param c La Casilla dada.
	 */
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
		
		if (c.getPieza().getTipo() == Pieza.Es.Peon)
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
		else if (c.getPieza().getTipo() == Pieza.Es.Rey)
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
							else if (temp.getPieza().getTipo() == Pieza.Es.Torre && temp.getColor() == color)
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
	
	/**
	 * Mueve en el tablero una pieza desde una Casilla inicial a una final.
	 * @param ini La Casilla inicial.
	 * @param fin La Casilla final.
	 * @param consta Especifica si el movimiento debe trasladarse al Vector de movimientos.
	 */
	public void mover(Casilla ini, Casilla fin, boolean consta)
	{
		Casilla temp = ini.clon();
		
		try
		{
			AudioPlayer.player.start(new AudioStream(new FileInputStream("sonidos/can-to-table-1.wav")));
		}
		catch (FileNotFoundException fnfe) { }
		catch (IOException ioe) { }
		
		if (consta)
			movimientos.add
			(
				String.valueOf((char)(ini.y+97)) +
				(ini.x+1) +
				(fin.getPieza() == null ? "-" : "x") +
				String.valueOf((char)(fin.y+97)) +
				(fin.x+1)
			);
		
		ini.setCasilla(null, true, ini.x, ini.y);

		/* <animar> */
		new Animar(ini, ini.getLocationOnScreen(), fin.getLocationOnScreen());
		/* </animar> */
		
		fin.setCasilla(temp.getPieza(), temp.getColor(), fin.x, fin.y);
	}
	
	/**
	 * Recibe un String que representa un movimiento y lo plasma en el tablero.
	 * @param mov String de movimiento.
	 */
	private void mover(String mov)
	{
		char[] letras = mov.toCharArray();
		
		Casilla ini = casillas[Character.getNumericValue(letras[1])-1][letras[0]-97];
		Casilla fin = casillas[Character.getNumericValue(letras[4])-1][letras[3]-97];
		
		fin.setCasilla(ini.getPieza(), ini.getColor(), fin.x, fin.y);
		ini.setCasilla(null, true, ini.x, ini.y);
	}
	
	/**
	 * Dada una Casilla obtiene un Vector con sus destinos válidos.
	 * @param c La Casilla dada.
	 * @return Un Vector de puntos válidos.
	 */
	private Vector<Point> posibles(Casilla c)
	{
		Vector<Point> puntos = c.getPieza().getPosibles(casillas, c.getPosicion());
		
		/* Quitar posiciones inválidas por jaque propio */
		if (nivelJaques == 0)
		{
			Casilla iniTemp = c.clon(), limpia = new Casilla(), rey = buscarRey(c.getColor()), casTest;
			
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
				
				if (c.getPieza().getTipo() == Pieza.Es.Rey)
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
	
	/**
	 * Refleja en el tablero el movimiento de un peón al llegar a la ultima fila.
	 * @param ini La Casilla inicial.
	 * @param fin La Casilla final.
	 * @param p Pieza en la que se transforma.
	 */
	public void promocionarPeon(Casilla ini, Casilla fin, Pieza p)
	{
		try
		{
			AudioPlayer.player.start(new AudioStream(new FileInputStream("sonidos/can-to-table-1.wav")));
		}
		catch (FileNotFoundException fnfe) { }
		catch (IOException ioe) { }
		
		movimientos.add
		(
			String.valueOf((char)(ini.y+97)) +
			(ini.x+1) +
			(fin.getPieza() == null ? "-" : "x") +
			String.valueOf((char)(fin.y+97)) +
			(fin.x+1)
		);
		
		fin.setCasilla(p, ini.getColor(), fin.x, fin.y);
		ini.setCasilla(null, true, ini.x, ini.y);
	}
	
	/**
	 * Retira a Partida todos los Listeners de las Casillas.
	 */
	public void quitaListeners(Partida p)
	{
		for (int i=0; i<8; i++)
			for (int j=0; j<8; j++)
				casillas[i][j].removeMouseListener(p);
	}
	
	/**
	 * Marca a una Casilla como Seleccionada y la actualiza.
	 * @param c La Casilla a ser marcada.
	 */
	public void setSeleccionada(Casilla c)
	{
		c.actualizarImagen(Casilla.Estado.Seleccionada);
	}
}
