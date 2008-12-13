package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ModoOpciones extends JPanel implements ActionListener
{
	private static final long serialVersionUID = -6136942568439299757L;
	private Principal parent = null;
	private JPanel botonera = null;
	private JButton bAceptar = null;
	private JButton bCancelar = null;
	private JPanel izq = null;
	private JPanel der = null;
	public ModoOpciones(Principal parent)
	{
		super();
		this.parent = parent;
		initialize();
		getBAceptar().addActionListener(this);
		getBCancelar().addActionListener(this);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize()
	{
		this.setLayout(new BorderLayout());
		this.add(getBotonera(), BorderLayout.SOUTH);
	}

	@Override
	public String toString()
	{
		return "Pantalla de opciones";
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
			botonera.add(getIzq(), BorderLayout.WEST);
			botonera.add(getDer(), BorderLayout.EAST);
		}
		return botonera;
	}

	/**
	 * This method initializes bAceptar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBAceptar()
	{
		if (bAceptar == null)
		{
			bAceptar = new JButton();
			bAceptar.setText("Aceptar");
		}
		return bAceptar;
	}

	/**
	 * This method initializes bCancelar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBCancelar()
	{
		if (bCancelar == null)
		{
			bCancelar = new JButton();
			bCancelar.setText("Cancelar");
		}
		return bCancelar;
	}

	/**
	 * This method initializes izq	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getIzq()
	{
		if (izq == null)
		{
			izq = new JPanel();
			izq.setLayout(new FlowLayout());
			izq.add(getBAceptar(), null);
		}
		return izq;
	}

	/**
	 * This method initializes der	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getDer()
	{
		if (der == null)
		{
			der = new JPanel();
			der.setLayout(new FlowLayout());
			der.add(getBCancelar(), null);
		}
		return der;
	}
}
