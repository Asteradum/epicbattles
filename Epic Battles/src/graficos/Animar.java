package graficos;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JWindow;

/**
 * Clase grafica que se superpone al tablero y representa a la pieza moviendose sobre él.
 * @author Alberto y Alvaro
 */
public class Animar extends JWindow
{
	private static final long serialVersionUID = -4459309244964766936L;
	private final int steps = 25;
	
	/**
	 * Constructor de la clase con el bucle de animación y un dispose() al final.
	 * @param ini Punto inicial.
	 * @param fin Punto final.
	 * @param imagen Imagen a mostrar.
	 */
	public Animar(Component rel, Point ini, Point fin)
	{
		super();
		
		float dx = (float)(fin.x - ini.x) / steps;
		float dy = (float)(fin.y - ini.y) / steps;
		
		this.setSize(new Dimension(40, 40));
		this.setLocationRelativeTo(rel);
		this.setVisible(true);
		this.setAlwaysOnTop(true);
		
		for (int i=0; i<steps; i++)
		{
			setLocation((int)(ini.x + dx*i), (int)(ini.y + dy*i));
			
			try { Thread.sleep(50); }
			catch (InterruptedException e) { }
		}
		
		try { Thread.sleep(100); }
		catch (InterruptedException e) { }
		
		dispose();
	}
}
