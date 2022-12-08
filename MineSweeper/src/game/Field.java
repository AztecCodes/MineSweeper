package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;


/**
 * Die Klasse bildet das Feld, welches im Frame aus vielen Tiles gebildet wird.
 * Ein Grossteil der Spiellogik ist hier implementiert.
 * 
 * 
 * @author  Mattia Trottmann
 * @version 1.0
 * @date 27.08.2019
 *
 */


public class Field {
	
	
	 	// Variabeln
			public static int width = 20;
			public static int height = 20;
			public int lastx;
			public int lasty;
			
			public static int AMOUNT_OF_BOMBS;
			private Random random;
			private boolean finish;
			private boolean dead;
			
			private Tile[][] tiles;
			private BufferedImage bomb = ImgLoader.scale(ImgLoader.loadImage("img/bomb.png"), Tile.getWidth(), Tile.getHeight());
			private BufferedImage flag = ImgLoader.scale(ImgLoader.loadImage("img/flag.png"), Tile.getWidth(), Tile.getHeight());
			private BufferedImage normal = ImgLoader.scale(ImgLoader.loadImage("img/normal.png"), Tile.getWidth(), Tile.getHeight());
			private BufferedImage pressed = ImgLoader.scale(ImgLoader.loadImage("img/pressed.png"), Tile.getWidth(), Tile.getHeight());
			private BufferedImage redBomb = ImgLoader.scale(ImgLoader.loadImage("img/redbomb.png"), Tile.getWidth(), Tile.getHeight());


			  /**
			   * Konstruktor des Feldes
			   * 
			   */	
			public Field() {
				random = new Random();
				tiles = new Tile[width][height];
				
				for(int x = 0; x < width;x++) {
					for(int y = 0; y < height;y++) {
						tiles[x] [y] = new Tile (x, y, normal, bomb, pressed, flag, redBomb);

					}
				}
				
				placeBombs();
				setNumbers();
			}
			
			
			
			  /**
			   *  Plaziert die Bomben durch eine For-Schleife
			   *  
			   */
			private void placeBombs() {
				for(int i = 0;i < AMOUNT_OF_BOMBS; i++) {
					placeBomb();
				}
			}
			
			  /**
			   *  Plaziert die Bomben durch Zufall
			   *  
			   */
			private void placeBomb() {
				int x = random.nextInt(width);
				int y = random.nextInt(height);
				
				if(!tiles[x] [y].isBomb()) {
					tiles[x] [y].setBomb(true);
				}
			
				else {
					placeBomb();
				}

			}
			
		
			  /**
			   *  	Stellt die Zahlen dar, welche die Anzahl benachbarter Minen anzeigt
			   *  
			   */
			private void setNumbers() {

				for(int x = 0; x < width;x++) {
					for(int y = 0; y < height;y++) { 
						int mx = x - 1;
						int gx = x + 1;
						int my = y - 1;
						int gy = y + 1;
							
						int amountOfBombs = 0;
						if (mx >= 0&&my >= 0 && tiles[mx] [my].isBomb()) {
							amountOfBombs++;
						}
						if (mx >= 0 && tiles[mx] [y].isBomb()) {
							amountOfBombs++;
						}
						if (mx >= 0&&gy < height && tiles[mx] [gy].isBomb()) {
							amountOfBombs++;
						}
						if (my >= 0&& tiles[x] [my].isBomb()) {
							amountOfBombs++;
						}
						if (gy < height && tiles[x] [gy].isBomb()) {
							amountOfBombs++;
						}
						if (gx < width && my >= 0 && tiles[gx] [my].isBomb()) {
							amountOfBombs++;
						}
						if (gx < width &&tiles[gx] [y].isBomb()) {
							amountOfBombs++;
						}
						if (gx < width && gy < height && tiles[gx] [gy].isBomb()) {
							amountOfBombs++;
						}
						
						tiles[x][y].setAmountOfNearBombs(amountOfBombs);
					}
					}
			}
			  /**
			   * Ausführung bei Linksklick.
			   *  @param x,y
			   */			
			
			public void clickedLeft(int x, int y) {
				
		if(!dead&&!finish) {		
				int tileX = x/width;
				int tileY = y/height;
				
			if (!tiles[tileX] [tileY].isFlag())	{
				tiles[tileX] [tileY].setOpened(true);
				
				if (tiles[tileX] [tileY].isBomb()) {
					 lastx = tileX;
					 lasty = tileY;
					 tiles[tileX][tileY].setRed(true);

					 dead = true;

					}
				else {
					if(tiles[tileX] [tileY].getAmountOfNearBombs() == 0) {
						open(tileX,tileY);
					}
				}
				checkFinish();
			}
		}
			}
			
			  /**
			   * Ausführung bei Rechtsklick.
			   *  @param x,y
			   */	
			public void clickedRight(int x, int y) {
				
			if(!dead&&!finish) {	
				int tileX = x/width;
				int tileY = y/height;
				tiles[tileX] [tileY].setFlag();
				
				checkFinish();
			}
			}
			  /**
			   * Überprüft ob das Spiel zu Ende ist.
			   *  
			   */	
			private void checkFinish() {
				finish = true;
			outer:	for(int x = 0; x < width;x++) {
					for(int y = 0; y < height;y++) { 
						if(!(tiles[x] [y].isOpened()||(tiles[x][y].isBomb()&&tiles[x][y].isFlag()))) {
							finish = false;
							break outer;
						}
					}
			}

			}		
			
			  /**
			   * Öffnet benachbarte leere Felder automatisch, nach MineSweeper Regelung
			   *  
			   */	
		private void open(int x, int y) {
			
			
				tiles[x][y].setOpened(true);
			if(tiles[x] [y].getAmountOfNearBombs()== 0) {
				int mx = x - 1;
				int gx = x + 1;
				int my = y - 1;
				int gy = y + 1;
				
				if (mx >= 0 && tiles [mx] [y].canOpen()) {
					open(mx,y);
				}
				if (gx < width && tiles [gx] [y].canOpen()) {
					open(gx,y);
				}
				if (my >= 0 && tiles [x] [my].canOpen()) {
					open(x,my);
				}
				if (gy < height&& tiles [x] [gy].canOpen()) {
					open(x,gy);
				}
				
				
				
				if (mx >= 0&&my >= 0 && tiles[mx] [my].canOpen()) {
						open(mx, my);		
						}
				if (mx >= 0 && tiles[mx] [y].canOpen()) {
					open(mx, y);		

				}
				if (mx >= 0&&gy < height && tiles[mx] [gy].canOpen()) {
					open(mx, gy);		

				}
				if (my >= 0&& tiles[x] [my].canOpen()) {
					open(x, my);		

				}
				if (gy < height && tiles[x] [gy].canOpen()) {
					open(x, gy);		

				}
				if (gx < width && my >= 0 && tiles[gx] [my].canOpen()) {
					open(gx, my);		

				}
				if (gx < width &&tiles[gx] [y].canOpen()) {
					open(gx, y);		

				}
				if (gx < width && gy < height && tiles[gx] [gy].canOpen()) {
					open(gx, gy);		

				}
			}
			
		}
		  /**
		   *  Zeichnet die einzelnen Felder
		   *  @param g
		   */	
			public void draw(Graphics g) {
				for(int x = 0; x < width;x++) {
					for(int y = 0; y < height;y++) {
						tiles[x] [y].draw(g);
					}
				}
				if(dead) {					
					for(int x = 0; x < width;x++) {
						for(int y = 0; y < height;y++) { 
								if (tiles [x][y].isBomb() ) {
									tiles[x][y].setOpened(true);									
									tiles[x][y].draw(g);

								}
															}
						} 
					Frame.ChangeTryAgain();

				}
				if(finish && !dead) {
				Font font = new Font("serif", Font.BOLD, 50);
					g.setColor(Color.BLACK);
					g.setFont(font);
					g.drawString("You won!", 97, 182);

				}
			}
			
			  /**
			   * Getter für die Weite des Feldes
			   *  @return width
			   */	
			public static int getWidth() {
				return width;
			}
			  /**
			   * Getter für die Anzahl von Bomben
			   *  @return AMOUNT_OF_BOMBS
			   */	
			public static  int getAmountOfBombs( ) {
				return AMOUNT_OF_BOMBS;
			}
			
			  /**
			   * Setter für die Weite des Feldes
			   *  @param AmountOfBombs
			   */	
			public static  void setAmountOfBombs(int AmountOfBombs) {
			AMOUNT_OF_BOMBS = AmountOfBombs;
			}
			
			  /**
			   * Getter für die Höhe des Feldes
			   *  @return height
			   */				
			public static int getHeight() {
				return height;
			}
	}
