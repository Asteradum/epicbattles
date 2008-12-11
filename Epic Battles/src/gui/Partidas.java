package gui;

import java.awt.GridBagLayout;
import java.awt.LayoutManager;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class Partidas extends JPanel {

	private static final long serialVersionUID = 1L;
	private JComponent parent = null;

	public Partidas() {
		// TODO Auto-generated constructor stub
		super();
		initialize();
	}

	public Partidas(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
		initialize();
	}

	public Partidas(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
		initialize();
	}

	public Partidas(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setLayout(new GridBagLayout());
	}

}
