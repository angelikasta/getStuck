
public class Card {
	
	private String suit;
	private String rank;
	private String colour;
	
	
	public Card() {
	}
	
	public Card(String suit, String rank) {
		this.suit = suit;
		this.rank = rank;
	}

	public String getSuit() {
		return suit;
	}

	public void setSuit(String suit) {
		this.suit = suit;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	
	@Override
	public String toString() {
		return ("[" + suit + " " + rank + "]");
	}
}