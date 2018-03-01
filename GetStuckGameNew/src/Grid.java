
public class Grid {

    //2d array of gridCell objects
    private GridCell[][] gridCells = new GridCell[7][7];
    private int gap;
    private int gapRow;
    private int gapCol;

    public Grid(Card[] cards) {

        placeCardsinGridCells(cards);
        //place gap in the middle of the array
        setGap(cards.length / 2);
        //place gap in the middle of the grid
        setGapRow(3);
        setGapCol(3);
    }

    private void placeCardsinGridCells(Card[] cards) {

        int x, y, k;
        k = 0;

        //place cards in the grid
        for (x = 0; x < gridCells.length; x++) {
            for (y = 0; y < gridCells.length; y++) {
                gridCells[x][y] = new GridCell(cards[k++]);
            }
        }
    }

    public GridCell[][] getGridCells() {
        return gridCells;
    }

    public void setGridCells(GridCell[][] gridCells) {
        this.gridCells = gridCells;
    }

    public void moveCellToGap(int row, int col) {

        //place card facedown
        gridCells[row][col].setFaceUp(false);

        //move the card to the gap, move the gap
        GridCell temp = gridCells[gapRow][gapCol];
        gridCells[gapRow][gapCol] = gridCells[row][col];
        gridCells[row][col] = temp;
        gapRow = row;
        gapCol = col;
    }

    protected boolean isPlayerStuck(String player) {
        //not fully working functioning

        int i, j;
        String suit1 = "";
        String suit2 = "";

        if (player.equals("red")) {
            suit1 = "HEARTS";
            suit2 = "DIAMONDS";
        } else if (player.equals("black")) {
            suit1 = "SPADES";
            suit2 = "CLUBS";
        }

        for (i = 0, j = 0; i < gridCells.length; i++, j++) {

            if (i == gapRow) {
                continue;
            }
            if (j == gapCol) {
                continue;
            }

            String suit = gridCells[i][gapCol].getCard().getSuit();
            String suit22 = gridCells[gapRow][j].getCard().getSuit();

            //check if there is blocked path in this row
            if (suit.equals(suit1) || suit.equals(suit2)) {
                if (!isRowPathBlocked(i)) {
                    return false;
                }
            }
            //check if there is blocked path in this column
            if (suit22.equals(suit1) || suit.equals(suit2)) {
                if (!isColPathBlocked(j)) {
                    return false;
                }
            }
        }
        //return true if both paths are blocked
        return true;
    }

    protected boolean isFaceCard(int row, int col) {
        
        //check if it is a face card
        switch (gridCells[row][col].getCard().getRank()) {
            case "JACK":
                return true;
            case "QUEEN":
                return true;
            case "KING":
                return true;
            default:
                return false;
        }
    }

    protected boolean isPathBlocked(int row, int col) {
        //check each path for block
        if (row == gapRow) {
            System.out.println("Same row as the gap");
            if (isColPathBlocked(col)) {
                return true;
            }
        } else if (col == gapCol) {
            System.out.println("Same column as the gap");
            if (isRowPathBlocked(row)) {
                return true;
            }
        }
        return false;
    }

    protected boolean isColPathBlocked(int col) {
        
        //check if the gap is in the same column
        while (col != gapCol) {
            //if its not in same column as the gap check if there are any cards faceup
            //in the same row (each column)
            if (!gridCells[gapRow][col].isFaceUp()) {
                return true;
            }
            //keep checking each column
            col = (col < gapCol) ? col + 1 : col - 1;
        }
        return false;
    }

    protected boolean isRowPathBlocked(int row) {
        //check if the card is in same row
        while (row != gapRow) {
            //if its not in same column check if there are any cards faceup
            //in the same column
            if (!gridCells[row][gapCol].isFaceUp()) {
                return true;
            }
            //carry on checking each column
            row = (row < gapRow) ? row + 1 : row - 1;
        }
        return false;
    }

    protected boolean isCardHorizontallyAligned(int rowSel) {
        //check if card is in the same row
        if (rowSel == gapRow) {
            return true;
        }
        return false;
    }

    protected boolean isCardVerticallyAligned(int colSel) {
        //check if card is in the same column
        if (colSel == gapCol) {
            return true;
        }
        return false;
    }

    public int getGap() {
        return gap;
    }

    public void setGap(int gap) {
        this.gap = gap;
    }

    public int getGapRow() {
        return gapRow;
    }

    public void setGapRow(int row) {
        this.gapRow = row;
    }

    public int getGapCol() {
        return gapCol;
    }

    public void setGapCol(int col) {
        this.gapCol = col;
    }
}
