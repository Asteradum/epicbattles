package logica.piezas;

import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

public abstract class Pieza
{
	public static final boolean BLANCAS = true;
	public static final boolean NEGRAS = false;
	public static final int PEON = 1;
	public static final int ALFIL = 2;
	public static final int CABALLO = 3;
	public static final int TORRE = 5;
	public static final int REINA = 9;
	public static final int REY = 10;
	
	protected Image blanca = null;
	protected Image negra = null;
	protected Image imagenBlanca = null;
	protected Image imagenNegra = null;

	public Pieza() throws Exception
	{
		super();
		
		if (blanca == null)
			try
			{
				this.blanca = ImageIO.read(new File("imagenes/piezas/Blanco" + this.getNombre() + ".png"));
			}
			catch (IOException ioe)
			{
				throw new Exception("No se ha encontrado " + this.getNombre() + " blanco");
			}
		
		if (negra == null)
			try
			{
				this.negra = ImageIO.read(new File("imagenes/piezas/Negro" + this.getNombre() + ".png"));
			}
			catch (IOException ioe)
			{
				throw new Exception("No se ha encontrado " + this.getNombre() + " negro");
			}
	
		if (imagenBlanca == null)
			try
			{
				this.imagenBlanca = ImageIO.read(new File("imagenes/Imagen/Imagen" + this.getNombre() + "Blanco.png"));
			}
			catch (IOException ioe)
			{
				throw new Exception("No se ha encontrado la imagen " + this.getNombre() + " blanco");
			}
		
		if (imagenNegra == null)
			try
			{
				this.imagenNegra = ImageIO.read(new File("imagenes/Imagen/Imagen" + this.getNombre() + "Negro.png"));
			}
			catch (IOException ioe)
			{
				throw new Exception("No se ha encontrado la imagen " + this.getNombre() + " negro");
			}
	}
	
	public abstract int getTipo();
	
	public abstract String getNombre();
	
	public abstract Vector<Point> getPosibles(Point p);
	
	public Image getImagen(boolean color)
	{
		if (color == Pieza.BLANCAS)
		{
			return this.blanca;
		}
		else
		{
			return this.negra;
		}
	}
	
	public Image getImagenInfo(boolean color)
	{
		if (color == Pieza.BLANCAS)
		{
			return this.imagenBlanca;
		}
		else
		{
			return this.imagenNegra;
		}
	}
}
