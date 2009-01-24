package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class ModoOpciones extends JPanel implements ActionListener
{
	private static final long serialVersionUID = -6136942568439299757L;
	private MiBoton bAceptar = null;
	private MiBoton bCancelar = null;
	private JPanel botonera = null;
	private JPanel der = null;
	private Image image = null;
	private JPanel izq = null;
	private JPanel panelOpc = null;
	private Principal parent = null;
	
	public ModoOpciones(Principal parent)
	{
		super();
		this.parent = parent;
		initialize();
		getBAceptar().addActionListener(this);
		getBCancelar().addActionListener(this);
		image = Fondo.cargar(Fondo.ModoOpciones);
	}
	
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource().equals(getBAceptar()))
		{
			/* Guardar opciones */
		}
		parent.loadRootPanel(new ModoInicio(parent));
	}

	private MiBoton getBAceptar()
	{
		if (bAceptar == null)
		{
			bAceptar = new MiBoton("Aceptar");
		}
		return bAceptar;
	}

	private MiBoton getBCancelar()
	{
		if (bCancelar == null)
		{
			bCancelar = new MiBoton("Cancelar");
		}
		return bCancelar;
	}
	
	private JPanel getBotonera()
	{
		if (botonera == null)
		{
			botonera = new JPanel();
			botonera.setOpaque(false);
			botonera.setLayout(new BorderLayout());
			botonera.add(getIzq(), BorderLayout.WEST);
			botonera.add(getDer(), BorderLayout.EAST);
		}
		return botonera;
	}
	
	private JPanel getDer()
	{
		if (der == null)
		{
			der = new JPanel();
			der.setOpaque(false);
			der.setLayout(new FlowLayout());
			der.add(getBCancelar(), null);
		}
		return der;
	}
	
	private JPanel getIzq()
	{
		if (izq == null)
		{
			izq = new JPanel();
			izq.setOpaque(false);
			izq.setLayout(new FlowLayout());
			izq.add(getBAceptar(), null);
		}
		return izq;
	}
	
	private JPanel getPanelOpc()
	{
		if (panelOpc == null)
		{
			panelOpc = new JPanel();
			panelOpc.setOpaque(false);
			panelOpc.setLayout(new FlowLayout());
		}
		return panelOpc;
	}
	
	private void initialize()
	{
		this.setLayout(new BorderLayout());
		this.add(getPanelOpc(), BorderLayout.CENTER);
		this.add(getBotonera(), BorderLayout.SOUTH);
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
		return "Pantalla de opciones";
	}
}
