package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;

import basedatos.GestorBaseDatos;

public class ModoCargar extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 467462451714083887L;
	private Principal parent = null;
	private boolean red = false;
	private Image image = null;
	private JPanel lateral = null;
	private JPanel botonera = null;
	private JPanel grid = null;
	private JButton bBorrar = null;
	private JButton bCargar = null;
	private JButton bVolver = null;
	private JList partidas = null;

	public ModoCargar(Principal parent, boolean red)
	{
		super();
		this.parent = parent;
		this.red = red;
		initialize();
		getBCargar().addActionListener(this);
		getBBorrar().addActionListener(this);
		getBVolver().addActionListener(this);
		image = Fondo.cargar(Fondo.Pantalla.ModoCargar);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize()
	{
		this.setLayout(new BorderLayout());
		this.add(getLateral(), BorderLayout.EAST);
	}

	@Override
	public String toString()
	{
		return "Pantalla de carga";
	}

	/**
	 * This method initializes lateral	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getLateral()
	{
		if (lateral == null)
		{
			lateral = new JPanel();
			lateral.setOpaque(false);
			lateral.setLayout(new BorderLayout());
			lateral.add(getPartidas(), BorderLayout.CENTER);
			lateral.add(getBotonera(), BorderLayout.SOUTH);
		}
		return lateral;
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
			botonera.setOpaque(false);
			botonera.setLayout(new FlowLayout());
			botonera.add(getGrid(), null);
		}
		return botonera;
	}

	/**
	 * This method initializes grid	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getGrid()
	{
		if (grid == null)
		{
			grid = new JPanel();
			grid.setOpaque(false);
			grid.setLayout(new GridLayout(3, 0, 10, 10));
			grid.add(getBCargar(), null);
			grid.add(getBBorrar(), null);
			grid.add(getBVolver(), null);
		}
		return grid;
	}

	/**
	 * This method initializes bBorrar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBBorrar()
	{
		if (bBorrar == null)
		{
			bBorrar = new JButton();
			bBorrar.setText("Borrar partida");
		}
		return bBorrar;
	}

	/**
	 * This method initializes bCargar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBCargar()
	{
		if (bCargar == null)
		{
			bCargar = new JButton();
			bCargar.setText("Cargar partida");
		}
		return bCargar;
	}

	/**
	 * This method initializes bVolver	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBVolver()
	{
		if (bVolver == null)
		{
			bVolver = new JButton();
			bVolver.setText("Volver");
		}
		return bVolver;
	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{	
		if (ae.getSource().equals(getBCargar()))
		{
			/* Cargar partida */
		}
		else if (ae.getSource().equals(getBBorrar()))
		{
			getPartidas().remove(getPartidas().getSelectedIndex());
		}
		else if (ae.getSource().equals(getBVolver()))
		{
			if (red)
			{
				parent.loadRootPanel(new ModoJuegoRed(parent));
			}
			else
			{
				parent.loadRootPanel(new ModoJuegoLocal(parent));
			}
		}
	}

	/**
	 * This method initializes partidas	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getPartidas()
	{
		if (partidas == null)
		{
			String[] aPartidas = GestorBaseDatos.leerPartidas();
			
			DefaultListModel model = new DefaultListModel();
		    partidas = new JList(model);
			
			for(int i = 0; i < aPartidas.length; i++)
			{
				model.addElement(aPartidas[i]);
			}
		}
		return partidas;
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g); 
	    if (image != null)
	    g.drawImage(image, 0,0,this.getWidth(),this.getHeight(),this);
	}
}
