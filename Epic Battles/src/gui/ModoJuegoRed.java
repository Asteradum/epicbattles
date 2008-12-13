package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ModoJuegoRed extends JPanel implements ActionListener
{
	private static final long serialVersionUID = -8513250153154802461L;
	private Principal parent = null;
	private JPanel botonera = null;
	private JPanel sup = null;
	private JPanel grid = null;
	private JButton bEmpezar = null;
	private JButton bCargar = null;
	private JPanel inf = null;
	private JButton bVolver = null;

	public ModoJuegoRed(Principal parent)
	{
		super();
		this.parent = parent;
		initialize();
		getBEmpezar().addActionListener(this);
		getBCargar().addActionListener(this);
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
		this.add(getBotonera(), BorderLayout.EAST);
	}

	@Override
	public String toString()
	{
		return "Configurar juego en red";
	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource().equals(getBEmpezar()))
		{
			parent.loadRootPanel(new ModoJuego(parent, true));
		}
		else if (ae.getSource().equals(getBCargar()))
		{
			parent.loadRootPanel(new ModoCargar(parent, true));
		}
		else if (ae.getSource().equals(getBVolver()))
		{
			parent.loadRootPanel(new ModoInicio(parent));
		}
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
			botonera.setLayout(new BorderLayout());
			botonera.add(getSup(), java.awt.BorderLayout.CENTER);
			botonera.add(getInf(), java.awt.BorderLayout.SOUTH);
		}
		return botonera;
	}

	/**
	 * This method initializes sup	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getSup()
	{
		if (sup == null)
		{
			sup = new JPanel();
			sup.setLayout(new FlowLayout());
			sup.add(getGrid(), null);
		}
		return sup;
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
			grid.setLayout(new GridLayout(2, 1, 10, 10));
			grid.add(getBEmpezar(), null);
			grid.add(getBCargar(), null);
		}
		return grid;
	}

	/**
	 * This method initializes bEmpezar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBEmpezar()
	{
		if (bEmpezar == null)
		{
			bEmpezar = new JButton();
			bEmpezar.setText("Empezar partida");
		}
		return bEmpezar;
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
	 * This method initializes inf	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getInf()
	{
		if (inf == null) 
		{
			inf = new JPanel();
			inf.setLayout(new FlowLayout());
			inf.add(getBVolver(), null);
		}
		return inf;
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
}
