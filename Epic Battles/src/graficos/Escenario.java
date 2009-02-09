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
	private Casilla[][] casillas = new Casilla[8][8];
	private Image image = null;
	private JPanel tablero = null;
	private Casilla iniMov =null;
	private Casilla finMov = null;
	//Pruebas
	
	private int pixelesX;
	private int pixelesY;
	
	public Escenario(boolean red)
	{
		super();
		initialize();
		this.setDoubleBuffered(true);
		if (red)
			image = Fondo.cargar(Fondo.EscenarioRed);
		else
			image = Fondo.cargar(Fondo.EscenarioLocal);
	}
	
	public Casilla getCasilla(int i, int j)
	{
		return casillas[i][j];
	}

	public Casilla[][] getCasillas()
	{
		return casillas;
	}
	
	public JPanel getTablero()
	{
		if (tablero == null)
		{
			Casilla c;
			boolean dib = false;
			
			GridLayout gridLayout = new GridLayout(8, 8);
			//Clase anonima apra la animacion
			tablero = new JPanel()
			{
					public void paintComponents(Graphics g)
					{
						super.paintComponents(g);	
						//Mirar paint
						//getimagen y el turno?
						g.drawImage(iniMov.getPieza().getImagen(true),0 , 0, pixelesX, pixelesY, this);
						super.paintComponents(g);							
					}
			};
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
	
	private void initialize()
	{
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets = new Insets(30, 30, 30, 30);
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridx = 0;
		this.setLayout(new GridBagLayout());
		this.add(getTablero(), gridBagConstraints);
	}

	//Metodo para la animacion
	public void animarMovimiento(Casilla ini, Casilla fin)
	{
		/*iniMov=ini;
		finMov=fin;
		pixelesX = iniMov.x * iniMov.getWidth();
		pixelesY = iniMov.y * iniMov.getHeight();
		for(;iniMov.x!=finMov.x && iniMov.y!=finMov.y;)
		{
			if (iniMov.x<finMov.x)
				pixelesX++;
			else pixelesX--;
			if (iniMov.y<finMov.y)
				pixelesY++;
			else pixelesY--;
			tablero.repaint();
		}*/
		
		
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if (image != null)
			g.drawImage(image,0,0,this.getWidth(),this.getHeight(),this);
	}
}
