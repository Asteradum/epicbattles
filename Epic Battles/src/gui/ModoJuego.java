package gui;

import graficos.Escenario;
import graficos.Imagen;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import logica.Partida;
import logica.Tablero;

class Juego extends JPanel
{
	private static final long serialVersionUID = 7325275995219105393L;
	private Principal parent = null;
	private ModoJuego juego = null;
	private Partida partida = null;
	private Image image = null;
	
	public Juego(Principal parent, ModoJuego mj, Partida partida)
	{
		super();
		this.parent = parent;
		this.juego = mj;
		this.partida = partida;
		initialize();
		//partida.setEscenario(escenario);
		//this.setBorder(LineBorder.createGrayLineBorder());
		try
	    {
	      image = javax.imageio.ImageIO.read(new File("imagenes/BanzaiBot-icon.gif"));
	    }
	    catch (Exception e) { /*handled in paintComponent()*/ }
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
		this.add(new Info(parent, juego), BorderLayout.SOUTH);
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
	    if (image != null)
	    	g.drawImage(image, 0,0,this.getWidth(),this.getHeight(),this);
	}
}

class Info extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 5535961802115944302L;
	private Principal parent = null;
	private ModoJuego juego = null;
	private JTextArea texto = null;
	private JPanel botonera = null;
	private JPanel datos = null;
	private JButton bPausa = null;

	public Info(Principal parent, ModoJuego mj)
	{
		super();
		this.parent = parent;
		this.juego = mj;
		initialize();
		//this.setBorder(LineBorder.createGrayLineBorder());
		getBPausa().addActionListener(this);
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
	
	public void setText(String text)
	{
		texto.setText(text);
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
			texto.setBackground(this.getBackground());
			//texto.setBorder(LineBorder.createGrayLineBorder());
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
			//botonera.setBorder(LineBorder.createGrayLineBorder());
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
			datos = new JPanel();
			datos.setLayout(new BorderLayout());
			datos.setBorder(LineBorder.createGrayLineBorder());
			datos.add(new Imagen(), BorderLayout.WEST);
			datos.add(getTexto(), BorderLayout.CENTER);
		}
		return datos;
	}

	/**
	 * This method initializes bPausa	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBPausa()
	{
		if (bPausa == null)
		{
			bPausa = new JButton();
			bPausa.setText("Pausa");
		}
		return bPausa;
	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource().equals(getBPausa()))
		{
			parent.loadRootPanel(new ModoPausa(parent, juego));
		}
	}
}

class Chat extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1449740754230340465L;
	private JTextArea chat = null;
	private JPanel send = null;
	private JButton sendButton = null;
	private JTextField message = null;	
	
	public Chat()
	{
		super();
		initialize();
		//this.setBorder(LineBorder.createGrayLineBorder());
		getSendButton().addActionListener(this);
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
			chat.setBorder(LineBorder.createGrayLineBorder());
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
	private JButton getSendButton()
	{
		if (sendButton == null)
		{
			sendButton = new JButton();
			sendButton.setText("Enviar");
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
		}
		return message;
	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource().equals(getSendButton()))
		{
			getChat().append(getMessage().getText() + "\n");
			getMessage().setText("");
		}
	}
}

public class ModoJuego extends JSplitPane
{
	private static final long serialVersionUID = -8925322799587248232L;
	
	public ModoJuego(Principal parent, boolean red, Partida partida)
	{
		super();
		this.setOrientation(HORIZONTAL_SPLIT);
		this.setTopComponent(new Juego(parent, this, partida));
		
		if (red)
		{
			this.setBottomComponent(new Chat());
		}
		else
		{
			this.setBottomComponent(null);	
		}
		this.setDividerSize(0);
	}
	
	@Override
	public String toString()
	{
		return "Pantalla de juego";
	}
}
