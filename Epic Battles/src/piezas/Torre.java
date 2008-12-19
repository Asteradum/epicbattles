package piezas;

public class Torre extends Pieza
{
	private static final long serialVersionUID = 4081511084636434630L;
	
	public Torre(boolean color)
	{
		super(color);
	}

	@Override
	public int getTipo()
	{
		return Pieza.TORRE;
	}	
}
