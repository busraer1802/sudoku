package classes;

public class Solver {
    
    public boolean isCellValid(Cell cell, int[][] board) {
        return board[cell.getRow()][cell.getColumn()] == 0;
    }

    public boolean isComplete(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) return false;
            }
        }
        return true;
    }

    public boolean isMoveValid(Cell cell, int move, int[][] board) {
        int r = cell.getRow();
        int c = cell.getColumn();

        if (r < 0 || c < 0 || move < 0 || r > 9 || c > 9 || move > 9) return false;

        for (int i = 0; i < 9; i++) {
            if (board[i][c] == move) return false;
        }

        for (int i = 0; i < 9; i++) {
            if (board[r][i] == move) return false;
        }

        for (int i = (r / 3) * 3; i < (r / 3) * 3 + 3; i++) {
            for (int j = (c / 3) * 3; j < (c / 3) * 3 + 3; j++) {
                if (board[i][j] == move) return false;
            }
        }

        return isCellValid(cell, board);
    }

    public Cell getNextEmptyCell(int[][] board) {
    	for (int i = 0; i < 9; i++) {
    		for (int j = 0; j < 9; j++) {
    			if (board[i][j] == 0) return new Cell(i, j);
    		}
    	}
    	return null;
    }

    public boolean isSolveable(int[][] board) {
    	Cell nextEmptyCell = getNextEmptyCell(board);

    	if (nextEmptyCell != null) {
    		for (int i = 1; i < 10; i++) {
    			if (isMoveValid(nextEmptyCell, i, board)) {
    				board[nextEmptyCell.getRow()][nextEmptyCell.getColumn()] = i;
    				if (isSolveable(board)) {
    					return true;
    				}
    				board[nextEmptyCell.getRow()][nextEmptyCell.getColumn()] = 0;
    			}
    		}
    	} else return true;
    	return false;
    }

    public int[][] solve(int[][] board) {
    	int[][] temp = new int[9][9];
    	temp[0][0] = -1;
    	if (isSolveable(board)) return board;
    	else return temp;
    }
}
