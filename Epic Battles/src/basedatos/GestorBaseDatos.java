package basedatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class GestorBaseDatos
{
	private static String driver = "Microsoft Access Driver (*.mdb)";
	private static String ruta = "epicbattles.MDB";	
	
	private static Connection cargar()
	{		
		String db = "jdbc:odbc:Driver={"+ driver +"};DBQ=" + ruta;
		Connection conexion = null;
		
		try
		{
			conexion =  DriverManager.getConnection( db, "", "");
		}
		catch (SQLException sqle)
		{
			sqle.printStackTrace();
		}
		
		return conexion;
	}
	
	public static String[] leerPartidas()
	{
		Connection conexion = cargar();
		String[] partidas = new String[0];
		
		if (conexion != null)
		{
			Statement stmt = null;
			ResultSet rs = null;
			
		}		
		return partidas;
	}	
}
