package logica;

import red.GestorSockets;

public class Red extends Thread implements Oponente
{
	private GestorSockets gestor = null;
	
	public Red(GestorSockets gs)
	{
		super();
		this.gestor = gs;
	}
	
	/*public Red(String ip)
	{
		super();
		this.gestor = new GestorSockets(ip);
	}*/

	@Override
	public void run()
	{
	}

	@Override
	public void decidir(Partida p)
	{
	}
}
