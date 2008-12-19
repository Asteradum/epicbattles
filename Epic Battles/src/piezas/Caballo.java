package piezas;

public class Caballo extends Pieza
{
	private static final long serialVersionUID = 3449768679721979235L;
	
	public Caballo(boolean color)
	{
		super(color);
	}

	@Override
	public int getTipo()
	{
		return Pieza.CABALLO;
	}		
}
