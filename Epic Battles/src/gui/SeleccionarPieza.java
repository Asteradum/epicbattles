package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JPanel;

import logica.piezas.Alfil;
import logica.piezas.Caballo;
import logica.piezas.Pieza;
import logica.piezas.Reina;
import logica.piezas.Torre;

public class SeleccionarPieza extends JDialog implements ActionListener
{
	private static final long serialVersionUID = 5832293965968994352L;
	private MiBoton bAlfil = null;
	private MiBoton bCaballo = null;
	private MiBoton bReina = null;
	private MiBoton bTorre = null;
	private JPanel panel = null;
	private JPanel botonera = null;
	private Frame frame = null;
	private boolean color;
	private Pieza opcion = null;
	private Image image = null;
	
	public SeleccionarPieza(Frame frame, boolean color)
	{
		super(frame, true);
		this.frame = frame;
		this.color = color;
		initialize();
		
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter()
		{ public void windowClosing(WindowEvent we) {}	});

		image = Fondo.cargar(Fondo.Promocion);
		pack();
		setLocationRelativeTo(frame);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		try
		{
			if (ae.getSource() == bAlfil)
			{
				opcion = new Alfil();
			}
			else if (ae.getSource() == bCaballo)
			{
				opcion = new Caballo();
			}
			else if (ae.getSource() == bReina)
			{
				opcion = new Reina();
			}
			else if (ae.getSource() == bTorre)
			{
				opcion = new Torre();
			}
		}
		catch (Exception e)
		{
			((Principal) frame).setHelp(e.getMessage());
		}
		
		setVisible(false);
	}

	/**
	 * This method initializes bAlfil	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private MiBoton getBAlfil()
	{
		if (bAlfil == null)
		{
			try
			{
				bAlfil = new MiBoton("")
				{
					private static final long serialVersionUID = -4292939848931950501L;
					private Image image = new Alfil().getImagen(color);

					@Override
					protected void paintComponent(Graphics g)
					{
						super.paintComponent(g);
						g.drawImage(image, 0,0,this.getWidth(),this.getHeight(),this);
					}
				};
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			bAlfil.setPreferredSize(new Dimension(60, 70));
			bAlfil.addActionListener(this);
		}
		return bAlfil;
	}

	/**
	 * This method initializes bCaballo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private MiBoton getBCaballo()
	{
		if (bCaballo == null)
		{
			try
			{
				bCaballo = new MiBoton("")
				{
					private static final long serialVersionUID = 1933539406529644438L;
					private Image image = new Caballo().getImagen(color);

					@Override
					protected void paintComponent(Graphics g)
					{
						super.paintComponent(g);
						g.drawImage(image, 0,0,this.getWidth(),this.getHeight(),this);
					}
				};
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			bCaballo.setPreferredSize(new Dimension(60, 70));
			bCaballo.addActionListener(this);
		}
		return bCaballo;
	}

	/**
	 * This method initializes bReina	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private MiBoton getBReina()
	{
		if (bReina == null) 
		{
			try
			{
				bReina = new MiBoton("")
				{
					private static final long serialVersionUID = -8965922067985387060L;
					private Image image = new Reina().getImagen(color);

					@Override
					protected void paintComponent(Graphics g)
					{
						super.paintComponent(g);
						g.drawImage(image, 0,0,this.getWidth(),this.getHeight(),this);
					}
				};
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			bReina.setPreferredSize(new Dimension(60, 70));
			bReina.addActionListener(this);
		}
		return bReina;
	}

	/**
	 * This method initializes bTorre	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private MiBoton getBTorre()
	{
		if (bTorre == null)
		{
			try
			{
				bTorre = new MiBoton("")
				{
					private static final long serialVersionUID = -4965165659906129588L;
					private Image image = new Torre().getImagen(color);

					@Override
					protected void paintComponent(Graphics g)
					{
						super.paintComponent(g);
						g.drawImage(image, 0,0,this.getWidth(),this.getHeight(),this);
					}
				};
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			bTorre.setPreferredSize(new Dimension(60, 70));
			bTorre.addActionListener(this);
		}
		return bTorre;
	}
	
	private JPanel getBotonera()
	{
		if (botonera == null)
		{
			FlowLayout fl = new FlowLayout();
			fl.setHgap(30);
			botonera = new JPanel();
			botonera.setLayout(fl);
			botonera.add(getBAlfil());
			botonera.add(getBCaballo());
			botonera.add(getBTorre());
			botonera.add(getBReina());
		}
		return botonera;
	}

	/**
	 * This method initializes panel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanel()
	{
		if (panel == null)
		{
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.insets = new Insets(0, 0, 0, 0);
			gridBagConstraints.gridy = 0;
			gridBagConstraints.gridx = 0;
			panel = new JPanel();
			panel.setLayout(new GridBagLayout());
			panel.setPreferredSize(new Dimension(597, 446));
			panel.add(getBotonera(), gridBagConstraints);
		}
		return panel;
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize()
	{
        this.setContentPane(getPanel());
        this.setTitle("Promoción del peón");
        this.setResizable(false);
	}

	public Pieza getOpcion()
	{
		return opcion;
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		if (image != null)
			getGraphics().drawImage(image, 0, 0, getWidth(), getHeight(), this);
	}
}