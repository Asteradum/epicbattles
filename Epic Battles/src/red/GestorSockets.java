package red;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class GestorSockets extends Thread
{
	public static enum Estado
	{
		Esperando, Servidor, Cliente, Finalizado
	}
	
	private Estado estado = Estado.Esperando;
	private String peerIP = null;
	private Socket socket = null;
	private ObjectOutputStream salida;
	private ObjectInputStream entrada;
	
	public GestorSockets()
	{
		super();
	}
	
	/*public GestorSockets(String ip)
	{
		super();
		this.peerIP = ip;
	}*/

	public int enviar(String message)
	{
		return 0;
	}
	
	public void conectar(String ip) throws IOException
	{
		socket = new Socket(InetAddress.getByName(ip), 12345);
	}
	
	@Override
	public void run()
	{
		while (estado == Estado.Esperando)
		{
			
		}
		
		while (estado == Estado.Servidor)
		{
			
		}
		
		while (estado == Estado.Cliente)
		{
			
		}
	}
}
