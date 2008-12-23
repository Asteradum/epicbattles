package basedatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

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
	
	public static Hashtable<Long,String> leerPartidas() throws SQLException
	{
		Connection conexion = cargar();
		Hashtable<Long,String> partidas = new Hashtable<Long,String>();
		
		if (conexion != null)
		{
			Statement stmt = conexion.createStatement();
			String query = "SELECT p.id_partida, p.fechahora, j1.nom_jugador,  j2.nom_jugador " +
					"FROM Partida p, Jugador j1, Jugador j2 " +
					"WHERE p.id_jug1 = j1.id_jugador AND p.id_jug2 = j2.id_jugador";
			ResultSet rs = stmt.executeQuery(query);
			
			Long id;
			String fecha, jug1, jug2;
			
			while(rs.next())
			{
				id = rs.getLong(1);
				fecha = new Date(Long.valueOf(rs.getString(2))).toLocaleString() + " || ";
				jug1 = rs.getString(3);
				jug2 = rs.getString(4);
				
				partidas.put(id, fecha+" "+jug1+" vs. "+jug2+" ");
			}
		}		
		else
		{
			throw new SQLException("No se pueden leer partidas");
		}
		
		conexion.close();
		return partidas;
	}
	
	public static Vector<String> leerPartida(Long id) throws SQLException
	{
		Connection conexion = cargar();
		Vector<String> movimientos = new Vector<String>();
		
		if (conexion != null)
		{
			Statement stmt = conexion.createStatement();
			String query = "SELECT p.movimientos " +
					"FROM Partida p " +
					"WHERE p.id_partida = " + id.toString();
			ResultSet rs = stmt.executeQuery(query);
			
			String[] movs = rs.getString("movimientos").split(",");			
			for (int i=0; i<movs.length; i++)
			{
				movimientos.add(movs[i]);
			}
		}		
		else
		{
			throw new SQLException("No se pueden cargar la partida especificada");
		}
		
		conexion.close();
		return movimientos;
	}
	
	public static void guardarPartida(Date fecha, Integer jug1, Integer jug2, Vector<String> movs, String ip) throws SQLException
	{
		Connection conexion = cargar();
		
		if (conexion != null)
		{
			String movimientos = "";
			
			for (String mov: movs)
			{
				movimientos += mov + ",";
			}
			
			Statement stmt = conexion.createStatement();
			String update = "INSERT INTO Partida VALUES ("+
				fecha.toString() + ", "+
				jug1.toString() + ", " +
				jug2.toString() + ", " +
				movimientos + ", " +
				ip + ")";
			
			stmt.executeUpdate(update);
		}		
		else
		{
			throw new SQLException("No se puede guardar la partida");
		}
		
		conexion.close();
	}
	
	public static void borrarPartida(Long id) throws SQLException
	{
Connection conexion = cargar();
		
		if (conexion != null)
		{
		
		}		
		else
		{
			throw new SQLException("No se puede guardar la partida");
		}
		
		conexion.close();
	}
}
