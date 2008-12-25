package logica;

import graficos.Imagen;

import java.awt.Component;

public class Partida
{
	private Oponente oponente = null;
	private Tablero tablero = null;
	private Imagen imagenInfo = null;
	
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

	public Component getEscenario()
	{
		return tablero.getEscenario();
	}

	public void setImagenInfo(Imagen i)
	{
		this.imagenInfo = i;
	}
}
