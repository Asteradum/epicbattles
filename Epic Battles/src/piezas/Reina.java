package piezas;

public class Reina extends Pieza
{
	private static final long serialVersionUID = -8469892820603676425L;

	@Override
	public int getTipo()
	{
		return Pieza.REINA;
	}

	@Override
	public String getNombre()
	{
		return "Reina";
	}	
}
