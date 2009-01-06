package gui;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
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
	private JButton bAlfil = null;
	private JButton bCaballo = null;
	private JButton bReina = null;
	private JButton bTorre = null;
	private JPanel panel = null;
	private Frame frame = null;
	private boolean color;
	private Pieza opcion = null;
	
	public SeleccionarPieza(Frame frame, boolean color)
	{
		super(frame, true);
		this.frame = frame;
		this.color = color;
		initialize();
		
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter()
		{ public void windowClosing(WindowEvent we) {}	});

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
	private JButton getBAlfil()
	{
		if (bAlfil == null)
		{
			bAlfil = new JButton()
			{
				private static final long serialVersionUID = -4292939848931950501L;

				@Override
				protected void paintComponent(Graphics g)
				{
					super.paintComponent(g);
					try
					{
						g.drawImage(new Alfil().getImagen(color), 0,0,this.getWidth(),this.getHeight(),this);
					}
					catch (Exception e) {}
				}
			};
			bAlfil.setPreferredSize(new Dimension(35, 70));
			bAlfil.addActionListener(this);
		}
		return bAlfil;
	}

	/**
	 * This method initializes bCaballo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBCaballo()
	{
		if (bCaballo == null)
		{
			bCaballo = new JButton()
			{
				private static final long serialVersionUID = 1933539406529644438L;

				@Override
				protected void paintComponent(Graphics g)
				{
					super.paintComponent(g);
					try
					{
						g.drawImage(new Caballo().getImagen(color), 0,0,this.getWidth(),this.getHeight(),this);
					}
					catch (Exception e) {}
				}
			};
			bCaballo.setPreferredSize(new Dimension(35, 70));
			bCaballo.addActionListener(this);
		}
		return bCaballo;
	}

	/**
	 * This method initializes bReina	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBReina()
	{
		if (bReina == null) 
		{
			bReina = new JButton()
			{
				private static final long serialVersionUID = -8965922067985387060L;

				@Override
				protected void paintComponent(Graphics g)
				{
					super.paintComponent(g);
					try
					{
						g.drawImage(new Reina().getImagen(color), 0,0,this.getWidth(),this.getHeight(),this);
					}
					catch (Exception e) {}
				}
			};
			bReina.setPreferredSize(new Dimension(35, 70));
			bReina.addActionListener(this);
		}
		return bReina;
	}

	/**
	 * This method initializes bTorre	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBTorre()
	{
		if (bTorre == null)
		{
			bTorre = new JButton()
			{
				private static final long serialVersionUID = -4965165659906129588L;

				@Override
				protected void paintComponent(Graphics g)
				{
					super.paintComponent(g);
					try
					{
						g.drawImage(new Torre().getImagen(color), 0,0,this.getWidth(),this.getHeight(),this);
					}
					catch (Exception e) {}
				}
			};
			bTorre.setPreferredSize(new Dimension(35, 70));
			bTorre.addActionListener(this);
		}
		return bTorre;
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
			panel = new JPanel();
			panel.setLayout(new GridLayout(2, 2));
			panel.add(getBAlfil());
			panel.add(getBCaballo());
			panel.add(getBTorre());
			panel.add(getBReina());
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
        this.setResizable(false);
	}

	public Pieza getOpcion()
	{
		return opcion;
	}
}