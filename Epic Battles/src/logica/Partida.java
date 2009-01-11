package logica;

import graficos.Escenario;
import graficos.Imagen;
import gui.Principal;
import gui.SeleccionarPieza;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import logica.piezas.Pieza;

public class Partida implements ActionListener, MouseListener, KeyListener
{
	private Casilla casillaSelec = null;
	public JTextArea chat = null;
	private Imagen imagenInfo = null;
	public JTextField mensaje = null;
	private Oponente oponente = null;
	private Tablero tablero = null;
	private JTextArea textoInfo = null;
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
	
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		if (chat != null && mensaje != null)
		{
			chat.append(mensaje.getText() + "\n");
			mensaje.setText("");
		}
	}
	
	public Escenario getEscenario()
	{
		return tablero.getEscenario();
	}

	@Override
	public void mouseClicked(MouseEvent me) 
	{
		if (me.getSource() instanceof Casilla)
		{
			boolean jaqueMate = false;
			Casilla casillaPulsada = (Casilla)me.getSource();
			
			if (casillaPulsada.esMarcada())
			{
				tablero.mover(casillaSelec, casillaPulsada);
				jaqueMate = tablero.comprobarJaques(!turno);
				tablero.limpiarPosibles();
				imagenInfo.setImagen(null);
				textoInfo.setText("");
				turno = !turno;
			}
			else if(casillaPulsada.esPromocion())
			{
				SeleccionarPieza sp = new SeleccionarPieza((Frame) getEscenario().getRootPane().getParent(), casillaSelec.getColor());
				
				tablero.promocionarPeon(casillaSelec, casillaPulsada, sp.getOpcion());
				jaqueMate = tablero.comprobarJaques(!turno);
				tablero.limpiarPosibles();
				imagenInfo.setImagen(null);
				textoInfo.setText("");
				turno = !turno;
			}
			else
			{
				tablero.limpiarPosibles();
				
				if (casillaPulsada.getPieza() != null && casillaPulsada.getColor() == turno)
				{
					String nombrePieza = casillaPulsada.getPieza().getNombre();
					String tipoPieza = String.valueOf(casillaPulsada.getPieza().getTipo());
					String colorPieza = (turno ? "blanco" : "negro");
					
					imagenInfo.setImagen(casillaPulsada.getPieza().getImagenInfo(turno));
					textoInfo.setText("Nombre: " + nombrePieza + "\nValor: " + tipoPieza + "\nColor: " + colorPieza + '\n');
					
					tablero.setSeleccionada(casillaPulsada);
					tablero.marcarPosibles(casillaPulsada);
					casillaSelec = casillaPulsada;
				}
			}
			
			if (jaqueMate)
			{
				System.out.println("Jaque mate");
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

	public void setChat(JTextArea chat)
	{
		this.chat = chat;
	}

	public void setImagenInfo(Imagen i)
	{
		this.imagenInfo = i;
	}
	
	public void setMensajeChat(JTextField m)
	{
		this.mensaje = m;
	}
	
	public void setTextoInfo(JTextArea ti)
	{
		this.textoInfo = ti;
	}

	@Override
	public void keyPressed(KeyEvent arg0)
	{
		if (arg0.getKeyCode() == KeyEvent.VK_ENTER)
		{
			chat.append(mensaje.getText() + "\n");
			mensaje.setText("");
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) { }

	@Override
	public void keyTyped(KeyEvent arg0) { }
}
