package gui;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class ModoOpciones extends JPanel
{
	private static final long serialVersionUID = -6136942568439299757L;
	private JPanel parent = null;
	
	public ModoOpciones(JPanel parent)
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
