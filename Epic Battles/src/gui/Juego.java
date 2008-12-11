package gui;

import graficos.Escenario;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class Juego extends JPanel
{
	private static final long serialVersionUID = 7325275995219105393L;
	private ModoJuego parent = null;
	
	public Juego(ModoJuego parent)
	{
		super();
		this.parent = parent;
		initialize();
	}	

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize()
	{
		this.setSize(300, 200);
		this.setLayout(new BorderLayout());
		this.add(new Escenario(this), BorderLayout.CENTER);
		this.add(new Info(this), BorderLayout.EAST);
	}		
}
