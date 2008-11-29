/**
 * 
 */
package juego.graficos;

public class Punto2D
{
	/**
	 * 
	 */

	private int xPos, yPos;


	public Punto2D()
	{
		// TODO Auto-generated constructor stub
	}



	public void setPos(int xPos, int yPos)
	{
		this.xPos = xPos;
		this.yPos = yPos;
	}



	public int getXPos()
	{
		return xPos;
	}
	
	public int getYPos()
	{
		return yPos;
	}
	
	public static float distanciaAbs(Punto2D ref1, Punto2D ref2)
	{
		return (float) Math.sqrt(Math.pow(ref1.xPos-ref2.xPos,2)+Math.pow(ref1.yPos-ref2.yPos,2));
	}

	public static float distanciaIso(Punto2D ref1, Punto2D ref2, float factX, float factY)
	{
		return (float) Math.sqrt(factX*Math.pow(ref1.xPos-ref2.xPos,2)+factY*Math.pow(ref1.yPos-ref2.yPos,2));
	}

}
