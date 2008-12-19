package piezas;

public class Peon extends Pieza
{
	private static final long serialVersionUID = -5218659293609981905L;
	
	public Peon(boolean color)
	{
		super(color);
	}	

	@Override
	public int getTipo()
	{
		return Pieza.PEON;
	}
}
