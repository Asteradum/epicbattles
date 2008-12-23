package gui;

import graficos.Escenario;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.swing.JPanel;

import logica.Partida;

public class Juego extends JPanel
{
	private static final long serialVersionUID = 7325275995219105393L;
	private Principal parent = null;
	private ModoJuego juego = null;
	private Escenario escenario = null;
	private Image image = null;
	
	public Juego(Principal parent, ModoJuego mj, Partida partida)
	{
		super();
		this.parent = parent;
		this.juego = mj;
		initialize();
		partida.setEscenario(escenario);
		//this.setBorder(LineBorder.createGrayLineBorder());
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
		this.setLayout(new BorderLayout());
		this.escenario = new Escenario(parent);
		this.add(escenario, BorderLayout.CENTER);
		this.add(new Info(parent, juego), BorderLayout.SOUTH);
	}	
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g); 
	    if (image != null)
	    g.drawImage(image, 0,0,this.getWidth(),this.getHeight(),this);
	}
}
