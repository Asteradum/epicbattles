package gui;

import javax.swing.JSplitPane;

import logica.Partida;

public class ModoJuego extends JSplitPane
{
	private static final long serialVersionUID = -8925322799587248232L;
	private Principal parent = null;
	private Partida partida = null;
	
	public ModoJuego(Principal parent, boolean red, Partida partida)
	{
		super();
		this.parent = parent;
		this.partida = partida;
		this.setOrientation(HORIZONTAL_SPLIT);
		this.setTopComponent(new Juego(parent, this, partida));	
		
		if (red)
		{				
			this.setBottomComponent(new Chat(parent));
		}
		else
		{
			this.setBottomComponent(null);	
		}
		this.setDividerSize(0);
	}
	
	@Override
	public String toString()
	{
		return "Pantalla de juego";
	}
}
