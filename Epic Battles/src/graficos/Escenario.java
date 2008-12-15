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
	private JPanel jPanel = null;

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
	private void initialize() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(30, 30, 30, 30);
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridx = 0;
        this.setLayout(new GridBagLayout());
        this.add(getJPanel(), gridBagConstraints);			
	}

	@Override
	public void paintComponent(Graphics g)
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
			JPanel jp;
			boolean dib = false;
			
			GridLayout gridLayout = new GridLayout(8, 8);
			jPanel = new JPanel();
			jPanel.setLayout(gridLayout);
			for (int i=0; i<8; i++)
			{
				for (int j=0; j<8; j++)
				{				
					jp = new JPanel();
					jp.setPreferredSize(new Dimension(40, 40));
					if (dib)
					{
						jp.setBackground(Color.darkGray);					
					}
					dib = !dib;
					jPanel.add(jp, 8*i+j);
				}
				dib = !dib;
			}
			jPanel.setOpaque(false);
			jPanel.setBorder(LineBorder.createGrayLineBorder());
		}
		return jPanel;
	}
}
