package gui;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import red.Chat;

public class ModoJuego extends JSplitPane
{
	private static final long serialVersionUID = -8925322799587248232L;
	private Principal parent = null;
	
	public ModoJuego(Principal parent, boolean red)
	{
		super();
		this.parent = parent;
		this.setOrientation(VERTICAL_SPLIT);
		this.setTopComponent(new Juego(parent));	
		
		if (red)
		{				
			this.setBottomComponent(new Chat(parent));
			this.setDividerSize(5);
			this.setOneTouchExpandable(true);
		}
		else
		{
			this.setBottomComponent(null);
			this.setDividerSize(0);		
		}
	}
	
	@Override
	public String toString()
	{
		return "Pantalla de juego";
	}
}
