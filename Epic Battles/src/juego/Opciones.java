package juego;

import java.awt.Container;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;

public class Opciones extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton jButton = null;

	public Opciones(Container parent)
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
			jButton.setText("Opciones");
		}
		return jButton;
	}

}
