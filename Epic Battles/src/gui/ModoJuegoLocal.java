package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import logica.Local;
import logica.Partida;

public class ModoJuegoLocal extends JPanel implements ActionListener
{
	private static final long serialVersionUID = -4236367814618101524L;
	private Principal parent = null;
	private Image image = null;
	private JPanel botonera = null;
	private MiBoton bEmpezar = null;
	private JPanel grid = null;
	private MiBoton bCargar = null;
	private MiBoton bVolver = null;
	private JPanel inf = null;
	private JPanel sup = null;
	
	public ModoJuegoLocal(Principal parent)
	{
		super();
		this.parent = parent;
		initialize();
		getBEmpezar().addActionListener(this);
		getBCargar().addActionListener(this);
		getBVolver().addActionListener(this);
		image = Fondo.cargar(Fondo.Pantalla.ModoJuegoLocal);
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
			botonera.setOpaque(false);
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
	private MiBoton getBEmpezar()
	{
		if (bEmpezar == null)
		{
			bEmpezar = new MiBoton("Empezar partida");
		}
		return bEmpezar;
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
			GridLayout gridLayout = new GridLayout(2, 1, 10, 10);
			grid = new JPanel();
			grid.setOpaque(false);
			grid.setLayout(gridLayout);
			grid.add(getBEmpezar(), null);
			grid.add(getBCargar(), null);
		}
		return grid;
	}
	
	/**
	 * This method initializes bCargar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private MiBoton getBCargar()
	{
		if (bCargar == null)
		{
			bCargar = new MiBoton("Cargar partida");
		}
		return bCargar;
	}

	/**
	 * This method initializes bVolver	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private MiBoton getBVolver()
	{
		if (bVolver == null)
		{
			bVolver = new MiBoton("Volver");
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
			inf.setOpaque(false);
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
			sup.setOpaque(false);
			sup.setLayout(new FlowLayout());
			sup.add(getGrid(), null);
		}
		return sup;
	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource().equals(getBEmpezar()))
		{
			try
			{
				parent.loadRootPanel(new ModoJuego(parent, false, new Partida(parent, new Local())));
			}
			catch (Exception e)
			{
				parent.setHelp(e.getMessage());
			}
		}
		else if (ae.getSource().equals(getBCargar()))
		{
			parent.loadRootPanel(new ModoCargar(parent, false));
		}
		else if (ae.getSource().equals(getBVolver()))
		{
			parent.loadRootPanel(new ModoInicio(parent));
		}
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if (image != null)
			g.drawImage(image, 0,0,this.getWidth(),this.getHeight(),this);
	}
}
