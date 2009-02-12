package basedatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

/**
 * Esta clase abstracta se usa para leer, cargar, eliminar y guardar partidas
 * @author Alberto y Alvaro
 */
public abstract class GestorBaseDatos
{
	private static final String driver = "Microsoft Access Driver (*.mdb)";
	private static final String ruta = "epicbattles.MDB";
	
	/**
	 * Borra la partida cuya clave es id
	 * @param id Clave de la tabla
	 * @throws SQLException
	 */
	public static void borrarPartida(Long id) throws SQLException
	{
		Connection conexion = cargar();
		
		if (conexion != null)
		{
			Statement stmt = conexion.createStatement();
			String update = "DELETE FROM Partida where fechahora='" + id.toString() + "'";
			
			stmt.executeUpdate(update);
			
			stmt.close();
			conexion.close();
		}
		else
		{
			throw new SQLException("No se puede borrar la partida");
		}
	}
	
	/**
	 * Crea una conexion a la base de Datos y devuelve la referencia.
	 * @return Una referencia Connection
	 */
	private static Connection cargar()
	{
		String db = "jdbc:odbc:Driver={"+ driver +"};DBQ=" + ruta;
		Connection conexion = null;
		
		try
		{
			conexion =  DriverManager.getConnection(db, "", "");
		}
		catch (SQLException sqle)
		{
			sqle.printStackTrace();
		}
		
		return conexion;
	}
	
	/**
	 * Guarda la partida, almacenando la fecha, los jugadores, los movimientos de la partida y la ip
	 * @param fecha Fecha ahora en milisegundos
	 * @param jug1 Nombre del jugador 1
	 * @param jug2 Nombre del jugador 2
	 * @param movs String que contiene todos los movimientos de la partida
	 * @param ip IP remota con la que se ha jugado
	 * @throws SQLException
	 */
	public static void guardarPartida(Long fecha, Long jug1, Long jug2, String movs, String ip) throws SQLException
	{
		Connection conexion = cargar();
		
		if (conexion != null)
		{
			Statement stmt = conexion.createStatement();
			String update = "INSERT INTO Partida VALUES ('"+
				fecha.toString() + "', " +
				jug1 + ", " +
				jug2 + ", '" +
				movs + "', '" +
				ip + "')";
			
			stmt.executeUpdate(update);
			
			stmt.close();
			conexion.close();
		}
		else
		{
			throw new SQLException("No se puede guardar la partida");
		}
	}
	
	/**
	 * Lee los movimientos de la partida a partir de un id
	 * @param id Clave de la tabla
	 * @return Un Vector de String
	 * @throws SQLException
	 */
	public static Vector<String> leerPartida(Long id) throws SQLException
	{
		Connection conexion = cargar();
		Vector<String> movimientos = new Vector<String>();
		
		if (conexion != null)
		{
			Statement stmt = conexion.createStatement();
			String query = "SELECT p.movimientos, p.ip " +
					"FROM Partida p " +
					"WHERE p.fechahora = '" + id.toString() + "'";
			ResultSet rs = stmt.executeQuery(query);
			
			if (rs.next())
			{
				movimientos.add(rs.getString(2));
				
				String[] movs = rs.getString(1).split(",");
				for (int i=0; i<movs.length; i++)
				{
					movimientos.add(movs[i]);
				}	
			}
			
			rs.close();
			stmt.close();
			conexion.close();
		}
		else
		{
			throw new SQLException("No se pueden cargar la partida especificada");
		}
		
		return movimientos;
	}
	
	/**
	 * Lee todas las partidas y almacena su fecha en milisegundos y en String
	 * @param red Si las partidas a buscar son en red o no
	 * @return Una hashtable con clave fecha milisegundos y valor fecha String
	 * @throws SQLException
	 */
	@SuppressWarnings("deprecation")
	public static Hashtable<Long,String> leerPartidas(boolean red) throws SQLException
	{
		Connection conexion = cargar();
		Hashtable<Long,String> partidas = new Hashtable<Long,String>();
		
		if (conexion != null)
		{
			Statement stmt = conexion.createStatement();
			String query = "SELECT p.fechahora, j1.nom_jugador,  j2.nom_jugador, p.ip " +
					"FROM Partida p, Jugador j1, Jugador j2 " +
					"WHERE p.id_jug1 = j1.id_jugador AND p.id_jug2 = j2.id_jugador";
			ResultSet rs = stmt.executeQuery(query);
			
			String fecha, jug1, jug2;
			long fechaLong;
			
			while(rs.next())
			{
				fechaLong = Long.valueOf(rs.getString(1)).longValue();
				fecha = new Date(fechaLong).toLocaleString() + " || ";
				jug1 = rs.getString(2);
				jug2 = rs.getString(3);
				
				if (red)
				{
					if (rs.getString(4) != "")
						partidas.put(fechaLong, fecha+" "+jug1+" vs. "+jug2+" ");
				}
				else
				{
					partidas.put(fechaLong, fecha+" "+jug1+" vs. "+jug2+" ");
				}
			}
			
			rs.close();
			stmt.close();
			conexion.close();
		}
		else
		{
			throw new SQLException("No se pueden leer partidas");
		}
		
		return partidas;
	}
}
