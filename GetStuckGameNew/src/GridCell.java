public class GridCell {
	
	private Card card;

	private boolean faceUp;
	
	public GridCell(Card card) {
		this.card = card;
		this.faceUp = true;
	}
	
	public Card getCard() {
		return card;
	}

	public boolean isFaceUp() {
		return faceUp;
	}

	public void setFaceUp(boolean faceUp) {
		this.faceUp = faceUp;
	}
}
