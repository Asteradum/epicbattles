package logica;

import graficos.Escenario;

public class Partida
{
	private Oponente oponente = null;
	private Tablero tablero = null;
	
	public Partida(Oponente op)
	{
		super();
		tablero = new Tablero();
		this.oponente = op;
	}
	
	public Partida(Tablero t, Oponente op)
	{
		super();
		this.tablero = t;
		this.oponente = op;
	}
	
	public void setEscenario(Escenario e)
	{
		tablero.setEscenario(e);
	}
}
