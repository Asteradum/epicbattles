package gui;

import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

public abstract class Fondo
{
	public static enum Pantalla
	{
		EscenarioRed, EscenarioLocal, ModoInicio,
		ModoJuegoLocal, ModoJuegoRed, ModoOpciones,
		ModoPausa, Promocion
	}
	
	private static String[] archivos =
	{
		"Chess_Castle_by_Vamp1r0.png",					// EscenarioRed
		"Chess_HDR_by_NeotericMetrist.png",				// EscenarioLocal
		"4ccfa6be010e7cb804564f61db2d6239.png",			// ModoInicio
		"Wanna_play_by_MovE1.png",						// ModoJuegoLocal
		"Chess_Globe_v3_by_evilhomer145.png",			// ModoJuegoRed
		"The_Acerbus_Configuration_by_steelgohst.png",	// ModoOpciones
		"Chess_Clock_by_Menotodokukagiri.png",			// ModoPausa
		"A_peon__s_tale___Blessed_by_Frostwake.png"		// Promocion
	};
	
	public static Image cargar(Pantalla modo)
	{
		Image image = null;
		
		try
		{
			image = ImageIO.read(new File("imagenes/" + archivos[modo.ordinal()]));
		}
		catch (Exception e) { /* Si no hay fondo no pasa nada... */ }
		
		return image;
	}
}
