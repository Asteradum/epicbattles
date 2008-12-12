package gui;

import graficos.Imagen;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Info extends JPanel
{
	private static final long serialVersionUID = 5535961802115944302L;
	private Principal parent = null;
	private JPanel datos = null;
	private JTextArea texto = null;

	public Info(Principal parent)
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
		this.add(new Imagen(parent), BorderLayout.WEST);
		this.add(getDatos(), BorderLayout.CENTER);
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
			datos.setLayout(new BoxLayout(getDatos(), BoxLayout.Y_AXIS));
			datos.add(getTexto(), null);
		}
		return datos;
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
}
