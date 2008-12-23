package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Principal extends JFrame implements WindowListener
{
	private static final long serialVersionUID = -3006194651918420869L;
	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	private JPanel jContentPane = null;
	private JComponent panel = null;
	private JLabel help = null;
	
	public Principal() throws HeadlessException
	{
		super();
		initialize();
		this.setResizable(false);
		//this.setSize((int)(dim.getWidth()/1.75), (int)(dim.getHeight()/1.75));
		this.setSize(730, 585);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(this);
	}	

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize()
	{
		this.setContentPane(getJContentPane());
		this.setTitle("Epic Battles v0.1");
		this.loadRootPanel(new ModoInicio(this));
		//this.loadRootPanel(new ModoJuego(this, true));
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
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getHelp(), BorderLayout.SOUTH);
		}
		return jContentPane;
	}	

	/**
	 * This method initializes help	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getHelp()
	{
		if (help == null)
		{
			help = new JLabel();
			help.setBorder(new LineBorder(Color.gray, 1));
			help.setForeground(Color.gray);
			help.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 11));
			help.setText("");
			help.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return help;
	}
	
	public void setHelp(String text)
	{
		getHelp().setText(text);
	}
	
	public void loadRootPanel(JComponent jp)
	{
		if (panel != null)
		{
			jContentPane.remove(panel);
		}
		panel = jp;
		setHelp(jp.toString());
		jContentPane.add(panel, BorderLayout.CENTER);
		//this.pack();
		this.repaint();
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent we)
	{
		if (panel.getClass().equals(ModoJuego.class))
		{
			String [] opciones = {"Guardar y salir", "Salir sin guardar", "Cancelar"};
			switch (JOptionPane.showOptionDialog(this, "¿Desea abandonar el juego?",
					"Salir", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
					null, opciones, opciones[0]))								
			{
				case 0:
					/*Guardar todo*/
				case 1:
					/*Cerrar todo*/
					System.exit(0);
					break;				
			}
		}
		else
		{
			if (JOptionPane.showConfirmDialog(this, "¿Desea abandonar el juego?", "Salir", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			{
				System.exit(0);
			}
		}
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
