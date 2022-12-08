package game;

import java.awt.BorderLayout;


import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

/**
 * Die Klasse bildet das GUI des Hauptspieles.
 * Verschiedenste Event Listener sind hier implementiert.
 * 
 * 
 * @author  Mattia Trottmann
 * @version 1.0
 * @date 27.08.2019
 *
 */

public class Frame extends JFrame implements MouseListener {
	
	//Variabeln
	private static final long serialVersionUID = 1L;
	private static int width = 400;
	private static int height = 400;
	
	private Screen screen;
	private Field field;
	private Font font;
	
	JButton btnMenu = new JButton("Menu");
	JButton btnDelete = new JButton("Reset");
	JLabel mines = new JLabel("Minen: " + Field.getAmountOfBombs());
	
	static JLabel tryagain = new JLabel("PRESS");
	static JLabel againtry = new JLabel(" RESET");

	JButton placeholder = new JButton("");
	JToolBar toolBar = new JToolBar();	
	
	private int insetLeft;
	private int insetTop;

	
	/**
	 * Konstruktor der Klasse Frame. Bildet das GUI.
	 *
	 */
	public Frame () {
		super("MineSweeper");
		font = new Font("serif", Font.BOLD, 20);
		font = new Font("serif", Font.BOLD, 20);

		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addMouseListener(this);
		screen = new Screen();
		field = new Field();
		
		tryagain.setFont(font);
		againtry.setFont(font);
		tryagain.setText("             ");
		againtry.setText("             ");

		toolBar.add(btnMenu);
	
		
		for(int i = 0; i < 4; i++) {
		toolBar.addSeparator();
		}
		toolBar.add(tryagain);
		for(int i = 0; i < 3; i++) {
		toolBar.addSeparator();
		}

		toolBar.add(btnDelete);
		
		for(int i = 0; i <3; i++) {
		toolBar.addSeparator();
		}
		
		toolBar.add(againtry);

		for(int i = 0; i < 2; i++) {
		toolBar.addSeparator();
		}


		toolBar.add(mines);

		toolBar.setFloatable(false);

		
		add(toolBar,BorderLayout.NORTH);
		add(screen);
		pack();
		insetLeft = getInsets().left;
		insetTop = getInsets().top;

		setSize(width + getInsets().left + getInsets().right, height + getInsets().top + getInsets().bottom + 32);
		setLocationRelativeTo(null);
		setVisible(true);
		
		// Event Listener
		
	    btnMenu.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent ae) {
	        	dispose();
	            new Menu();

	            
	        }
	       
	    });
	    
	    btnDelete.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent ae) {
	        	dispose();
	            new Frame();
   
	        }
	    });
	}
	
	// MouseListener
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getButton() == 1) field.clickedLeft(e.getX() - insetLeft, e.getY() - insetTop - 32);
		if(e.getButton() == 3) field.clickedRight(e.getX() - insetLeft, e.getY() - insetTop -32);
		screen.repaint();
	}

	
	/**
	 * 
	 * Die Klasse Screem führt die Zeichnung des Fieldes auf dem Frame aus.
	 *
	 */
	public class Screen extends JPanel {
		private static final long serialVersionUID = 1L;
	
		// Erstellt ein farbiges Viereck
		public void paintComponent(Graphics g) {
			g.setFont(font);
			field.draw(g);
		}
	}
	
	
	/**
	 * Wenn das Spiel verloren wurde, wird diese Aufforderung angezeigt.
	 *
	 */
	public static void ChangeTryAgain() {
		tryagain.setText("PRESS");
		againtry.setText(" RESET");

	}
	

	/**
	 * Gibt den Wert der Weite des JFrames zurück
	 * @return width
	 */
	public static int getFrameWidth() {
		return width;
	}
	
	/**
	 * Gibt den Wert der Höhe des JFrames zurück
	 * @return height
	 */
	public static int getFrameHeight() {
		return height;
	}
	

}
