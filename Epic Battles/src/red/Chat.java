package red;

import gui.Principal;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Chat extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1449740754230340465L;
	private Principal parent = null;	
	private JTextArea chat = null;
	private JPanel send = null;
	private JButton sendButton = null;
	private JTextField message = null;	
	
	public Chat(Principal parent)
	{
		super();
		this.parent = parent;
		initialize();
		getSendButton().addActionListener(this);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize()
	{
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(100,100));
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

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource().equals(getSendButton()))
		{
			getChat().append(getMessage().getText());
			getMessage().setText("");
		}
	}
}
