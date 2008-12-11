package graficos;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.io.File;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;

public class Escenario extends JPanel
{
	private static final long serialVersionUID = -3141654143505676034L;
	private JComponent parent = null;
	private Image image = null;
	private JPanel jPanel = null;

	public Escenario(JComponent parent)
	{
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
	 */
	private void initialize() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(5, 0, 0, 0);
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
					jp.setPreferredSize(new Dimension(48, 48));
					if (dib)
					{
						jp.setBackground(Color.BLACK);					
					}
					dib = !dib;
					jPanel.add(jp, 8*i+j);
				}
				dib = !dib;
			}
				jPanel.setOpaque(false);
		}
		return jPanel;
	}
} 
