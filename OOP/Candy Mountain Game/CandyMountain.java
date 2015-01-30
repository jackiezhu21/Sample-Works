import java.util.Random;

public class CandyMountain {

    private char[][] grid;
    protected int size;
    private final int goalRow;
    private final int goalCol;
    private final int liopRow;
    private final int liopCol;

    protected boolean hasSeenLiopleurodonBoo;

    protected Position goal;
    protected Position quester;
    protected Position liopPos;
    protected Position position;

    private Random rand = new Random();
    private Random rand2 = new Random();

    public static final Move NORTH = new Move(-1, 0);
    public static final Move EAST = new Move(0, 1);
    public static final Move SOUTH = new Move(1, 0);
    public static final Move WEST = new Move(0, -1);

    public CandyMountain() {
        this(8);
    }

    public CandyMountain(int size) {
        this.size = size;
        this.grid = new char[size][size];
        this.position = new Position(0, 0);
        this.goalRow = rand.nextInt(size / 2) + size / 2;
        this.goalCol = rand.nextInt(size / 2) + size / 2;
        this.goal = new Position(goalRow, goalCol);
        this.liopRow = rand2.nextInt(size / 2);
        this.liopCol = rand2.nextInt(size / 2) + size / 2;
        this.liopPos = new Position(liopRow, liopCol);
        this.quester = new Position(0, 0);
        this.hasSeenLiopleurodonBoo = false;
        for (int x = 0; x < this.grid.length; x++) {
            for (int y = 0; y < this.grid[x].length; y++) {
                this.grid[x][y] = ' ';
            }
        }
    }

    public boolean hasReachedGoal() {
        if (hasSeenLiopleurodon() && quester.equals(goal)) {
            return true;
        }
        return false;
    }

    public int moveQuester(Move move) {
        int newRow = move.getRowDelta() + quester.getRow();
        int newCol = move.getColDelta() + quester.getCol();

        if (newRow < size && newCol < size) {
            position = new Position(quester.getRow(), quester.getCol());
            quester = new Position(newRow, newCol);
            if (liopPos.equals(quester)) {
                System.out.println("Liopleurodon at: " + liopPos.toString());
                System.out.println("Goal at: " + goal.toString());
                hasSeenLiopleurodon();
            }
        } else {
            return -1;
        }
        return 1;
    }

    public Position getQuester() {
        return this.quester;
    }

    public Position getGoal() {
        return this.goal;
    }

    public Position getLiopPos() {
        return this.liopPos;
    }


    public String toString() {
        String finalStr = "";
        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                finalStr = finalStr + "+---";
            }
            finalStr = finalStr + "+\n";

            for (int j = 0; j < size; j++) {
                finalStr = finalStr + "| " + grid[i][j] + " ";
                if (i == position.getRow()
                    && j == position.getCol()) {
                    grid[i][j] = '.';
                }
                if (i == quester.getRow()
                    && j == quester.getCol()) {
                    grid[i][j] = 'Q';
                }
                if (i == liopPos.getRow()
                    && j == liopPos.getCol()
                    && hasSeenLiopleurodon()) {
                    grid[i][j] = 'L';
                } else if (i == goal.getRow()
                           && j == goal.getCol()
                           && hasSeenLiopleurodon()) {
                    grid[i][j] = 'G';
                }
                finalStr = finalStr.substring(0,
                        finalStr.length() - 3) + " " + grid[i][j] + " ";
            }
            finalStr = finalStr + "|\n";
        }
        for (int k = 0; k < size; k++) {
            finalStr = finalStr + "+---";
        }
        finalStr = finalStr + "+\n";
        return finalStr;
    }

    public boolean hasSeenLiopleurodon() {
        if (quester.equals(liopPos)) {
            hasSeenLiopleurodonBoo = true;

        }
        return hasSeenLiopleurodonBoo;
    }


}