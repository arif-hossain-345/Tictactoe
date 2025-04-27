

import java.util.Scanner;


public class Board {
    public char[][] grid = new char[3][3];

    public Board() {
        setGrid();
   }


    public void setGrid() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = ' ';
            }
        }
    }

    public void display() {
        System.out.println();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(grid[row][col]);
                if (col < 2) System.out.print("  |  ");
            }
            System.out.println();
            if (row < 2) System.out.println("- -  -  -  - - -");
        }
    }
}