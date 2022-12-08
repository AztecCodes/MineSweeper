package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 * Diese Klasse stellt einen Image Loader dar, welcher zur Aufbereitung von Grafiken bestimmt ist.
 * Alle Grafiken welche im Ablauf des Programmes sichtbar sind, basieren ursprünglich auf dieser Klasse.
 * 
 * 
 * @author  Mattia Trottmann
 * @version 1.0
 * @date 27.08.2019
 *
 */


public class ImgLoader {
	public static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(ImgLoader.class.getClassLoader().getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static BufferedImage scale(BufferedImage source, int width, int height) {
		BufferedImage scaled = new BufferedImage(width,height,source.getType());
		Graphics g = scaled.getGraphics();
		g.drawImage(source, 0, 0, width, height, null);
		g.dispose();
		return scaled;
	}
}
