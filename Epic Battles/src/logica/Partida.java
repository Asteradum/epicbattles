package logica;

import graficos.Escenario;
import graficos.Imagen;
import gui.Principal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Partida extends Thread implements ActionListener, MouseListener
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
			e.printStackTrace();
		}
		
		this.oponente = op;
		this.tablero.dameListeners(this);
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
	
	public Escenario getEscenario()
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

	@Override
	public void actionPerformed(ActionEvent ae)
	{
	}

	@Override
	public void mouseClicked(MouseEvent me) 
	{
		if (me.getSource() instanceof Casilla)
		{
			Casilla casillaPulsada = (Casilla)me.getSource();
			
			if (casillaPulsada.esMarcada())
			{
				//this.tablero.mover(casillaPulsada);
			}
			else
			{
				this.tablero.limpiarPosibles();
				
				if (casillaPulsada.getPieza() != null)
					this.tablero.posibles(casillaPulsada);
			}
		}
		else this.tablero.limpiarPosibles();
		
	}

	@Override
	public void mouseEntered(MouseEvent e) { }
	@Override
	public void mouseExited(MouseEvent e) { }
	@Override
	public void mousePressed(MouseEvent e) { }
	@Override
	public void mouseReleased(MouseEvent e) { }
}
