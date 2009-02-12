package logica;

import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.Vector;

/**
 * Es una clase abstracta con métodos y atributos estáticos que proporciona información necesaria para guardar el juego.
 * @author Alberto y Alvaro
 */
public abstract class GameSave
{
	private static GregorianCalendar gc = new GregorianCalendar(TimeZone.getDefault());
	public static String ip;
	public static long jug1, jug2;
	public static Vector<String> vs;
	
	/**
	 * Toma la hora actual del calendario gregoriano en la zona horaria del sistema.
	 * @return La fecha en milisegundos.
	 */
	public static long ahora()
	{
		return gc.getTimeInMillis();
	}
	
	/**
	 * Construye un String con los movimientos de la partida a partir de una referencia el Vector de movimientos.
	 * @return Un String con los movimientos de la partida.
	 */
	public static String crearMemo()
	{
		StringBuilder sb = new StringBuilder(512);
		
		for (String s: vs)
		{
			if (sb.length() > 0)
				sb.append(",");
			sb.append(s);
		}
		
		return sb.toString();
	}
}
