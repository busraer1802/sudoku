package classes;
import javax.swing.*;
import java.awt.*;

public class Sudoku {

    private static class Colors {
        private static final Color BLACK = new Color(0);
        private static final Color RED = new Color(170, 10, 10);
        private static final Color GREEN = new Color(10, 170, 10);
    }

    private final Generator generator;
    private final Solver solver;
    private int[][] board;

    public Sudoku() {
        generator = new Generator();
        solver = generator.getSolver();
        board = new int[9][9];
    }

    public void startGame(JTextField[][] cells) {
        board = generator.generate();

        while (board[0][0] == -1) {
            board = generator.generate();
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != 0) {
                    cells[i][j].setForeground(Sudoku.Colors.BLACK);
                    fillCell(cells, i, j);
                } else {
                    cells[i][j].setForeground(Sudoku.Colors.RED);
                    cells[i][j].setText("");
                    cells[i][j].setEditable(true);
                }
            }
        }
    }

    public void finishGame(JTextField[][] cells) {
        board = solver.solve(board);
        if (isSolved(cells)) {
            printSolution(cells);
            JOptionPane.showMessageDialog(null, "You Won.");
        }
        else {
            printSolution(cells);
            JOptionPane.showMessageDialog(null, "You Lost.");
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cells[i][j].setText("");
                cells[i][j].setEditable(false);
            }
        }
    }

    public void giveUp(JTextField[][] cells) {
        board = solver.solve(board);
        printSolution(cells);
    }

    private boolean isSolved(JTextField[][] cells) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (isCellEmpty(cells[i][j])) {
                    return false;
                } else if (!isCellCorrect(cells, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void printSolution(JTextField[][] cells) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (isCellCorrect(cells, i, j)) cells[i][j].setForeground(Sudoku.Colors.GREEN);
                else cells[i][j].setForeground(Sudoku.Colors.RED);

                if (!cells[i][j].isEditable()) cells[i][j].setForeground(Sudoku.Colors.BLACK);                
                cells[i][j].setEditable(false);          
                fillCell(cells, i, j);
            }
        }
    }

    private void fillCell(JTextField[][] cells, int i, int j) {
        cells[i][j].setText(Integer.toString(board[i][j]));
    }

    private boolean isCellCorrect(JTextField[][] cells, int i, int j) {
        return cells[i][j].getText().equals(Integer.toString(board[i][j]));
    }

    private boolean isCellEmpty(JTextField cell) {
        return cell.getText().equals("");
    }
}
