package graficos;

import gui.Fondo;
import gui.Principal;

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

public class Escenario extends JPanel
{
	private static final long serialVersionUID = -3141654143505676034L;
	private Principal parent = null;
	private Image image = null;
	private JPanel tablero = null;
	private JPanel[][] casillas = new JPanel[8][8];

	public Escenario(Principal parent)
	{
		super();
		this.parent = parent;
		initialize();		
		image = Fondo.cargar(Fondo.Pantalla.Escenario);
		//this.setBorder(LineBorder.createGrayLineBorder());
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
	@SuppressWarnings("serial")
	private JPanel getTablero()
	{
		if (tablero == null)
		{
			JPanel jp;
			boolean dib = false;
			
			GridLayout gridLayout = new GridLayout(8, 8);
			tablero = new JPanel();
			tablero.setLayout(gridLayout);
			for (int i=0; i<8; i++)
			{
				for (int j=0; j<8; j++)
				{
					jp = new JPanel()
					{
						@Override
						public void paintComponent(Graphics g)
						{
							super.paintComponent(g);
						}
					};
					jp.setPreferredSize(new Dimension(40, 40));
					if (dib)
					{
						jp.setBackground(Color.darkGray);					
					}
					dib = !dib;
					tablero.add(jp, 8*i+j);
					casillas[i][j] = jp;
				}
				dib = !dib;
			}
			tablero.setOpaque(false);
			tablero.setBorder(LineBorder.createGrayLineBorder());
		}
		return tablero;
	}
	
	public JPanel getCasilla(int i, int j)
	{
		return casillas[i][j];
	}
}
