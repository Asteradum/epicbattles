package epicbattles.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import juego.gui.Inicio;
import juego.gui.Juego;
import juego.gui.Opciones;
import juego.gui.Pausa;

import red.Chat;

public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3466096037393013321L;
	private JPanel jContentPane = null;
	private JSplitPane gamePanel = null;
	private JLabel help = null;
	private Inicio inicio = null;
	private Juego juego = null;
	private Opciones opciones = null;
	private Pausa pausa = null;
	private boolean b2player = false;
	
	public Principal() throws HeadlessException {
		// TODO Auto-generated constructor stub
		super();
		initialize();
		//this.pack();
	}

	public Principal(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
		initialize();
	}

	public Principal(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
		initialize();
	}

	public Principal(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setContentPane(getJContentPane());
		this.setTitle("Epic Battles v0.1");
		getHelp().setText(inicio.toString());
	}	

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane()
	{
		if (jContentPane == null)
		{
			jContentPane = new JPanel();
			inicio = new Inicio(this);
			jContentPane.setLayout(new BorderLayout());
			//jContentPane.add(inicio, BorderLayout.CENTER);
			jContentPane.add(getGamePanel("player-vs-ia"), BorderLayout.CENTER);
			//jContentPane.add(getGamePanel("2-player"), BorderLayout.CENTER);
			//jContentPane.add(new Imagen(), BorderLayout.CENTER);
			jContentPane.add(getHelp(), BorderLayout.SOUTH);
		}
		return jContentPane;
	}
	
	private JSplitPane getGamePanel()
	{	
		if (gamePanel == null) return getGamePanel("player-vs-ia");
		else return gamePanel;
	}

	/**
	 * This method initializes gamePanel	
	 * 	
	 * @return javax.swing.JSplitPane	
	 */
	private JSplitPane getGamePanel(String mode)
	{
		if (gamePanel == null)
		{			
			gamePanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
			gamePanel.setTopComponent(new Juego(this));		
			if (mode == "2-player")
			{				
				b2player = true;				
				gamePanel.setBottomComponent(new Chat(this));
				gamePanel.setDividerSize(5);
				gamePanel.setOneTouchExpandable(true);
			}
			else if (mode == "player-vs-ia")
			{	
				gamePanel.setDividerSize(0);		
			}				
		}
		return gamePanel;
	}

	/**
	 * This method initializes help	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getHelp() {
		if (help == null) {
			help = new JLabel();
			help.setBorder(new LineBorder(Color.gray, 1));
			help.setForeground(Color.gray);
			help.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 11));
			help.setText("");
			help.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return help;
	}

}
