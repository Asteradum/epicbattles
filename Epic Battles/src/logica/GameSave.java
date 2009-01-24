package logica;

import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.Vector;

public abstract class GameSave
{
	private static GregorianCalendar gc = new GregorianCalendar(TimeZone.getDefault());
	public static String ip;
	public static long jug1, jug2;
	public static Vector<String> vs;
	
	public static long ahora()
	{
		return gc.getTimeInMillis();
	}
	
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
