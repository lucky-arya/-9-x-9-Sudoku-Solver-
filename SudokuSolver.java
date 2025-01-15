import java.util.Scanner;

public class SudokuSolver {

    static int count = 0;
    static int countLimit = 10;

    public static void SolveSudoku(int[][] Sudoku, int row, int col) {
        if (count >= countLimit) {
            return;
        }
        // If we reach the end of the Sudoku grid, print the solution
        if (row == Sudoku.length) {
            printSudoku(Sudoku);
            count++;
            return;
        }

        // Move to the next row if we reach the end of the column
        int nextRow = row, nextCol = col + 1;
        if (nextCol == Sudoku[0].length) {
            nextRow = row + 1;
            nextCol = 0;
        }

        // If the current cell is already filled, move to the next cell
        if (Sudoku[row][col] != 0) {
            SolveSudoku(Sudoku, nextRow, nextCol);
        } else {
            // Try all possible digits from 1 to 9
            for (int digit = 1; digit <= 9; digit++) {
                if (isSafe(Sudoku, row, col, digit)) {
                    Sudoku[row][col] = digit; // Place the digit
                    SolveSudoku(Sudoku, nextRow, nextCol); // Recurse
                    Sudoku[row][col] = 0; // Backtrack
                }
            }
        }
    }

    public static boolean isSafe(int[][] Sudoku, int row, int col, int digit) {
        // Row check
        for (int i = 0; i < Sudoku[0].length; i++) {
            if (Sudoku[row][i] == digit) {
                return false;
            }
        }

        // Column check
        for (int i = 0; i < Sudoku.length; i++) {
            if (Sudoku[i][col] == digit) {
                return false;
            }
        }

        // 3x3 grid check
        int sr = (row / 3) * 3;
        int sc = (col / 3) * 3;
        for (int i = sr; i < sr + 3; i++) {
            for (int j = sc; j < sc + 3; j++) {
                if (Sudoku[i][j] == digit) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void printSudoku(int[][] Sudoku) {
        System.out.println("--------Solution is : ---------");
        for (int i = 0; i < Sudoku.length; i++) {
            for (int j = 0; j < Sudoku[0].length; j++) {
                System.out.print(Sudoku[i][j] + " ");
            }
            System.out.println();
        }
    }
     public static int[][] inputSudoku() {
        Scanner scn = new Scanner(System.in);
        int[][] Sudoku = new int[9][9];

        System.out.println("Enter the Sudoku grid row by row (use 0 for empty cells):");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Sudoku[i][j] = scn.nextInt();
            }
        }
        scn.close();
        return Sudoku;
    }

    public static void main(String[] args) {
        
        int[][] Sudoku =inputSudoku();
        System.out.println("Given Input is :");
        SolveSudoku(Sudoku, 0, 0);

        if (count != 0) {
            System.out.println("Total number of solutions is: " + count);
        } else if (count == 10) {
            System.out.println("Solution exist More Than 10 but we are Showing only 10 for Optimisation.");
        } else {
            System.out.println("Solution doesn't exist.");
        }

    }
}
