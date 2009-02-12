package graficos;

import java.awt.Canvas;
import java.awt.Image;

/**
 * Sprite es un Canvas abstracto con una x e y p�blicas asociadas
 * @author Alberto y Alvaro
 */
public abstract class Sprite extends Canvas
{
	private static final long serialVersionUID = 4039319764122648832L;
	protected Image imagen;
	public int x;
	public int y;

	public Sprite()
	{
		super();
	}
}
