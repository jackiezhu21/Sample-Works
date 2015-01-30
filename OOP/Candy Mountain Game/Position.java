public class Position {

    private int row;
    private int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Position update(Move move) {
        this.row = row + move.getRowDelta();
        this.col = col + move.getColDelta();
        Position position = new Position(row, col);
        return position;
    }

    public String toString() {
        String posStr = "(" + row + "," + col + ")";
        return posStr;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public boolean equals(Object other) {
        Position comparePos = (Position) other;
        if (this.row == comparePos.row && this.col == comparePos.col) {
            return true;
        }
        return false;
    }

}