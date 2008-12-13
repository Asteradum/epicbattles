package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ModoInicio extends JPanel implements ActionListener
{
	private static final long serialVersionUID = -7943530594020763917L;
	private Principal parent = null;
	private Image image = null;
	private JPanel jPanel = null;
	private JPanel botonera = null;	
	private JButton bLocal = null;
	private JButton bRed = null;
	private JButton bSalir = null;
	private JButton bOpciones = null;	
	
	public ModoInicio(Principal parent)
	{
		super();
		this.parent = parent;
		initialize();
		getBLocal().addActionListener(this);
		getBRed().addActionListener(this);
		getBOpciones().addActionListener(this);
		getBSalir().addActionListener(this);
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
		this.setPreferredSize(new Dimension(300,200));
		this.setLayout(new GridBagLayout());
		this.add(getJPanel(), gridBagConstraints);
	}

	/**
	 * This method initializes bLocal	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBLocal()
	{
		if (bLocal == null)
		{
			bLocal = new JButton();
			bLocal.setText("Juego local");
			bLocal.setName("jButton1");
		}
		return bLocal;
	}

	/**
	 * This method initializes bRed	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBRed()
	{
		if (bRed == null) 
		{
			bRed = new JButton();
			bRed.setText("Juego en red");
			bRed.setName("jButton2");
		}
		return bRed;
	}

	/**
	 * This method initializes bSalir	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBSalir()
	{
		if (bSalir == null)
		{
			bSalir = new JButton();
			bSalir.setText("Salir");
			bSalir.setName("jButton3");
		}
		return bSalir;
	}

	/**
	 * This method initializes bOpciones	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBOpciones()
	{
		if (bOpciones == null)
		{
			bOpciones = new JButton();
			bOpciones.setText("Opciones");
			bOpciones.setName("jButton4");
		}
		return bOpciones;
	}
	
	@Override
	public String toString()
	{
		return "Pantalla de inicio";
	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{	
		if (ae.getSource().equals(getBLocal()))
		{
			parent.loadRootPanel(new ModoJuegoLocal(parent));
		}
		else if (ae.getSource().equals(getBRed()))
		{
			parent.loadRootPanel(new ModoJuegoRed(parent));
		}
		else if (ae.getSource().equals(getBOpciones()))
		{
			parent.loadRootPanel(new ModoOpciones(parent));
		}
		else if (ae.getSource().equals(getBSalir()))
		{
			if (JOptionPane.showConfirmDialog(this, "¿Desea abandonar el juego?", "Salir", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			{
				System.exit(0);
			}
		}
	}

	/**
	 * This method initializes botonera	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getBotonera() 
	{
		if (botonera == null)
		{
			GridLayout gridLayout = new GridLayout(2, 2, 10, 10);
			botonera = new JPanel();
			botonera.setOpaque(false);
			botonera.setName("botonera");
			botonera.setLayout(gridLayout);
			botonera.add(getBLocal(), 0);
			botonera.add(getBRed(), 1);
			botonera.add(getBOpciones(), 2);
			botonera.add(getBSalir(), 3);
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

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel()
	{
		if (jPanel == null)
		{
			jPanel = new JPanel();
			jPanel.setOpaque(false);
			jPanel.setLayout(new FlowLayout());
			jPanel.add(getBotonera(), null);
		}
		return jPanel;
	}
}
