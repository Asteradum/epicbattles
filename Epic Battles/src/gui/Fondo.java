package gui;

import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

public abstract class Fondo
{
	public static enum Pantalla
	{
		Escenario, ModoCargar,
		ModoInicio, ModoJuegoLocal,
		ModoJuegoRed, ModoOpciones,
		ModoPausa
	}
	
	private static String[] archivos =
	{
		"A BIT OF STARWARS BOBAFETT.png",
		"BanzaiBot-icon.gif",
		"foobar2000.png",
		"millenium Finderclone.png",
		"QUAKE3.png"
	};
	
	public static Image cargar(Pantalla modo)
	{
		Image image = null;
		
		try
		{
			image = ImageIO.read(new File("imagenes/" + archivos[(int)(Math.random()*archivos.length)]));
		}
		catch (Exception e) { /*handled in paintComponent()*/ }
		
		return image;
	}
}
