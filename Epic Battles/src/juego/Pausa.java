/**
 * 
 */
package juego;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;

/**
 * @author Alberto y Alvaro
 *
 */
public class Pausa extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton jButton = null;

	/**
	 * 
	 */
	public Pausa(JComponent parent)
	{
		// TODO Auto-generated constructor stub
		super();
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

}
