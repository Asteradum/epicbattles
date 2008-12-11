package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Principal extends JFrame
{
	private static final long serialVersionUID = -3006194651918420869L;
	private JPanel jContentPane = null;
	private JPanel panel = null;
	private JLabel help = null;
	
	public Principal() throws HeadlessException
	{
		super();
		initialize();
		//this.setResizable(false);
	}	

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize()
	{
		this.setContentPane(getJContentPane());
		this.setTitle("Epic Battles v0.1");
		this.loadRootPanel(new ModoPausa(getJContentPane()));
	}	

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane()
	{
		if (jContentPane == null)
		{
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getHelp(), BorderLayout.SOUTH);
		}
		return jContentPane;
	}	

	/**
	 * This method initializes help	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getHelp()
	{
		if (help == null)
		{
			help = new JLabel();
			help.setBorder(new LineBorder(Color.gray, 1));
			help.setForeground(Color.gray);
			help.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 11));
			help.setText("");
			help.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return help;
	}
	
	public void setHelp(String text)
	{
		getHelp().setText(text);
	}
	
	public void loadRootPanel(JPanel jp)
	{
		if (panel != null) jContentPane.remove(panel);
		panel = jp;
		setHelp(jp.toString());
		jContentPane.add(panel, BorderLayout.CENTER);
		this.pack();
	}

}
