package epicbattles;

import java.util.Date;

import gui.Principal;

/**
 * @author Alberto y Alvaro
 *
 */
public final class Main
{
	public static void main(String[] args)
	{
		System.out.println(System.currentTimeMillis());
		new Principal().setVisible(true);
	}
}
