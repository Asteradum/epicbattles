package gui;

import graficos.Imagen;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Info extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 5535961802115944302L;
	private Principal parent = null;
	private ModoJuego juego = null;
	private JTextArea texto = null;
	private JPanel botonera = null;
	private JPanel datos = null;
	private JButton bPausa = null;

	public Info(Principal parent, ModoJuego mj)
	{
		super();
		this.parent = parent;
		this.juego = mj;
		initialize();
		getBPausa().addActionListener(this);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize()
	{
		this.setLayout(new BorderLayout());
		this.add(getDatos(), BorderLayout.CENTER);
		this.add(getBotonera(), BorderLayout.SOUTH);
	}
	
	public void setText(String text)
	{
		texto.setText(text);
	}

	/**
	 * This method initializes texto	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getTexto()
	{
		if (texto == null)
		{
			texto = new JTextArea();
		}
		return texto;
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
			botonera.add(getBPausa(), null);
		}
		return botonera;
	}

	/**
	 * This method initializes datos	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getDatos()
	{
		if (datos == null)
		{
			datos = new JPanel();
			datos.setLayout(new BorderLayout());
			datos.add(new Imagen(parent), BorderLayout.WEST);
			datos.add(getTexto(), BorderLayout.CENTER);
		}
		return datos;
	}

	/**
	 * This method initializes bPausa	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBPausa()
	{
		if (bPausa == null)
		{
			bPausa = new JButton();
			bPausa.setText("Pausa");
		}
		return bPausa;
	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource().equals(getBPausa()))
		{
			parent.loadRootPanel(new ModoPausa(parent, juego));
		}
	}
}
