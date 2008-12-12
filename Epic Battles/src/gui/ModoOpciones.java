package gui;

import java.awt.FlowLayout;

import javax.swing.JPanel;

public class ModoOpciones extends JPanel
{
	private static final long serialVersionUID = -6136942568439299757L;
	private Principal parent = null;
	
	public ModoOpciones(Principal parent)
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
		this.setLayout(new FlowLayout());
	}

	@Override
	public String toString()
	{
		return "Pantalla de opciones";
	}
}
