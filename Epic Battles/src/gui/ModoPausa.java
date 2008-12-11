/**
 * 
 */
package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import java.awt.GridLayout;
import java.io.File;

public class ModoPausa extends JPanel
{
	private static final long serialVersionUID = 6099231033223006821L;
	private JComponent parent = null;
	private Image image = null;
	private JButton bMenu = null;
	private JPanel botonera = null;
	private JButton bContinuar = null;
	private JPanel jPanel = null;
	private JButton bSalir = null;
	private JButton bGuardar = null;

	public ModoPausa(JComponent parent)
	{
		super();
		this.parent = parent;
		initialize();
		try
	    {
	      image = javax.imageio.ImageIO.read(new File("imagenes/BanzaiBot-icon.gif"));
	    }
	    catch (Exception e) { /*handled in paintComponent()*/ }
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize()
	{
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets = new Insets(5, 0, 0, 0);
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridx = 0;
		this.setPreferredSize(new Dimension(200, 300));
		this.setLayout(new GridBagLayout());
		this.add(getJPanel(), gridBagConstraints);		
	}

	/**
	 * This method initializes bMenu	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBMenu() {
		if (bMenu == null) {
			bMenu = new JButton();
			bMenu.setText("Menu principal");
		}
		return bMenu;
	}
	
	public String toString()
	{
		return "Pantalla de pausa";
	}

	/**
	 * This method initializes botonera	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getBotonera() {
		if (botonera == null) {
			GridLayout gridLayout = new GridLayout(4, 1, 10, 10);
			botonera = new JPanel();
			botonera.setLayout(gridLayout);
			botonera.setOpaque(false);
			botonera.add(getBContinuar(), null);
			botonera.add(getBGuardar(), null);
			botonera.add(getBMenu(), null);
			botonera.add(getBSalir(), null);
		}
		return botonera;
	}

	/**
	 * This method initializes bContinuar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBContinuar() {
		if (bContinuar == null) {
			bContinuar = new JButton();
			bContinuar.setText("Continuar");
		}
		return bContinuar;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setOpaque(false);
			jPanel.setLayout(new FlowLayout());
			jPanel.add(getBotonera(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes bSalir	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBSalir() {
		if (bSalir == null) {
			bSalir = new JButton();
			bSalir.setText("Salir");
		}
		return bSalir;
	}

	/**
	 * This method initializes bGuardar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBGuardar() {
		if (bGuardar == null) {
			bGuardar = new JButton();
			bGuardar.setText("Guardar partida");
		}
		return bGuardar;
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g); 
	    if (image != null)
	    g.drawImage(image, 0,0,this.getWidth(),this.getHeight(),this);
	}
}
