package epicbattles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import gui.Principal;

/**
 * @author Alberto y Alvaro
 *
 */
public final class Main
{
	public static void main(String[] args)
	{
		try
		{
			AudioPlayer.player.start(new AudioStream(new FileInputStream("sonidos/erased.wav")));
		}
		catch (FileNotFoundException fnfe)
		{
			fnfe.printStackTrace();
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
		
		new Principal().setVisible(true);
	}
}
