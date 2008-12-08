/**
 * 
 */
package juego;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @author Alberto y Alvaro
 *
 */
public class Pausa extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6099231033223006821L;
	private Container parent = null;
	private JButton jButton = null;

	/**
	 * 
	 */
	public Pausa(Container parent)
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
		this.setLayout(new BorderLayout());
		this.add(getJButton(), BorderLayout.CENTER);
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("Pausa");
		}
		return jButton;
	}
	
	public String toString()
	{
		return "Pantalla de pausa";
	}

}
