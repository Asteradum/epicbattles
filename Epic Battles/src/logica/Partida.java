package logica;

import graficos.Imagen;
import gui.Principal;

import java.awt.Component;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Partida extends Thread
{
	private Oponente oponente = null;
	private Tablero tablero = null;
	private Imagen imagenInfo = null;
	private JTextArea textoInfo = null;
	private JTextArea chat = null;
	private JTextField mensaje = null;
	private boolean fin = false;
	
	public Partida(Principal p, Oponente op)
	{
		super();
		
		try
		{
			this.tablero = new Tablero();
		}
		catch (Exception e)
		{
			p.setHelp(e.getMessage());
		}
		
		this.oponente = op;
		this.start();
	}
	
	public Partida(Principal p, Tablero t, Oponente op)
	{
		super();
		this.tablero = t;
		this.oponente = op;
		this.start();
	}

	@Override
	public void run()
	{
		while (imagenInfo == null || textoInfo == null || chat == null || mensaje == null);
		
		while (!fin)
		{
			
		}
	}
	
	public void terminar()
	{
		fin = true;
	}
	
	public Component getEscenario()
	{
		return tablero.getEscenario();
	}

	public void setImagenInfo(Imagen i)
	{
		this.imagenInfo = i;
	}

	public void setTextoInfo(JTextArea ti)
	{
		this.textoInfo = ti;
	}

	public void setChat(JTextArea chat)
	{
		this.chat = chat;
	}

	public void setMensajeChat(JTextField m)
	{
		this.mensaje = m;
	}
}
