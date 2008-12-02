/**
 * 
 */
package juego;

import java.awt.GridBagLayout;
import java.awt.LayoutManager;

import javax.swing.JPanel;

/**
 * @author Alberto y Alvaro
 *
 */
public class Juego extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public Juego() {
		// TODO Auto-generated constructor stub
		super();
		initialize();
	}

	/**
	 * @param layout
	 */
	public Juego(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
		initialize();
	}

	/**
	 * @param isDoubleBuffered
	 */
	public Juego(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
		initialize();
	}

	/**
	 * @param layout
	 * @param isDoubleBuffered
	 */
	public Juego(LayoutManager layout, boolean isDoubleBuffered) {
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
