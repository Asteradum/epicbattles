package logica.piezas;

import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

import logica.Casilla;

/**
 * Clase abstracta que se encarga de cargar las imagenes de cada pieza.
 * @author Alberto y Alvaro.
 */
public abstract class Pieza
{
	public static enum Es
	{
		Peon, Alfil, Caballo, Torre, Reina, Rey
	}
	
	public static final boolean BLANCAS = true;
	public static final boolean NEGRAS = false;
	public static final int VAL_PEON = 1;
	public static final int VAL_ALFIL = 3;
	public static final int VAL_CABALLO = 3;
	public static final int VAL_TORRE = 5;
	public static final int VAL_REINA = 9;
	public static final int VAL_REY = 10;
	
	protected Image blanca = null;
	protected Image negra = null;
	protected Image imagenBlanca = null;
	protected Image imagenNegra = null;
	
	/**
	 * Carga las Image de cada Pieza.
	 * @throws Exception Si no se encuentran las imagenes.
	 */
	public Pieza() throws Exception
	{
		super();
		
		try
		{
			this.blanca = ImageIO.read(new File("imagenes/piezas/Blanco" + this.getNombre() + ".png"));
		}
		catch (IOException ioe)
		{
			throw new Exception("No se ha encontrado " + this.getNombre() + " blanco");
		}
	
		try
		{
			this.negra = ImageIO.read(new File("imagenes/piezas/Negro" + this.getNombre() + ".png"));
		}
		catch (IOException ioe)
		{
			throw new Exception("No se ha encontrado " + this.getNombre() + " negro");
		}

		try
		{
			this.imagenBlanca = ImageIO.read(new File("imagenes/Imagen/Imagen" + this.getNombre() + "Blanco.png"));
		}
		catch (IOException ioe)
		{
			throw new Exception("No se ha encontrado la imagen " + this.getNombre() + " blanco");
		}
	
		try
		{
			this.imagenNegra = ImageIO.read(new File("imagenes/Imagen/Imagen" + this.getNombre() + "Negro.png"));
		}
		catch (IOException ioe)
		{
			throw new Exception("No se ha encontrado la imagen " + this.getNombre() + " negro");
		}
	}
	
	/**
	 * @return El valor de la Pieza.
	 */
	public abstract int getValor();
	
	/**
	 * @return El tipo de Pieza.
	 */
	public abstract Es getTipo();
	
	/**
	 * @return El nombre de la Pieza.
	 */
	public abstract String getNombre();
	
	/**
	 * Dado un array de Casillas que es el tablero, devuelve un Vector con sus movimientos posibles.
	 * @param casillas El array de Casillas.
	 * @param p La posición de la Pieza de la cual sacar movimientos posibles.
	 * @return Un Vector con sus movimientos posibles.
	 */
	public abstract Vector<Point> getPosibles(Casilla[][] casillas, Point p);
	
	/**
	 * Dado el color, devuelve una imagen de la Pieza.
	 * @param color El color de la Pieza.
	 * @return La imagen de la Pieza.
	 */
	public Image getImagen(boolean color)
	{
		if (color == Pieza.BLANCAS)
			return this.blanca;
		else
			return this.negra;
	}
	
	/**
	 * Dado el color, devuelve una imagen representativa de la Pieza.
	 * @param color El color de la Pieza.
	 * @return La imagen representativa de la Pieza.
	 */
	public Image getImagenInfo(boolean color)
	{
		if (color == Pieza.BLANCAS)
			return this.imagenBlanca;
		else
			return this.imagenNegra;
	}
	
	/**
	 * Dado un color, devuelve su nombre.
	 * @param color El color del cual sacar el nombre.
	 * @return Un String con el nombre del color.
	 */
	public static String getNombreColor(boolean color)
	{
		if (color == Pieza.BLANCAS)
			return "Blancas";
		else
			return "Negras";
	}
}
