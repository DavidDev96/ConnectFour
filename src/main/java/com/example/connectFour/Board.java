package com.example.connectFour;

public class Board {
    public static final int SIZE = 7;
    private int[][] board = new int[SIZE][SIZE];


    public Board() {
        for (int[] innerArray: board) {
            // second for...each loop access each element inside the row
            for(int data: innerArray) {
                innerArray[data] = 0;
            }
        }
    }

    public DiscColor getDiscColorOfPosition(int x, int y) {
        switch (board[x][y]) {
            case 1:
                return DiscColor.RED;
            case 2:
                return DiscColor.YELLOW;
            default:
                return DiscColor.WHITE;
        }
    }


    public void drop(Player player, int column) {
        if (player == Player.PLAYER_ONE) {
            // drop red disc
            for (int i = board.length - 1; i >= 0; i--)
                for (int j = board[i].length - 1; j >= 0; j--) {
                    if (j == column && board[i][j] == 0) {
                        board[i][j] = DiscColor.RED.ordinal();
                        return;
                    }
                }
        } else if (player == Player.PLAYER_TWO){
            // drop yellow disc
            for (int i = board.length - 1; i >= 0; i--)
                for (int j = board[i].length - 1; j >= 0; j--) {
                    if (j == column && board[i][j] == 0) {
                        board[i][j] = DiscColor.YELLOW.ordinal();
                        return;
                    }
                }
        } else {
            throw new IllegalArgumentException("Player not set");
        }
    }
}
