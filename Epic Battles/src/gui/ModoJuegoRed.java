package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import red.GestorSockets;

import logica.Partida;
import logica.Red;

public class ModoJuegoRed extends JPanel implements ActionListener
{
	private static final long serialVersionUID = -8513250153154802461L;
	private MiBoton bCargar = null;
	private MiBoton bEmpezar = null;
	private JPanel botonera = null;
	private MiBoton bVolver = null;
	private GestorSockets gestor = null;
	private JPanel grid = null;
	private Image image = null;
	private JPanel inf = null;
	private Principal parent = null;
	private JPanel sup = null;

	public ModoJuegoRed(Principal parent)
	{
		super();
		this.parent = parent;
		initialize();
		this.gestor = new GestorSockets();
		getBEmpezar().addActionListener(this);
		getBCargar().addActionListener(this);
		getBVolver().addActionListener(this);
		image = Fondo.cargar(Fondo.ModoJuegoRed);
	}
	
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource().equals(getBEmpezar()))
		{
			try
			{
				parent.loadRootPanel(new ModoJuego(parent, true, new Partida(parent, new Red(gestor))));
			}
			catch (Exception e)
			{
				parent.setHelp(e.getMessage());
			}
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

	private MiBoton getBCargar()
	{
		if (bCargar == null)
		{
			bCargar = new MiBoton("Cargar partida");
		}
		return bCargar;
	}

	private MiBoton getBEmpezar()
	{
		if (bEmpezar == null)
		{
			bEmpezar = new MiBoton("Empezar partida");
		}
		return bEmpezar;
	}
	
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
	
	private MiBoton getBVolver()
	{
		if (bVolver == null)
		{
			bVolver = new MiBoton("Volver");
		}
		return bVolver;
	}
	
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
	
	private void initialize()
	{
		this.setLayout(new BorderLayout());
		this.add(getBotonera(), BorderLayout.EAST);
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if (image != null)
			g.drawImage(image, 0,0,this.getWidth(),this.getHeight(),this);
	}
	
	@Override
	public String toString()
	{
		return "Configurar juego en red";
	}
}
