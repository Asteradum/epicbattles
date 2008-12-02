/**
 * 
 */
package juego;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;

/**
 * @author Alberto
 *
 */
public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;

	/**
	 * @throws HeadlessException
	 */
	public Principal() throws HeadlessException {
		// TODO Auto-generated constructor stub
		super();
		initialize();
	}

	/**
	 * @param gc
	 */
	public Principal(GraphicsConfiguration gc) {
		super(gc);
		// TODO Auto-generated constructor stub
		initialize();
	}

	/**
	 * @param title
	 * @throws HeadlessException
	 */
	public Principal(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
		initialize();
	}

	/**
	 * @param title
	 * @param gc
	 */
	public Principal(String title, GraphicsConfiguration gc) {
		super(title, gc);
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
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
		}
		return jContentPane;
	}

}
