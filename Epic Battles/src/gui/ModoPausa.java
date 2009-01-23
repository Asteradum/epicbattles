package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import logica.GameSave;
import basedatos.GestorBaseDatos;

public class ModoPausa extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 6099231033223006821L;
	private Principal parent = null;
	private ModoJuego juego = null;
	private Image image = null;
	private MiBoton bMenu = null;
	private JPanel botonera = null;
	private MiBoton bContinuar = null;
	private JPanel jPanel = null;
	private MiBoton bSalir = null;
	private MiBoton bGuardar = null;

	public ModoPausa(Principal parent, ModoJuego mj)
	{
		super();
		this.parent = parent;
		this.juego = mj;
		initialize();
		getBContinuar().addActionListener(this);
		getBGuardar().addActionListener(this);
		getBMenu().addActionListener(this);
		getBSalir().addActionListener(this);
		image = Fondo.cargar(Fondo.ModoPausa);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize()
	{
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets = new Insets(5, 0, 0, 0);
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridx = 0;
		this.setPreferredSize(new Dimension(200, 300));
		this.setLayout(new GridBagLayout());
		this.add(getJPanel(), gridBagConstraints);
	}

	/**
	 * This method initializes bMenu
	 * 	
	 * @return javax.swing.JButton
	 */
	private MiBoton getBMenu()
	{
		if (bMenu == null)
		{
			bMenu = new MiBoton("Menu principal");
		}
		return bMenu;
	}
	
	@Override
	public String toString()
	{
		return "Pantalla de pausa";
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
			GridLayout gridLayout = new GridLayout(4, 1, 10, 10);
			botonera = new JPanel();
			botonera.setLayout(gridLayout);
			botonera.setOpaque(false);
			botonera.add(getBContinuar(), null);
			botonera.add(getBGuardar(), null);
			botonera.add(getBMenu(), null);
			botonera.add(getBSalir(), null);
		}
		return botonera;
	}

	/**
	 * This method initializes bContinuar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private MiBoton getBContinuar()
	{
		if (bContinuar == null)
		{
			bContinuar = new MiBoton("Continuar");
		}
		return bContinuar;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel()
	{
		if (jPanel == null)
		{
			jPanel = new JPanel();
			jPanel.setOpaque(false);
			jPanel.setLayout(new FlowLayout());
			jPanel.add(getBotonera(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes bSalir	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private MiBoton getBSalir()
	{
		if (bSalir == null)
		{
			bSalir = new MiBoton("Salir");
		}
		return bSalir;
	}

	/**
	 * This method initializes bGuardar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private MiBoton getBGuardar()
	{
		if (bGuardar == null)
		{
			bGuardar = new MiBoton("Guardar partida");
		}
		return bGuardar;
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if (image != null)
			g.drawImage(image, 0,0,this.getWidth(),this.getHeight(),this);
	}
	
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource().equals(getBContinuar()))
		{
			parent.loadRootPanel(juego);
		}
		else if (ae.getSource().equals(getBGuardar()))
		{
			try
			{
				GestorBaseDatos.guardarPartida
				(
					GameSave.ahora(), GameSave.jug1, GameSave.jug2,
					GameSave.crearMemo(), GameSave.ip
				);
			}
			catch (SQLException sqle)
			{
				parent.setHelp(sqle.getMessage());
			}
			
			parent.loadRootPanel(juego);
			parent.setHelp("Partida guardada");
		}
		else if (ae.getSource().equals(getBMenu()))
		{
			String [] opciones = {"Guardar y salir", "Salir sin guardar", "Cancelar"};
			switch (JOptionPane.showOptionDialog(this, "¿Desea abandonar el juego?",
					"Salir", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
					null, opciones, opciones[0]))
			{
				case 0:
					/*Guardar todo*/
					try
					{
						GestorBaseDatos.guardarPartida
						(
							GameSave.ahora(), GameSave.jug1, GameSave.jug2,
							GameSave.crearMemo(), GameSave.ip
						);
					}
					catch (SQLException sqle)
					{
						parent.setHelp(sqle.getMessage());
					}
					
				case 1:
					/*Cerrar todo*/
					parent.loadRootPanel(new ModoInicio(parent));
					break;
			}
		}
		else if (ae.getSource().equals(getBSalir()))
		{
			String [] opciones = {"Guardar y salir", "Salir sin guardar", "Cancelar"};
			switch (JOptionPane.showOptionDialog(this, "¿Desea abandonar el juego?",
					"Salir", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
					null, opciones, opciones[0]))
			{
				case 0:
					/*Guardar todo*/
					try
					{
						GestorBaseDatos.guardarPartida
						(
							GameSave.ahora(), GameSave.jug1, GameSave.jug2,
							GameSave.crearMemo(), GameSave.ip
						);
					}
					catch (SQLException sqle)
					{
						parent.setHelp(sqle.getMessage());
					}
				case 1:
					/*Cerrar todo*/
					System.exit(0);
					break;
			}
		}
	}
}
