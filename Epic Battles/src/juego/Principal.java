package juego;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JSplitPane gamePanel = null;
	private JLabel titulo = null;
	
	public Principal() throws HeadlessException {
		// TODO Auto-generated constructor stub
		super();
		initialize();
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
		// TODO getGamePanel().setTopComponent(new Juego(this));if (arg0=="2-player") getGamePanel().setBottomComponent(new Chat(this));else{getGamePanel().setBottomComponent(new JPanel());}
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
	}
	
	

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(new Inicio(this), BorderLayout.CENTER);
			jContentPane.add(getTitulo(), BorderLayout.SOUTH);
		}
		return jContentPane;
	}

	/**
	 * This method initializes gamePanel	
	 * 	
	 * @return javax.swing.JSplitPane	
	 */
	private JSplitPane getGamePanel() {
		if (gamePanel == null) {
			gamePanel = new JSplitPane();
			gamePanel.setOrientation(JSplitPane.VERTICAL_SPLIT);
		}
		return gamePanel;
	}

	/**
	 * This method initializes titulo	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getTitulo() {
		if (titulo == null) {
			titulo = new JLabel();
			titulo.setBorder(new LineBorder(Color.gray, 1));
			titulo.setForeground(Color.gray);
			titulo.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 11));
			titulo.setText("barra de estado/ayuda");
			titulo.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return titulo;
	}

}
