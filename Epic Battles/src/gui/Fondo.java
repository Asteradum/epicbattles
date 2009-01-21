package gui;

import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

public abstract class Fondo
{
	static int i = 0;
	public static final int EscenarioRed = i++;
	public static final int EscenarioLocal = i++;
	public static final int ModoInicio = i++;
	public static final int ModoJuegoLocal = i++;
	public static final int ModoJuegoRed = i++;
	public static final int ModoOpciones = i++;
	public static final int ModoPausa = i++;
	public static final int Promocion = i++;
	
	private static String[] archivos =
	{
		"Chess_Castle_by_Vamp1r0.png",					// EscenarioRed
		"Chess_HDR_by_NeotericMetrist.png",				// EscenarioLocal
		"4ccfa6be010e7cb804564f61db2d6239.png",			// ModoInicio
		"Wanna_play_by_MovE1.png",						// ModoJuegoLocal
		"Chess_Globe_v3_by_evilhomer145.png",			// ModoJuegoRed
		"The_Acerbus_Configuration_by_steelgohst.png",	// ModoOpciones
		"",												// ModoPausa
		"A_peon__s_tale___Blessed_by_Frostwake.png"		// Promocion
	};
	
	public static Image cargar(int modo)
	{
		Image image = null;
		
		try
		{
			image = ImageIO.read(new File("imagenes/" + archivos[modo]));
		}
		catch (Exception e) { /* Si no hay fondo no pasa nada... */ }
		
		return image;
	}
}
