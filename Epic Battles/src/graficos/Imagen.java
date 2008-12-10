package graficos;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.swing.JPanel;

public class Imagen extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6882189812615089538L;
	private Image image = null;

	public Imagen() {
		// TODO Auto-generated constructor stub
		super();
		initialize();
		//this.setSize(90, 90);
		try
	    {
	      image = javax.imageio.ImageIO.read(new File("BanzaiBot-icon.gif"));
	    }
	    catch (Exception e) { /*handled in paintComponent()*/ }
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setLayout(new FlowLayout());
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g); 
	    if (image != null)
	    g.drawImage(image, 0,0,this.getWidth(),this.getHeight(),this);
	}

}
