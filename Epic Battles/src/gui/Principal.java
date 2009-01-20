package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Principal extends JFrame
{
	private static final long serialVersionUID = -3006194651918420869L;
	private JLabel help = null;
	private JPanel jContentPane = null;
	private JComponent panel = null;
	
	public Principal()
	{
		super();
		
		final Principal frame = this;
		
		initialize();
		this.setResizable(false);
		this.setSize(730, 585);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		this.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent we)
			{
				if (panel instanceof ModoJuego)
				{
					String [] opciones = {"Guardar y salir", "Salir sin guardar", "Cancelar"};
					
					switch (JOptionPane.showOptionDialog(frame, "¿Desea abandonar el juego?",
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
					if (JOptionPane.showConfirmDialog(frame, "¿Desea abandonar el juego?", "Salir", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
					{
						System.exit(0);
					}
				}
		    }
		});
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
			help = new JLabel("");
			help.setBorder(new LineBorder(Color.gray, 1));
			help.setForeground(Color.gray);
			help.setFont(new Font("Verdana", Font.PLAIN, 12));
			help.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return help;
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
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize()
	{
		this.setContentPane(getJContentPane());
		this.setTitle("Epic Battles v0.1");
		this.loadRootPanel(new ModoInicio(this));
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
		this.repaint();
	}
	
	public void setHelp(String text)
	{
		getHelp().setText(text);
	}
}
