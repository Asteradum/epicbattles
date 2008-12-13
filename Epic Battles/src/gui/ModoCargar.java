package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;

public class ModoCargar extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 467462451714083887L;
	private Principal parent = null;
	private boolean red = false;
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
			partidas = new JList();
		}
		return partidas;
	}
}
