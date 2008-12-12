package gui;

import java.awt.FlowLayout;

import javax.swing.JPanel;

public class Partidas extends JPanel
{
	private static final long serialVersionUID = -7480625156531026554L;
	private Principal parent = null;

	public Partidas(Principal parent)
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
}
