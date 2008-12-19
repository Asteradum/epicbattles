package piezas;

public class Alfil extends Pieza
{
	private static final long serialVersionUID = 1646351736441866648L;
	
	public Alfil(boolean color)
	{
		super(color);		
	}	

	@Override
	public int getTipo()
	{
		return Pieza.ALFIL;
	}
}
