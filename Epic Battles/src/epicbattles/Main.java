package epicbattles;

import basedatos.GestorBaseDatos;
import gui.Principal;

/**
 * @author Alberto y Alvaro
 *
 */
public final class Main
{
	public static void main(String[] args)
	{	
		System.out.println("Hola mundo");
		new GestorBaseDatos();
		new Principal().setVisible(true);	
	}
}
