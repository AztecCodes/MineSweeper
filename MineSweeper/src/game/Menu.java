package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Die Klasse bildet das Start Menu des Spieles.
 * Der Schwierigkeitsgrad kann hier entschieden werden.
 * 
 * 
 * @author  Mattia Trottmann
 * @version 1.0
 * @date 27.08.2019
 *
 */
public class Menu extends JFrame {
	
	//Variabeln

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JPanel difficulty;


	private JButton button;
	private JButton easy;
	private JButton normal;
	private JButton hard;

	
	  /**
	   * Konstruktor für die Erstellung eines Main Menu GUI
	   * Dieses GUI beinhaltet die Auswahl des Schwierigkeitsgrades.
	   * Durch den Hauptbutton kann man das Spiel starten.
	   */
		public Menu() {
			super("Main Menu");
			panel = new JPanel();
			difficulty = new JPanel();
			
			Color back = new Color(164,176,205);

			panel.setBackground(back);
			difficulty.setBackground(back);
			
			


			button = new JButton("Start MineSweeper");
			easy = new JButton("20 Mines (Easy)");
			normal = new JButton("40 Mines (Normal)");
			hard = new JButton("80 Mines (Hard)");


			
			add(panel,BorderLayout.CENTER);
			add(difficulty,BorderLayout.SOUTH);

			panel.add(button);
			difficulty.add(easy);

			difficulty.add(normal);

			difficulty.add(hard);
			button.setEnabled(false);
			
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setResizable(false);
		    this.pack();
		    this.setVisible(true);
		    
		    //EventListeners
		    
		    button.addActionListener(new ActionListener(){
		    	/** Bei Klick auf "Start" wird das Hauptmenü geschlossen und das Spiel geöffnet.
		    	 * @param ae
		    	 */
		        public void actionPerformed(ActionEvent ae) {
		        	dispose();
		            new Frame();
		        }


		    });
		    
		    easy.addActionListener(new ActionListener(){
		    	/** Bei Klick auf "Easy" im Hauptmenü, werden die entsprechenden Einstellungen eingestellt.
		    	 * @param ae
		    	 */
		        public void actionPerformed(ActionEvent ae) {
		       	Field.setAmountOfBombs(20);
				button.setEnabled(true);
				easy.setEnabled(false);
				normal.setEnabled(true);
				hard.setEnabled(true);


		        }


		    });
		    
		    normal.addActionListener(new ActionListener(){
		    	
		    	/** Bei Klick auf "Normal" im Hauptmenü, werden die entsprechenden Einstellungen eingestellt.
		    	 * @param ae
		    	 */
		        public void actionPerformed(ActionEvent ae) {
			       	Field.setAmountOfBombs(40);
					button.setEnabled(true);
					normal.setEnabled(false);
					easy.setEnabled(true);
					hard.setEnabled(true);




		        }


		    });
		    
		    hard.addActionListener(new ActionListener(){
		    	/** Bei Klick auf "Hard" im Hauptmenü, werden die entsprechenden Einstellungen eingestellt.
		    	 * @param ae
		    	 */
		        public void actionPerformed(ActionEvent ae) {
			       	Field.setAmountOfBombs(80);
					button.setEnabled(true);
					hard.setEnabled(false);
					easy.setEnabled(true);
					normal.setEnabled(true);


		        }


		    });
		
		}
		

		
}
