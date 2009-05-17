package epicbattles;

import gui.Principal;

/**
 * Contiene la función main
 * @author Alberto y Alvaro
 */
public abstract class Main
{
	/**
	 * Punto de entrada a la aplicación
	 * @param args Argumentos (no usado)
	 */
	public static void main(String[] args)
	{
		new Principal().setVisible(true);
	}
}
