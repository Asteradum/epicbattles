package epicbattles;

import juego.interfaces.Principal;


/**
 * 
 */

/**
 * @author Alberto y Alvaro
 *
 */
public final class Main
{
	private static Principal start = null;
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{	
		System.out.println("Hola mundo");
		new Principal().setVisible(true);
		//start = new Principal();
		//start.setVisible(false);
	}

}
