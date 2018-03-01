
public class Player {
	
	private String colourChosen;
	private int wins = 0;
	private int score = 0;
	
	public Player(String colourChosen) {
		this.colourChosen = colourChosen;
	}

	public String getColourChosen() {
		return colourChosen;
	}

	public void setColourChosen(String colourChosen) {
		this.colourChosen = colourChosen;
	}

	public int getWins() {
		return wins;
	}

	public void setWins() {
		this.wins += 1;
	}
	
	public int getScore() {
		return score;
	}
	
	public void resetScore() {
		this.score = 0;
	}
}
