package logica;

import graficos.Escenario;
import graficos.Imagen;
import gui.Principal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import logica.piezas.Pieza;

public class Partida implements ActionListener, MouseListener
{
	private Oponente oponente = null;
	private Tablero tablero = null;
	private Imagen imagenInfo = null;
	private JTextArea textoInfo = null;
	private JTextArea chat = null;
	private JTextField mensaje = null;
	private boolean fin = false;
	private Casilla casillaSelec = null;
	private boolean turno = Pieza.BLANCAS;
	
	public Partida(Principal p, Oponente op) throws Exception
	{
		super();
		this.tablero = new Tablero();
		this.oponente = op;
		this.tablero.dameListeners(this);
		this.tablero.girarTablero();
	}
	
	public Partida(Principal p, Tablero t, Oponente op)
	{
		super();
		this.tablero = t;
		this.oponente = op;
	}

	/*@Override
	public void run()
	{
		while (imagenInfo == null || textoInfo == null || chat == null || mensaje == null);
		
		while (!fin)
		{
			
		}
	}*/
	
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
				tablero.mover(casillaSelec, casillaPulsada);
				/*try
				{
					if (tablero.esJaque(turno))
						JOptionPane.showMessageDialog(chat, "Cuidado! Estas en Jaque.");
				}
				catch (Exception e){}*/

				tablero.limpiarPosibles();
				turno = !turno;
				//tablero.girarTablero();
			}
			else
			{
				tablero.limpiarPosibles();
				
				if (casillaPulsada.getPieza() != null && casillaPulsada.getColor() == turno)
				{
					try
					{
						this.imagenInfo.setImagen(casillaPulsada.getPieza().getNombre(), turno);
						this.textoInfo= casillaPulsada.getPieza().getInformacion();
					}
					catch (Exception e){}
					tablero.setSeleccionada(casillaPulsada);
					tablero.marcarPosibles(casillaPulsada);
					casillaSelec = casillaPulsada;
				}
				else 
				{
					try
					{
						this.imagenInfo.setImagen(null, turno);
						this.textoInfo= null;
					}
					catch (Exception e){}
				}
			}
		}
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
