/**
 * 
 */
package juego.interfaces;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import juego.graficos.Escenario;
import juego.graficos.Imagen;
import javax.swing.BoxLayout;

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
	private JLabel jLabel1 = null;
	private JPanel texto = null;
	private JPanel scen = null;
	private Imagen image;
	
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
	private void initialize()
	{
		this.setSize(300, 200);
		this.setLayout(new BorderLayout());
		scen = new Escenario();
		this.add(getInfo(), BorderLayout.EAST);
		this.add(scen, BorderLayout.CENTER);
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
			info.setLayout(new BoxLayout(getInfo(), BoxLayout.Y_AXIS));
			image = new Imagen();
			info.add(image, null);
			info.add(getTexto(), null);
		}
		return info;
	}
	
	/**
	 * This method initializes jLabel1	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getJLabel1()
	{
		if (jLabel1 == null)
		{
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
	private JPanel getTexto()
	{
		if (texto == null)
		{
			texto = new JPanel();
			texto.setLayout(new FlowLayout());
			texto.add(getJLabel1(), null);
		}
		return texto;
	}
	
}
