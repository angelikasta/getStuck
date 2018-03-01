import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


public class Game {
	
	private static Game getStuck = new Game();
        //49 cards including a gap
	private Card cards[] = new Card[49];
	private Grid grid;
	private Display display = new Display();
	private TurnValidator validator = new TurnValidator();
	private Player red, black;
	private Player activePlayer;
        private Scanner scanner;

	
	public static void main(String[] args) {
		
		getStuck.initDeck();
		getStuck.shuffleDeck();
		getStuck.createGapInMiddle();
		getStuck.initGame();
		getStuck.runGame();
	}
	
	public void initGame() {
            //initiate the grid
		grid = new Grid(cards);
                //initiate the players
		red = new Player("red");
		black = new Player("black");
                //first player to go - red
		activePlayer = red;
	}
        
        public void initGame2(Player red, Player black) {
		grid = new Grid(cards);
                
                //change the player starting after restarting the game,
                //not fully functional
                switch (activePlayer.getColourChosen()) {
			case "red": activePlayer = black;
				break;
			case "black": activePlayer = red;
				break;
		}
	
	}
	
	public void initDeck() {
		int i = 0;
		
                //initialize the deck of cards
		for (Suits suit : Suits.values()) {
			for (Ranks rank : Ranks.values()) {
				cards[i] = new Card(suit.toString(), rank.toString());
				i++;
			}
		}
		cards[i] = new Card();
	}
	
	public void shuffleDeck() {
            //generate random number
	    Random rnd = ThreadLocalRandom.current();
	    int i;
            //shuffle card position in the array using the random index
	    for (i = cards.length - 2; i > 0; i--) {
	      int index = rnd.nextInt(i + 1);
	      Card temp = cards[index];
	      cards[index] = cards[i];
	      cards[i] = temp;
	    }
	}
	
	public void createGapInMiddle() {
                //create gap in the middle of the array
		Card temp = cards[cards.length / 2];
		cards[cards.length / 2] = cards[cards.length - 1];
		cards[cards.length - 1] = temp;
	}
	
	public void runGame() {
		boolean gameRunning = true;
		while (gameRunning) {
                    
                        
			display.printGrid(grid.getGridCells(), activePlayer.getColourChosen());
                        
			String selectedRow = display.getGridrow();
			String selectedCol = display.getGridCol();
			
                        
			if (validator.isValidMove(selectedRow, selectedCol, grid)) {
                            //rows counted from 0 but display from 1 - adjustment
				int row = Integer.parseInt(selectedRow) - 1;
                            //another adjustment
				int col = Integer.parseInt(selectedCol) - 1;
                                
                                //if mve is valid - call moveCard function
				moveCard(row, col);
			} else {
                            
				display.printInvalidMove();
			}
			if (grid.isPlayerStuck(activePlayer.getColourChosen())) {
                            //if player is stuck - decide winner
				display.printGrid(grid.getGridCells(), activePlayer.getColourChosen());
				decideWinner();
				gameRunning = false;
				
			}
		}
                //when game finished - ask if restart the game
                scanner = new Scanner(System.in);
                System.out.println("Press Y to play again");
		String sel = scanner.next();
		if (sel.equals("Y")){
                    restartGame();
                } else {
                    System.out.println("Thank you for playing"); 
                }
	}
	
	public void restartGame() {
            //reinitialize the deck with shuffled cards
		display.printGameRestarting();
		getStuck.initDeck();
		getStuck.shuffleDeck();
		getStuck.createGapInMiddle();
		getStuck.initGame2(red, black);
		getStuck.runGame();
	}
	
	protected void changePlayerTurn() {
            //players taking turns
		switch (activePlayer.getColourChosen()) {
			case "red": activePlayer = black;
				break;
			case "black": activePlayer = red;
				break;
		}
	}
	
	public void moveCard(int row, int col) {
		//check if valid suit is selected
		if (validator.isValidSuitSelected(grid.getGridCells()[row][col].getCard(), activePlayer.getColourChosen())) {
                    //check if card is aligned 
			if (grid.isCardVerticallyAligned(col) || grid.isCardHorizontallyAligned(row)) {
                            //check if it is a face card
				if (grid.isFaceCard(row, col)) {
                                    //make a move
					grid.moveCellToGap(row, col);
                                        //initiate next player turn
					changePlayerTurn();
					return;
				} else {
                                    //if itsnt not a face card - check for path block
					if (!grid.isPathBlocked(row, col)) {
                                            //make a move if not blocked
						grid.moveCellToGap(row, col);
						changePlayerTurn();
						return;
					} else {
                                            //display path blocked msg
						display.printPathBlocked();
					}
				}
			}
		}
                //display error msg
		display.printInvalidMove();
	}
	
	protected void decideWinner() {
            //check for the last player who made a move to indicate a winner
		if (activePlayer.getColourChosen().equals("red")) {
			black.setWins();
			display.showWinner(black.getColourChosen(), red, black);
		}
		else if (activePlayer.getColourChosen().equals("black")) {
			red.setWins();
			display.showWinner(red.getColourChosen(), red, black);
		}
	}
}
