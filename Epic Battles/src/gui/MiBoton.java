package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.plaf.basic.BasicButtonUI;

public class MiBoton extends JButton implements MouseListener
{
	private static final long serialVersionUID = -7883759316335518307L;
	private Image image = null;

	public MiBoton(String arg0)
	{
		super(arg0);
		setFont(new Font("Verdana", Font.BOLD, 14));
		setUI(new BasicButtonUI());
		setForeground(Color.darkGray);
		addMouseListener(this);
	}
	
	public MiBoton(Image i)
	{
		super();
		this.image = i;
		setFont(new Font("Verdana", Font.BOLD, 14));
		setUI(new BasicButtonUI());
	}

	@Override
	public void mouseClicked(MouseEvent e) { }

	@Override
	public void mouseEntered(MouseEvent e)
	{
		setForeground(new Color(60, 23, 232));
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		setForeground(Color.darkGray);
	}

	@Override
	public void mousePressed(MouseEvent e) { }

	@Override
	public void mouseReleased(MouseEvent e) { }
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if (image != null)
			g.drawImage(image,0,0,this.getWidth(),this.getHeight(),this);
	}
}
