package com.example.connectFour;

public class ConnectFour {

    // implement the logic of the
    // game. Keep the SOLID principles in mind in order to create well-structured and testable code

    private Board board;
    private String playerOneName;
    private String playerTwoName;
    private static int playersTurn = CurrentPlayer.PLAYER_ONE.ordinal();


    public ConnectFour(String player1, String player2) {
        this.board = new Board();
        this.playerOneName = player1;
        this.playerTwoName = player2;
    }

    public static CurrentPlayer getCurrentPlayer() {
        return playersTurn == CurrentPlayer.PLAYER_ONE.ordinal() ? CurrentPlayer.PLAYER_ONE : CurrentPlayer.PLAYER_TWO;
    }

    public String getPlayerOneName() {
        return playerOneName;
    }

    public void changeTurn() {
        if (playersTurn == CurrentPlayer.PLAYER_ONE.ordinal()) {
            playersTurn = CurrentPlayer.PLAYER_TWO.ordinal();
        } else {
            playersTurn = CurrentPlayer.PLAYER_ONE.ordinal();
        }
    }

    public int getPlayersTurn() {
        return playersTurn + 1;
    }

    public String getPlayerTwoName() {
        return playerTwoName;
    }

    public int getDiscOfPosition(int x, int y) {
        return board.getDiscOfPosition(x, y);
    }

    public void initializeGame() {



        System.out.println("============ INITIALIZING GAME ============");


        System.out.println("It is " + (ConnectFour.getCurrentPlayer().ordinal() == 0 ? playerOneName : playerTwoName) + "`s turn");

        // create GUI n shit
    }
}

