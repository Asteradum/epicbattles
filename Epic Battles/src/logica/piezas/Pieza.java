package logica.piezas;

import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

import logica.Casilla;

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
	
	public abstract int getValor();
	
	public abstract Es getTipo();
	
	public abstract String getNombre();
	
	public abstract Vector<Point> getPosibles(Casilla[][] casillas, Point p);
	
	public Image getImagen(boolean color)
	{
		if (color == Pieza.BLANCAS)
			return this.blanca;
		else
			return this.negra;
	}
	
	public Image getImagenInfo(boolean color)
	{
		if (color == Pieza.BLANCAS)
			return this.imagenBlanca;
		else
			return this.imagenNegra;
	}
	
	public static String getNombreColor(boolean color)
	{
		if (color == Pieza.BLANCAS)
			return "Blancas";
		else
			return "Negras";
	}
}
