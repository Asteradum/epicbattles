package gui;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ModoInicio extends JPanel implements ActionListener
{
	private static final long serialVersionUID = -7943530594020763917L;
	private MiBoton bLocal = null;
	private MiBoton bOpciones = null;
	private JPanel botonera = null;
	private MiBoton bRed = null;	
	private MiBoton bSalir = null;
	private Image image = null;
	private JPanel jPanel = null;
	private Principal parent = null;	
	
	public ModoInicio(Principal parent)
	{
		super();
		this.parent = parent;
		initialize();
		getBLocal().addActionListener(this);
		getBRed().addActionListener(this);
		getBOpciones().addActionListener(this);
		getBSalir().addActionListener(this);
		image = Fondo.cargar(Fondo.Pantalla.ModoInicio);
	}
	
	@Override
	public void actionPerformed(ActionEvent ae)
	{	
		if (ae.getSource().equals(getBLocal()))
		{
			parent.loadRootPanel(new ModoJuegoLocal(parent));
		}
		else if (ae.getSource().equals(getBRed()))
		{
			parent.loadRootPanel(new ModoJuegoRed(parent));
		}
		else if (ae.getSource().equals(getBOpciones()))
		{
			parent.loadRootPanel(new ModoOpciones(parent));
		}
		else if (ae.getSource().equals(getBSalir()))
		{
			if (JOptionPane.showConfirmDialog(this, "¿Desea abandonar el juego?", "Salir", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			{
				System.exit(0);
			}
		}
	}
	
	private MiBoton getBLocal()
	{
		if (bLocal == null)
		{
			bLocal = new MiBoton("Juego local");
		}
		return bLocal;
	}
	
	private MiBoton getBOpciones()
	{
		if (bOpciones == null)
		{
			bOpciones = new MiBoton("Opciones");
		}
		return bOpciones;
	}
	
	private JPanel getBotonera() 
	{
		if (botonera == null)
		{
			GridLayout gridLayout = new GridLayout(2, 2, 10, 10);
			botonera = new JPanel();
			botonera.setOpaque(false);
			botonera.setLayout(gridLayout);
			botonera.add(getBLocal(), 0);
			botonera.add(getBRed(), 1);
			botonera.add(getBOpciones(), 2);
			botonera.add(getBSalir(), 3);
		}
		return botonera;
	}
	
	private MiBoton getBRed()
	{
		if (bRed == null) 
		{
			bRed = new MiBoton("Juego en red");
		}
		return bRed;
	}
	
	private MiBoton getBSalir()
	{
		if (bSalir == null)
		{
			bSalir = new MiBoton("Salir");
		}
		return bSalir;
	}

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
	
	private void initialize()
	{
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets = new Insets(5, 0, 0, 0);
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridx = 0;
		this.setLayout(new GridBagLayout());
		this.add(getJPanel(), gridBagConstraints);
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
		return "Pantalla de inicio";
	}
}
