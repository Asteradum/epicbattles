package gui;

import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class ModoJuegoLocal extends JPanel
{
	private static final long serialVersionUID = -4236367814618101524L;
	private Principal parent = null;

	public ModoJuegoLocal(Principal parent)
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
		this.setLayout(new GridBagLayout());
	}

	@Override
	public String toString()
	{
		return "Configurar juego local";
	}
}
