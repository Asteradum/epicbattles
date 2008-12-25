package graficos;

import gui.Fondo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import logica.Casilla;

public class Escenario extends JPanel
{
	private static final long serialVersionUID = -3141654143505676034L;
	private Image image = null;
	private JPanel tablero = null;
	private Casilla[][] casillas = new Casilla[8][8];

	public Escenario()
	{
		super();
		initialize();
		this.setDoubleBuffered(true);
		image = Fondo.cargar(Fondo.Pantalla.Escenario);
	}
	
	/**
	 * This method initializes this
	 * 
	 */
	private void initialize()
	{
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets = new Insets(30, 30, 30, 30);
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridx = 0;
		this.setLayout(new GridBagLayout());
		this.add(getTablero(), gridBagConstraints);
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if (image != null)
			g.drawImage(image,0,0,this.getWidth(),this.getHeight(),this);
	}

	/**
	 * This method initializes jPanel
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getTablero()
	{
		if (tablero == null)
		{
			Casilla c;
			boolean dib = false;
			
			GridLayout gridLayout = new GridLayout(8, 8);
			tablero = new JPanel();
			tablero.setLayout(gridLayout);
			
			for (int i=0; i<8; i++)
			{
				for (int j=0; j<8; j++)
				{
					c = new Casilla();
					c.setPreferredSize(new Dimension(40, 40));
					
					if (dib)
					{
						c.setBackground(Color.darkGray);
					}
					dib = !dib;
					
					tablero.add(c, 8*i+j);
					casillas[i][j] = c;
				}
				dib = !dib;
			}
			
			tablero.setOpaque(false);
			tablero.setBorder(LineBorder.createGrayLineBorder());
		}
		return tablero;
	}
	
	public Casilla getCasilla(int i, int j)
	{
		return casillas[i][j];
	}

	public Casilla[][] getCasillas()
	{
		return casillas;
	}
}
