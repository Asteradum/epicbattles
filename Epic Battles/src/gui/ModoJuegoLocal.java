package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ModoJuegoLocal extends JPanel
{
	private static final long serialVersionUID = -4236367814618101524L;
	private Principal parent = null;
	private JPanel botonera = null;
	private JButton bEmpezar = null;
	private JPanel jPanel = null;
	private JButton bCargar = null;
	private JButton bVolver = null;
	private JPanel inf = null;
	private JPanel sup = null;
	
	public ModoJuegoLocal(Principal parent)
	{
		super();
		this.parent = parent;
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
		this.add(getBotonera(), BorderLayout.EAST);
	}

	@Override
	public String toString()
	{
		return "Configurar juego local";
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
			botonera.add(getSup(), BorderLayout.CENTER);
			botonera.add(getInf(), java.awt.BorderLayout.SOUTH);
		}
		return botonera;
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
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel()
	{
		if (jPanel == null)
		{
			GridLayout gridLayout = new GridLayout(2, 1, 10, 10);
			jPanel = new JPanel();
			jPanel.setLayout(gridLayout);
			jPanel.add(getBEmpezar(), null);
			jPanel.add(getBCargar(), null);
		}
		return jPanel;
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
			sup.add(getJPanel(), null);
		}
		return sup;
	}
}
