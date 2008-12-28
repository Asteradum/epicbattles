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
	
	protected Image blanca;
	protected Image negra;

	public Pieza() throws Exception
	{
		super();
		
		try
		{
			this.blanca = ImageIO.read(new File("imagenes/Blanco" + this.getNombre() + ".png"));
		}
		catch (IOException e)
		{
			throw new Exception("No se ha encontrado " + this.getNombre() + " blanco");
		}
		
		try
		{
			this.negra = ImageIO.read(new File("imagenes/Negro" + this.getNombre() + ".png"));
		}
		catch (IOException e)
		{
			throw new Exception("No se ha encontrado " + this.getNombre() + " negro");
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
}
