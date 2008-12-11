package gui;

import java.awt.GridBagLayout;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class ModoJuegoRed extends JPanel
{
	private static final long serialVersionUID = -8513250153154802461L;
	private Principal parent = null;

	public ModoJuegoRed(Principal parent)
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
		return "Configurar juego en red";
	}
}
