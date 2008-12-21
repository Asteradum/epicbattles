package piezas;

public class Rey extends Pieza
{
	private static final long serialVersionUID = 1077899422152787342L;

	@Override
	public int getTipo()
	{
		return Pieza.REY;
	}

	@Override
	public String getNombre()
	{
		return "Rey";
	}	
}
