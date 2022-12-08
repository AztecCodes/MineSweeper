package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Diese Klasse bildet die einzelnen Tiles, welche später das Field bilden.
 * 
 * @author  Mattia Trottmann
 * @version 1.0
 * @date 27.08.2019
 *
 */


public class Tile {
	
	//Variabeln
	private BufferedImage normal;
	private BufferedImage openedImg;
	private BufferedImage bombImage;
	private BufferedImage flagImage;
	private BufferedImage redBomb;


	private int x;
	private int y;
	private boolean bomb;
	private boolean opened;
	private boolean flag;
	private boolean red;

	
	private int amountOfNearBombs;
	private static int width = Frame.getFrameWidth()/Field.getWidth();
	private static int height = Frame.getFrameHeight()/Field.getHeight();
	  /**
	   * Konstruktor der Tiles
	   *  @param x,y,normal,bomb,openedImg,flag,red
	   */	
	public Tile(int x, int y, BufferedImage normal, BufferedImage bomb, BufferedImage openedImg, BufferedImage flag, BufferedImage red) {
		
		this.x = x;
		this.y = y;
		this.normal = normal;
		this.bombImage = bomb;		
		this.openedImg = openedImg;
		this.flagImage = flag;
		this.redBomb = red;

		
	}
	

	  /** Setzt ein Tile auf normal. Somit wird später ein normales Feld gezeichnet.
	   * @param normal
	   */
	public void setNormal(BufferedImage normal) {
		this.normal = normal;
	}
	
	  /** Setzt ein Tile auf rote Bombe. 
	   * @param red
	   */
	public void setRed(boolean red) {
		this.red = red;
	}
	
	  /** Setzt ein Tile auf geöffnet. 
	   * @param openedImg
	   */
	public void setOpenedImg(BufferedImage openedImg) {
		this.openedImg = openedImg;
	}
	
	  /** Fragt ab, ob ein Tile geöffnet wurde. 
	   * @return opened
	   */
	
	public boolean isOpened() {
		return opened;
	}
	
	  /** Setzt ein Tile auf geöffnet.
	   * @param opened
	   */
	
	public void setOpened(boolean opened) {
		this.opened = opened;
	}
	
	  /** Setzt ein Tile als Bombe. 
	   * @param bomb
	   */
	
	public void setBomb(boolean bomb) {
		this.bomb = bomb;
	}
	
	
	  /** Fragt ab, ob ein Tile eine Bombe ist. 
	   * @return bomb
	   */
	public boolean isBomb() {
		return bomb;
	}
	
	
	  /** Setzt ein Tile als Flagge
	   */
	public void setFlag() {
		if(flag) {
			flag = false;
		}
		else {
			if (!opened) {
				flag = true;
			}
		}
	}
	
	  /** Fragt ab, ob Tile eine Flagge hat. 
	   * @return flag
	   */
	
	public boolean isFlag() {
		return flag;
	}
	
	
	  /** Setzt bzw. liest wieviele benachbarte Bomben es gibt.
	   * @param amountOfNearBombs
	   */

	public void setAmountOfNearBombs(int amountOfNearBombs) {
		this.amountOfNearBombs = amountOfNearBombs;
	}
	
	  /** Gettet die Anahl von benachbarten Bomben. 
	   * @return amountOfNearBombs
	   */
	public int getAmountOfNearBombs() {
		return amountOfNearBombs;
	}
	
	  /** Setzt ein Tile als Bombe. 
	   * @param bomb
	   */
	public boolean canOpen() {
		return !opened&&!bomb&&amountOfNearBombs >= 0;
	}
	
	  /** Zeichnet die verschiedenen Möglichkeiten der Felder.
	   * @param g 
	   */
	public void draw(Graphics g) {
		if(!opened) {
				if(!flag) g.drawImage(normal, x * width, y * height, null);
				else g.drawImage(flagImage, x * width, y * height, null);

		}
		else {


			if (bomb) {
				g.drawImage(bombImage, x * width, y * height, null);
				  if (red) { g.drawImage(redBomb, x * width, y * height, null);
					}
			}

			else {
				g.drawImage(openedImg, x * width, y * height, null);
				if(amountOfNearBombs > 0) {
					if(amountOfNearBombs ==1) {
					g.setColor(Color.BLUE);
					}
					if(amountOfNearBombs ==2) {
					Color darkgreen = new Color(0, 100, 0);
					g.setColor(darkgreen);
					}
					if(amountOfNearBombs ==3) {
					g.setColor(Color.RED);
					}
					if(amountOfNearBombs ==4) {
					g.setColor(Color.black);
					}
					g.drawString("" + amountOfNearBombs, x * width + 7, y * height + height - 4);
				}
			}
			

		}
	}
	  /** Getter um die Weite eines Tiles zu bekommen
	   * @return width
	   */
	public static int getWidth() {
		return width;
	}
	
	  /** Getter um die Höhe eines Tiles zu bekommen
	   * @return height
	   */
	public static int getHeight() {
		return height;
	}
	
}
