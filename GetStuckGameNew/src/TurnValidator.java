public class TurnValidator {
	
	protected boolean isValidMove(String row, String col, Grid grid) {
		int val, val2;
                
			val = Integer.parseInt(row);
			val2 = Integer.parseInt(col);
                        
		//check if input is between 1 and 7
		if (val < 1 || val > 7) {
                    return false;
                }
		
                //adjusting the row and column number (starting from 1 not 0)
		val -= 1;
		val2 -= 1;
		
                //check the selected card is not the gap
		if (val == grid.getGapRow() && val2 == grid.getGapCol()){
                    return false;
                }
                //check that selectd card is not the face down card
		if (!grid.getGridCells()[val][val2].isFaceUp()){
                    return false;
                }
		
		return true;
	}
	
	protected boolean isValidSuitSelected(Card card, String player) {
            //check if correct suit is chosen
		if (player.equals("red") && (card.getSuit().equals("HEARTS") || card.getSuit().equals("DIAMONDS"))){
                    return true;
                }
		else if (player.equals("black") && (card.getSuit().equals("SPADES") || card.getSuit().equals("CLUBS"))){
                    return true;
                }
		return false;
	}
}
