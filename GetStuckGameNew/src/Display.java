import java.util.Scanner;

public class Display {
	
	private Scanner scanner;
	
	public Display() {
		scanner = new Scanner(System.in);
	}
	
	protected void printGrid(GridCell[][] gridCells, String player) {
		int x, y, r = 1;
        
                //print which players turn it is
		System.out.print(player.toUpperCase() + " turn to play\nGrid:");
		
                //loop through all gridCells
		for (x = 0; x < gridCells.length; x++) {
                        //print row numbers
			System.out.print("\n" + (x + 1) + "|  ");
			for (y = 0; y < gridCells.length; y++, r++) {
                            //print the gap
				if (gridCells[x][y].getCard().getRank() == null) {
					System.out.printf("%-25s", String.valueOf(r) + ". " + "--------------"); 
				} 
                                //print the cards which are face down
				else if (!gridCells[x][y].isFaceUp()) {
					System.out.printf("%-25s", String.valueOf(r) + ". " + "***HIDDEN***"); 
				}
                                //print the rest of the cards
				else {
					System.out.printf("%-25s", String.valueOf(r) + ". " + gridCells[x][y].getCard().toString()); 
				}
			}
		}
		System.out.println();
	}
	
        //error msg
	protected void printInvalidMove() {
		System.err.println("Move Invalid"); 
	}
	//path blocked msg
	protected void printPathBlocked() {
		System.err.println("Path blocked"); 
	}
	
        //row selection
	protected String getGridrow() {
	    System.out.println("Choose a Row (1-7)");
		String selection = scanner.next();
		return selection;
	}
	
        //column selection
	protected String getGridCol() {
		System.out.println("Choose a Column (1-7)");
		String selection = scanner.next();
		return selection;
	}
	
        //display winner
	protected void showWinner(String winner, Player red, Player black) {
		System.out.println("The winner is " + winner);
		System.out.println("Red " + red.getWins() + " - " + black.getWins() + " Black");		
	}
	
        //display restarting the game
	protected void printGameRestarting() {
		System.out.println("The game is now restarting...");
	}
}
