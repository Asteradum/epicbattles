package gui;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import red.Chat;

public class ModoJuego extends JSplitPane
{
	private static final long serialVersionUID = -8925322799587248232L;
	private JPanel parent = null;
	
	public ModoJuego(JPanel parent, boolean red)
	{
		super();
		this.parent = parent;
		this.setOrientation(VERTICAL_SPLIT);
		this.setTopComponent(new Juego(this));	
		
		if (red)
		{				
			this.setBottomComponent(new Chat(this));
			this.setDividerSize(5);
			this.setOneTouchExpandable(true);
		}
		else
		{	
			this.setDividerSize(0);		
		}
	}
}
