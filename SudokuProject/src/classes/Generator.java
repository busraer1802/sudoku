package classes;
import java.util.Random;

public class Generator {
	private static final int COLUMN = 0;
	private static final int ROW = 1;

	private int[][] board;
	private final Solver solver;

	public Generator() {
		board = new int[9][9];
		solver = new Solver();
	}

	private void generatePermutation(int selector) {
		int val1, val2;
	    int min = 0;
	    int max = 2;
	    Random r = new Random();

	    for (int i = 0; i < 3; i++) {
	    	val1 = r.nextInt(max - min + 1) + min;
	    	do {
	    		val2 = r.nextInt(max - min + 1) + min;
	    	} while (val2 == val1);
	    	max += 3;
	    	min += 3;
	    	if (selector == Generator.COLUMN) columnPermutation(val1, val2);
	    	else rowPermutation(val1, val2);
	    }
	}

	private void columnPermutation(int val1, int val2) {
		for (int i = 0; i < 9; i++) {
			int temp = board[i][val1];
			board[i][val1] = board[i][val2];
			board[i][val2] = temp;
		}
	}

	private void rowPermutation(int val1, int val2) {
		for (int i = 0; i < 9; i++) {
			int temp = board[val1][i];
			board[val1][i] = board[val2][i];
			board[val2][i] = temp;
		}
	}

	private void subSquareColPermutation(int val1, int val2) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				int temp = board[j][val1];
				board[j][val1] = board[j][val2];
				board[j][val2] = temp;
			}
			val1++;
			val2++;
		}
	}

	private void subSquareRowPermutation(int val1, int val2) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				int temp = board[val1][j];
				board[val1][j] = board[val2][j];
				board[val2][j] = temp;
			}
			val1++;
			val2++;
		}
	}

	private void transpose() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				int temp = board[i][j];
				board[i][j] = board[j][i];
				board[j][i] = temp;
			}
		}
	}

	private int clearCell(Cell cell) {
		int count = 9;
		for (int i = 1; i <= 9; i++) {
			if (!solver.isMoveValid(cell, i, board)) count--;
			if (count == 1) {
				board[cell.getRow()][cell.getColumn()] = 0;
				return 1;
			}
		}
		return 0;
	}

	public int[][] generate() {
		Random r = new Random();
	    int maxEmptyCells = 43;

	    for (int i = 0; i < 9; i++) {
	    	for (int j = 0; j < 9; j++) {
	    		board[i][j] = 0;
	    	}
	    }

	    int emptyCells = 0;
	    int val1, val2;

	    int[] subSquareIndex = {0, 3, 6};

	    board = solver.solve(board);

	    if (r.nextInt(2) == 0) transpose();
	    generatePermutation(Generator.ROW);
	    generatePermutation(Generator.COLUMN);

	    for (int i = 0; i < 2; i++) {
	    	val1 = subSquareIndex[r.nextInt(3)];
	    	do {
	    		val2 = subSquareIndex[r.nextInt(3)];
	    	} while (val1 == val2);

	    	if (i % 2 == 0) subSquareRowPermutation(val1, val2);
	    	else subSquareColPermutation(val1, val2);
	    }
	    while (emptyCells < maxEmptyCells) {
	    	emptyCells += clearCell(new Cell(r.nextInt(9), r.nextInt(9)));
	    }
	    return board;
	}

	public Solver getSolver() {
		return solver;
	}
}
