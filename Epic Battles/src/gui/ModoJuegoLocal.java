package gui;

import java.awt.GridBagLayout;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class ModoJuegoLocal extends JPanel {

	private static final long serialVersionUID = 1L;

	public ModoJuegoLocal() {
		// TODO Auto-generated constructor stub
		super();
		initialize();
	}

	public ModoJuegoLocal(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
		initialize();
	}

	public ModoJuegoLocal(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
		initialize();
	}

	public ModoJuegoLocal(LayoutManager layout, boolean isDoubleBuffered) {
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
