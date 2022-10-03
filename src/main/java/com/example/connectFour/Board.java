package com.example.connectFour;

public class Board {
    static final int SIZE = 7;
    private int[][] board = new int[SIZE][SIZE];


    public Board() {
        for (int[] innerArray: board) {
            // second for...each loop access each element inside the row
            for(int data: innerArray) {
                innerArray[data] = 0;       // 0 for empty cell, 1 for yellow (player 1) disc, 2 for red (player 2) disc
            }
        }
    }

    public int getDiscOfPosition(int x, int y) {
        return board[x][y];
    }


    public void drop(CurrentPlayer currentPlayer, int column) {
        if (currentPlayer == CurrentPlayer.PLAYER_ONE) {
            // drop red disc
        } else if (currentPlayer == CurrentPlayer.PLAYER_TWO){
            // drop yellow disc
        } else {
            throw new IllegalArgumentException("Player not set");
        }
    }
}
