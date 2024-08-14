package classes;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Frame {
	  private static final Sudoku sudoku = new Sudoku();
	  private static final JFrame frame = new JFrame("Sudoku");
	  private static final JTextField[][] cells = new JTextField[9][9];
	  private static JButton startButton;
	  private static JButton finishButton;
	  private static JButton giveUpButton;

	  public static void main(String[] args) {
	        
		  EventQueue.invokeLater(() -> {
			  try {
				  initialize();
				  frame.setVisible(true);
			  } catch (Exception e) {
				  e.printStackTrace();
			  }
		  });
	  }

	  private static void initialize() {
		  frame.setBounds(100, 100, 415, 500);
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      frame.getContentPane().setLayout(null);
	       
	     int cellX = 10;
	     int cellY = 10;
	     int cellWidth = 40;
	     int cellHeight = 40;

	     for (int i = 0; i < 9; i++) {
	    	 if (i != 0 && i % 3 == 0) cellY += 10;

	    	 for (int j = 0; j < 9; j++) {	               
	    		 if (j != 0 && j % 3 == 0) cellX += 10;
	    		 
	    		 JTextField cell = new JTextField();
	    		 cell.setBounds(cellX, cellY, cellWidth, cellHeight);
	             cell.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 22));
	             cell.setHorizontalAlignment(SwingConstants.CENTER);
	             cell.setEditable(false);
	             cell.addKeyListener(new KeyAdapter() {
	            	 
	            	 public void keyTyped(KeyEvent e) {
	            		 char c = e.getKeyChar();
	            		 if (((c < '1' || c > '9') && c != KeyEvent.VK_BACK_SPACE) || cell.getText().length() == 1) {
	            			 e.consume();
	            		 }
	            	 }
	             });
	             
	             frame.getContentPane().add(cell);
	             cellX = cellX + cellWidth;

	             cells[i][j] = cell;
	            }

	    	 cellX = 10;	            
	         cellY = cellY + cellHeight;
	     }

	     int btnLeftX = 25;
	     int btnRightX = 225;
	     int btnY = 410;
	     int btnWidth = 155;
	     int btnHeight = 35;
	     Font btnFont = new Font("Calibri Light", Font.BOLD, 18);

	     startButton = new JButton("Start");
	     startButton.setBounds(btnLeftX, btnY, btnWidth, btnHeight);
	     startButton.setFont(btnFont);
	     startButton.addActionListener(actionEvent -> startGame());
	     startButton.setVisible(true);
	     frame.getContentPane().add(startButton);

	     finishButton = new JButton("Submit");
	     finishButton.setBounds(btnRightX, btnY, btnWidth, btnHeight);
	     finishButton.setFont(btnFont);
	     finishButton.addActionListener(actionEvent -> finishGame());
	     finishButton.setVisible(false);
	     frame.getContentPane().add(finishButton);

	     giveUpButton = new JButton("Give Up");
	     giveUpButton.setBounds(btnLeftX, btnY, btnWidth, btnHeight);
	     giveUpButton.setFont(btnFont);
	     giveUpButton.addActionListener(actionEvent -> giveUp());
	     giveUpButton.setVisible(false);
	     frame.getContentPane().add(giveUpButton);
	  }

	  private static void startGame() {
		  
		  sudoku.startGame(cells);

		  startButton.setVisible(false);
		  finishButton.setVisible(true);
		  giveUpButton.setVisible(true);
	  }

	  private static void finishGame() {

		  sudoku.finishGame(cells);

	      startButton.setVisible(true);
	      finishButton.setVisible(false);
	      giveUpButton.setVisible(false);
	  }

	  private static void giveUp() {

		  sudoku.giveUp(cells);

		  startButton.setVisible(true);
		  finishButton.setVisible(false);
		  giveUpButton.setVisible(false);
	  }
}
