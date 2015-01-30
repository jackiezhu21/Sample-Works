import java.util.Random;

public class TrappedCandyMountain extends CandyMountain {

    private int numTraps;
    private char[][] traps;
    private Random rand = new Random();
    private char[][] grid;

    public TrappedCandyMountain(int numTraps) {
       super();
        this.numTraps = numTraps;
        this.traps = new char[super.size][super.size];
        this.grid = new char[super.size][super.size];
        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[i].length; j++) {
                this.grid[i][j] = ' ';
            }
        }
        int x = 0;
        while (x < numTraps) {
            int i = rand.nextInt(super.size - 1) + 1;
            int j = rand.nextInt(super.size - 1) + 1;
            Position trapPosition = new Position(i, j);
            if (!trapPosition.equals(super.getQuester())
                && !trapPosition.equals(super.getGoal())
                && !trapPosition.equals(super.getLiopPos())
                && !trapPosition.equals(new Position(0, 0))) {
                traps[i][j] = '#';
                x++;
            }



        }


    }

    public int moveQuester(Move move) {
        int newRow = move.getRowDelta() + super.getQuester().getRow();
        int newCol = move.getColDelta() + super.getQuester().getCol();
        if (newRow < size && newCol < size) {
            if (traps[newRow][newCol] == '#') {
                return 999;
            }
            position = new Position(quester.getRow(), quester.getCol());
            quester = new Position(newRow, newCol);
            if (liopPos.equals(quester)) {
                System.out.println("Liopleurodon at: " + liopPos.toString());
                System.out.println("Goal at: " + goal.toString());
                super.hasSeenLiopleurodon();
            }
            return 1;
        } else {
            return -1;
        }
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
                if (traps[i][j] ==  '#' && hasSeenLiopleurodon()) {
                    grid[i][j] = '#';
                }
                if (i == position.getRow()
                    && j == position.getCol()) {
                    grid[i][j] = '.';
                }
                if (i == super.getQuester().getRow()
                    && j == super.getQuester().getCol()) {
                    grid[i][j] = 'Q';
                }
                if (i == super.getLiopPos().getRow()
                    && j == super.getLiopPos().getCol()
                    && hasSeenLiopleurodon()) {
                    grid[i][j] = 'L';
                } else if (i == super.getGoal().getRow()
                    && j == super.getGoal().getCol()
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


}