package gui;

import graficos.Imagen;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import logica.Partida;

class Juego extends JPanel
{
	private static final long serialVersionUID = 7325275995219105393L;
	private Principal parent = null;
	private ModoJuego juego = null;
	private Partida partida = null;
	
	public Juego(Principal parent, ModoJuego mj, Partida partida)
	{
		super();
		this.parent = parent;
		this.juego = mj;
		this.partida = partida;
		initialize();
	}	

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize()
	{
		this.setLayout(new BorderLayout());
		this.add(partida.getEscenario(), BorderLayout.CENTER);
		this.add(new Info(parent, juego, partida), BorderLayout.SOUTH);
	}
}

class Info extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 5535961802115944302L;
	private Principal parent = null;
	private ModoJuego juego = null;
	private Partida partida = null;
	private JTextArea texto = null;
	private JPanel botonera = null;
	private MiBoton bPausa = null;
	private JPanel datos = null;

	public Info(Principal parent, ModoJuego mj, Partida partida)
	{
		super();
		this.parent = parent;
		this.juego = mj;
		this.partida = partida;
		initialize();
		getBPausa().addActionListener(this);
		this.getTexto().setText("Bienvenido a EpicBattles");
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize()
	{
		this.setLayout(new BorderLayout());
		this.add(getDatos(), BorderLayout.CENTER);
		this.add(getBotonera(), BorderLayout.SOUTH);
	}

	/**
	 * This method initializes texto	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getTexto()
	{
		if (texto == null)
		{
			texto = new JTextArea();
			texto.setEditable(false);
			texto.setMargin(new Insets(5, 5, 5, 5));
			texto.setBackground(this.getBackground());
			texto.setFont(new Font("Verdana", Font.PLAIN, 14));
			partida.setTextoInfo(texto);
		}
		return texto;
	}

	/**
	 * This method initializes botonera	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getBotonera()
	{
		if (botonera == null)
		{
			botonera = new JPanel();
			botonera.setLayout(new FlowLayout());
			botonera.add(getBPausa(), null);
		}
		return botonera;
	}

	/**
	 * This method initializes datos	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getDatos()
	{
		if (datos == null)
		{
			Imagen imagen = new Imagen();
			
			datos = new JPanel();
			datos.setLayout(new BorderLayout());
			datos.setBorder(LineBorder.createGrayLineBorder());
			
			datos.add(imagen, BorderLayout.WEST);
			partida.setImagenInfo(imagen);
			
			datos.add(getTexto(), BorderLayout.CENTER);
		}
		return datos;
	}

	/**
	 * This method initializes bPausa	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private MiBoton getBPausa()
	{
		if (bPausa == null)
		{
			bPausa = new MiBoton("Pausa");
			partida.setPausa(bPausa);
		}
		return bPausa;
	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource().equals(getBPausa()))
		{
			if (partida.getFin())
			{
				String [] opciones = {"Menú principal", "Salir del programa", "Volver"};
				
				switch (JOptionPane.showOptionDialog(parent, "¿Desea abandonar el juego?",
						"Salir", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
						null, opciones, opciones[0]))
				{
					case 0:
						parent.loadRootPanel(new ModoInicio(parent));
						break;
					case 1:
						/*Cerrar todo*/
						System.exit(0);
						break;
				}
			}
			else
				parent.loadRootPanel(new ModoPausa(parent, juego));
		}
	}
}

class Chat extends JPanel
{
	private static final long serialVersionUID = 1449740754230340465L;
	Partida partida = null;
	private JTextArea chat = null;
	private JPanel send = null;
	private MiBoton sendButton = null;
	private JTextField message = null;
	
	public Chat(Partida p)
	{
		super();
		this.partida = p;
		initialize();
		getSendButton().addActionListener(p);
		getMessage().addKeyListener(p);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize()
	{
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(100,100));
		this.add(getChat(), BorderLayout.CENTER);
		this.add(getSend(), BorderLayout.SOUTH);
	}

	/**
	 * This method initializes chat	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getChat()
	{
		if (chat == null)
		{
			chat = new JTextArea(20, 30);
			chat.setAutoscrolls(true);
			chat.setEditable(false);
			chat.setFont(new Font("Verdana", Font.PLAIN, 12));
			chat.setBorder(LineBorder.createGrayLineBorder());
			partida.setChat(chat);
		}
		return chat;
	}

	/**
	 * This method initializes send	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getSend()
	{
		if (send == null)
		{
			send = new JPanel();
			send.setLayout(new BorderLayout());
			send.add(getMessage(), BorderLayout.CENTER);
			send.add(getSendButton(), BorderLayout.EAST);
		}
		return send;
	}

	/**
	 * This method initializes sendButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private MiBoton getSendButton()
	{
		if (sendButton == null)
		{
			sendButton = new MiBoton("Enviar");
		}
		return sendButton;
	}

	/**
	 * This method initializes message	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getMessage()
	{
		if (message == null)
		{
			message = new JTextField();
			message.setFont(new Font("Verdana", Font.PLAIN, 12));
			partida.setMensajeChat(message);
		}
		return message;
	}
}

public class ModoJuego extends JSplitPane
{
	private static final long serialVersionUID = -8925322799587248232L;
	private Partida partida = null;
	
	public ModoJuego(Principal parent, boolean red, Partida partida)
	{
		super();
		this.partida = partida;
		this.setOrientation(HORIZONTAL_SPLIT);
		this.setTopComponent(new Juego(parent, this, partida));
		
		if (red)
		{
			this.setBottomComponent(new Chat(partida));
		}
		else
		{
			this.setBottomComponent(null);
		}
		this.setDividerSize(0);
	}
	
	public boolean getFin()
	{
		return partida.getFin();
	}
	
	@Override
	public String toString()
	{
		return "Pantalla de juego";
	}
}
