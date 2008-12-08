/**
 * 
 */
package juego;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

/**
 * @author Alberto y Alvaro
 *
 */
public class Juego extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7325275995219105393L;
	private Container parent = null;
	private JPanel info = null;
	private Canvas scen = null;
	private JPanel image = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JPanel texto = null;
	private JLabel jLabel11 = null;
	private JLabel jLabel12 = null;
	private JLabel jLabel13 = null;
	
	/**
	 * 
	 */
	public Juego(Container parent)
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
		this.add(getInfo(), BorderLayout.EAST);
		this.add(getScen(), BorderLayout.CENTER);
	}

	public String toString()
	{
		return "Pantalla de juego";
	}

	/**
	 * This method initializes info	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getInfo()
	{
		if (info == null)
		{
			info = new JPanel();
			info.setLayout(new BorderLayout());
			info.add(getImage(), BorderLayout.NORTH);
			info.add(getTexto(), BorderLayout.CENTER);
		}
		return info;
	}
	
	private Canvas getScen()
	{
		if (scen == null)
		{
			scen = new Canvas();
		}
		return scen;
	}

	/**
	 * This method initializes image	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getImage() {
		if (image == null) {
			image = new JPanel();
			image.setLayout(new FlowLayout());
			image.add(getJLabel(), null);
		}
		return image;
	}

	/**
	 * This method initializes jLabel	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getJLabel() {
		if (jLabel == null) {
			jLabel = new JLabel();
			jLabel.setText("Soy una imagen");
		}
		return jLabel;
	}

	/**
	 * This method initializes jLabel1	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Soy información");
		}
		return jLabel1;
	}

	/**
	 * This method initializes texto	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getTexto() {
		if (texto == null) {
			texto = new JPanel();
			texto.setLayout(new FlowLayout());
			texto.add(getJLabel1(), null);
		}
		return texto;
	}

	/**
	 * This method initializes jLabel11	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getJLabel11() {
		if (jLabel11 == null) {
			jLabel11 = new JLabel();
			jLabel11.setText("Soy información");
		}
		return jLabel11;
	}

	/**
	 * This method initializes jLabel12	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getJLabel12() {
		if (jLabel12 == null) {
			jLabel12 = new JLabel();
			jLabel12.setText("Soy información");
		}
		return jLabel12;
	}

	/**
	 * This method initializes jLabel13	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getJLabel13() {
		if (jLabel13 == null) {
			jLabel13 = new JLabel();
		}
		return jLabel13;
	}
	
}
