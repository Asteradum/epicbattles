package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import logica.Partida;
import logica.Red;

public class ModoJuegoRed extends JPanel implements ActionListener
{
	private static final long serialVersionUID = -8513250153154802461L;
	private Principal parent = null;
	private Image image = null;
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
		image = Fondo.cargar(Fondo.Pantalla.ModoJuegoRed);
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
			new Partida(new Red());
			parent.loadRootPanel(new ModoJuego(parent, true, new Partida(new Red())));
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
			botonera.setOpaque(false);
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
			sup.setOpaque(false);
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
			grid.setOpaque(false);
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
			inf.setOpaque(false);
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
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if (image != null)
			g.drawImage(image, 0,0,this.getWidth(),this.getHeight(),this);
	}
}
