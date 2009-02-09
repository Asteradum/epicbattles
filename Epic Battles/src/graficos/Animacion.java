package graficos;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;

import logica.Casilla;

public class Animacion extends Canvas {

	
	private static final long serialVersionUID = -8247688342056582760L;
	private int posicionX;
	private int posicionY;
	private int anchuraPieza;
	private int alturaPieza;
	private int posicionFinalX;
	private int posicionFinalY;
	private Image piezaAMover= null;
	
	
	public Animacion()
	{
		super();
		this.setLocation(0, 0);
		this.setSize(320, 320);
		
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		g.drawImage(piezaAMover, posicionX, posicionY, anchuraPieza, alturaPieza, this);
	}
	
	public void update()
	{
		if (posicionX<posicionFinalX)
			posicionX++;
		else posicionX--;
		if (posicionY<posicionFinalY)
			posicionY++;
		else posicionY--;
		
		repaint();
	}
	
	public void animar(Casilla ini, Casilla fin)
	{
		piezaAMover=ini.getPieza().getImagen(ini.getColor());
		posicionX= (ini.x -1) * ini.getWidth();
		anchuraPieza=ini.getWidth();
		alturaPieza= ini.getHeight();
		posicionY = (ini.y- 1) * ini.getHeight();
		posicionFinalX= fin.x * fin.getWidth();
		posicionFinalY= fin.y * fin.getHeight();
		
		for (;posicionX!=posicionFinalX && posicionY!=posicionFinalY;)
			update();
	}

	/**
	 * @param posicionX the posicionX to set
	 */
	public void setPosicionX(int posicionX) {
		this.posicionX = posicionX;
	}

	/**
	 * @return the posicionX
	 */
	public int getPosicionX() {
		return posicionX;
	}

	/**
	 * @param posicionY the posicionY to set
	 */
	public void setPosicionY(int posicionY) {
		this.posicionY = posicionY;
	}

	/**
	 * @return the posicionY
	 */
	public int getPosicionY() {
		return posicionY;
	}

}
