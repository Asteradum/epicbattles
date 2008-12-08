/**
 * 
 */
package juego;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @author Alberto y Alvaro
 *
 */
public class Inicio extends JPanel implements ActionListener, ImageObserver
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7943530594020763917L;
	private Container parent = null;
	private JButton jButton = null;
	private JButton jButton1 = null;
	private JButton jButton2 = null;
	private JButton jButton3 = null;
	private JButton jButton4 = null;
	private JPanel botonera = null;
	Image image;
	
	/**
	 * 
	 */
	public Inicio(Container parent)
	{
		// TODO Auto-generated constructor stub
		super();
		this.parent = parent;
		initialize();
		try
	    {
	      image = javax.imageio.ImageIO.read(new File("BanzaiBot-icon.gif"));
	    }
	    catch (Exception e) { /*handled in paintComponent()*/ }
	}	

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setLayout(new FlowLayout());
		this.add(getBotonera(), null);
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("boton1");
			jButton.setName("jButton");
		}
		return jButton;
	}

	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("boton2");
			jButton1.setName("jButton1");
		}
		return jButton1;
	}

	/**
	 * This method initializes jButton2	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setText("boton3");
			jButton2.setName("jButton2");
		}
		return jButton2;
	}

	/**
	 * This method initializes jButton3	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton3() {
		if (jButton3 == null) {
			jButton3 = new JButton();
			jButton3.setText("boton5");
			jButton3.setName("jButton3");
		}
		return jButton3;
	}

	/**
	 * This method initializes jButton4	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton4() {
		if (jButton4 == null) {
			jButton4 = new JButton();
			jButton4.setText("boton4");
			jButton4.setName("jButton4");
		}
		return jButton4;
	}
	
	public String toString()
	{
		return "Pantalla de inicio";
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		// TODO Auto-generated method stub
		
		if (false)
		{
			;
		}
		else if (false)
		{
			;
		}
	}

	/**
	 * This method initializes botonera	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getBotonera() {
		if (botonera == null) {
			botonera = new JPanel();
			botonera.setLayout(new BoxLayout(getBotonera(), BoxLayout.Y_AXIS));
			botonera.add(getJButton(), null);
			botonera.add(getJButton1(), null);
			botonera.add(getJButton2(), null);
			botonera.add(getJButton4(), null);
			botonera.add(getJButton3(), null);
		}
		return botonera;
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g); 
	    if (image != null)
	    g.drawImage(image, 0,0,this.getWidth(),this.getHeight(),this);
	}

}
