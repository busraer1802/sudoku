package classes;

public class Cell {
	private final int row, column;

	public Cell(int r, int c) {
		this.row = r;
		this.column = c;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}
}
