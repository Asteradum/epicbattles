package gui;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.plaf.basic.BasicButtonUI;

public class MiBoton extends JButton
{
	private static final long serialVersionUID = -7883759316335518307L;

	public MiBoton(String arg0)
	{
		super(arg0);
		setFont(new Font("Verdana", Font.BOLD, 14));
		setUI(new BasicButtonUI());
	}
}
