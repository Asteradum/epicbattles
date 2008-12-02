package juego;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public final class framer extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel imagen = null;
	private JPanel content = null;
	public framer() throws HeadlessException {
		// TODO Auto-generated constructor stub
		super();
		initialize();
	}

	public framer(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
		initialize();
	}

	public framer(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
		initialize();
	}

	public framer(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(401, 286);
		this.setContentPane(getContent());
		this.setTitle("Epic Battles");
	}

	/**
	 * This method initializes imagen	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getImagen() {
		if (imagen == null) {
			imagen = new JPanel();
			imagen.setLayout(new FlowLayout());
			imagen.setBackground(Color.darkGray);
		}
		return imagen;
	}

	/**
	 * This method initializes content	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getContent() {
		if (content == null) {
			content = new JPanel();
			content.setLayout(new FlowLayout());
			content.add(getImagen(), null);
		}
		return content;
	}

}  //  @jve:decl-index=0:visual-constraint="199,21"
