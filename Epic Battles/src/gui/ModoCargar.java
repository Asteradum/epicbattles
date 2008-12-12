package gui;

import java.awt.FlowLayout;

import javax.swing.JPanel;

public class ModoCargar extends JPanel
{
	private static final long serialVersionUID = 467462451714083887L;
	private Principal parent = null;

	public ModoCargar(Principal parent)
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
		return "Pantalla de carga";
	}
}
