package graficos;

import java.awt.Canvas;
import java.awt.Image;

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
