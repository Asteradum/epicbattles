package juego.gui;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Opciones extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6136942568439299757L;
	private Container parent = null;
	private JButton jButton = null;
	private JButton jButton1 = null;
	private JPanel botonera = null;
	public Opciones(Container parent)
	{
		// TODO Auto-generated constructor stub
		super();
		this.parent = parent;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setLayout(new FlowLayout());
		this.add(getBotonera(), null);
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("Opciones");
		}
		return jButton;
	}
	
	public String toString()
	{
		return "Pantalla de opciones";
	}

	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("Opciones");
		}
		return jButton1;
	}

	/**
	 * This method initializes botonera	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getBotonera() {
		if (botonera == null) {
			botonera = new JPanel();
			botonera.setLayout(new BoxLayout(getBotonera(), BoxLayout.Y_AXIS));
			botonera.add(getJButton(), null);
			botonera.add(getJButton1(), null);
		}
		return botonera;
	}

}
