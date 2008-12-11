package red;

import gui.ModoJuego;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Chat extends JPanel
{
	private static final long serialVersionUID = 1449740754230340465L;
	private ModoJuego parent = null;	
	private JTextArea chat = null;
	private JPanel send = null;
	private JButton sendButton = null;
	private JTextField message = null;	
	
	public Chat(ModoJuego parent)
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
		this.setLayout(new BorderLayout());
		this.add(getChat(), BorderLayout.CENTER);
		this.add(getSend(), BorderLayout.SOUTH);
	}

	/**
	 * This method initializes chat	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getChat()
	{
		if (chat == null)
		{
			chat = new JTextArea(20, 30);
			chat.setAutoscrolls(true);
		}
		return chat;
	}

	/**
	 * This method initializes send	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getSend()
	{
		if (send == null)
		{
			send = new JPanel();
			send.setLayout(new BorderLayout());
			send.add(getMessage(), BorderLayout.CENTER);
			send.add(getSendButton(), BorderLayout.EAST);
		}
		return send;
	}

	/**
	 * This method initializes sendButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSendButton()
	{
		if (sendButton == null)
		{
			sendButton = new JButton();
			sendButton.setText("Enviar");
		}
		return sendButton;
	}

	/**
	 * This method initializes message	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getMessage()
	{
		if (message == null)
		{
			message = new JTextField();
		}
		return message;
	}

}
