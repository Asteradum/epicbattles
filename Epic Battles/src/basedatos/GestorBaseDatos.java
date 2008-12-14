package basedatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GestorBaseDatos
{
	private Connection conexion = null;
	
	public GestorBaseDatos()
	{
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql:Driver=EpicBattles";
		
		try
		{
			Class.forName(driver);
			conexion = DriverManager.getConnection(url, "", "");
		}
		catch (ClassNotFoundException cnfe)
		{
			cnfe.printStackTrace();
		}
		catch (SQLException sqle)
		{
			sqle.printStackTrace();
		}
	}	
}
