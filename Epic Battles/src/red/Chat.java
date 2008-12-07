package red;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Chat extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton jButton = null;

	public Chat(Container parent) {
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
			jButton.setText("Chat");
		}
		return jButton;
	}

}
