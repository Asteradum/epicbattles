package piezas;

public class Reina extends Pieza
{
	private static final long serialVersionUID = -8469892820603676425L;
	
	public Reina(boolean color)
	{
		super(color);
	}

	@Override
	public int getTipo()
	{
		return Pieza.REINA;
	}	
}
