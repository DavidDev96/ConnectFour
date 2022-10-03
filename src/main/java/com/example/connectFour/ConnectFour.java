package com.example.connectFour;

public class ConnectFour {

    // implement the logic of the
    // game. Keep the SOLID principles in mind in order to create well-structured and testable code

    private Board board;
    private String playerOneName;
    private String playerTwoName;

    private Player winner;
    private static int playersTurn = Player.PLAYER_ONE.ordinal();

    private boolean allDiscsPlayed() {
        for (int row = 0; row < Board.SIZE-1; row++) {
            for (int col = 0; col < Board.SIZE; col++) {
                if (board.getDiscColorOfPosition(row, col) == DiscColor.WHITE) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean playerWon(DiscColor color) {
        boolean horizontalWin = checkHorizontal(color);
        boolean verticalWin = checkVertical(color);
        boolean rightDiagonalWin = checkRightDiagonal(color);
        boolean leftDiagonalWin = checkLeftDiagonal(color);
        return horizontalWin || verticalWin || rightDiagonalWin || leftDiagonalWin;
    }

    private boolean checkHorizontal(DiscColor color) {
        int count = 0;
        for (int i = 0; i < Board.SIZE; i++) {
            count = 0;
            for (int j = 0; j < Board.SIZE; j++) {
                if (board.getDiscColorOfPosition(i,j) == color) {
                    count++;
                    if (count == 4) {
                        this.winner = color == DiscColor.RED ? Player.PLAYER_ONE : Player.PLAYER_TWO;
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
        }
        return false;
    }

    private boolean checkVertical(DiscColor color) {
        int count = 0;
        for (int j = 0; j < Board.SIZE; j++) {
            count = 0;
            for (int i = 0; i < Board.SIZE; i++) {
                if (board.getDiscColorOfPosition(i,j) == color) {
                    count++;
                    if (count == 4) {
                        this.winner = color == DiscColor.RED ? Player.PLAYER_ONE : Player.PLAYER_TWO;
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
        }
        return false;
    }

    private boolean checkRightDiagonal(DiscColor color) {
        int count;
        for (int i = 0; i < Board.SIZE; i++) {
            count = 0;
            int row = i;
            int col = 0;
            while (row >= 0 && col < Board.SIZE) {
                if (board.getDiscColorOfPosition(row, col) == color) {
                    count++;
                    if (count == 4) {
                        this.winner = color == DiscColor.RED ? Player.PLAYER_ONE : Player.PLAYER_TWO;
                        return true;
                    }
                } else {
                    count = 0;
                }
                row--;
                col++;
            }
        }
        for (int j = 0; j < Board.SIZE; j++) {
            count = 0;
            int row = Board.SIZE - 1;
            int col = j;
            while (row >= 0 && col < Board.SIZE) {
                if (board.getDiscColorOfPosition(row, col) == color) {
                    count++;
                    if (count == 4) {
                        this.winner = color == DiscColor.RED ? Player.PLAYER_ONE : Player.PLAYER_TWO;
                        return true;
                    }
                } else {
                    count = 0;
                }
                row--;
                col++;
            }
        }
        return false;
    }

    private boolean checkLeftDiagonal(DiscColor color) {
        int count;
        for (int i = 0; i < Board.SIZE; i++) {
            count = 0;
            int row = i;
            int col = Board.SIZE - 1;
            while (row >= 0 && col >= 0) {
                if (board.getDiscColorOfPosition(row, col) == color) {
                    count++;
                    if (count == 4) {
                        this.winner = color == DiscColor.RED ? Player.PLAYER_ONE : Player.PLAYER_TWO;
                        return true;
                    }
                } else {
                    count = 0;
                }
                row--;
                col--;
            }
        }
        for (int j = 0; j < Board.SIZE; j++) {
            count = 0;
            int row = Board.SIZE - 1;
            int col = j;
            while (row >= 0 && col >= 0) {
                if (board.getDiscColorOfPosition(row,col) == color) {
                    count++;
                    if (count == 4) {
                        this.winner = color == DiscColor.RED ? Player.PLAYER_ONE : Player.PLAYER_TWO;
                        return true;
                    }
                } else {
                    count = 0;
                }
                row--;
                col--;
            }
        }
        return false;
    }

    public ConnectFour(String player1, String player2) {
        this.board = new Board();
        this.playerOneName = player1;
        this.playerTwoName = player2;
    }

    public static Player getCurrentPlayer() {
        return playersTurn == Player.PLAYER_ONE.ordinal() ? Player.PLAYER_ONE : Player.PLAYER_TWO;
    }

    public String getCurrentPlayerName() {
        return playersTurn == Player.PLAYER_ONE.ordinal() ? getPlayerOneName() : getPlayerTwoName();
    }

    public String getPlayerOneName() {
        return playerOneName;
    }

    public void changeTurn() {
        if (playersTurn == Player.PLAYER_ONE.ordinal()) {
            playersTurn = Player.PLAYER_TWO.ordinal();
        } else {
            playersTurn = Player.PLAYER_ONE.ordinal();
        }
    }

    public int getPlayersTurn() {
        return playersTurn;
    }

    public String getPlayerTwoName() {
        return playerTwoName;
    }

    public String getWinner() {
        if (winner == Player.PLAYER_ONE) {
            return getPlayerOneName();
        } else if (winner == Player.PLAYER_TWO) {
            return getPlayerTwoName();
        } else {
            return null;
        }
    }

    public DiscColor getDiscColorOfPosition(int x, int y) {
        return board.getDiscColorOfPosition(x, y);
    }

    public GameState getCurrentGameState() {
        if (this.playerWon(getPlayersTurn() == 1 ? DiscColor.RED : DiscColor.YELLOW)) {
            return GameState.WIN;
        } else if (this.allDiscsPlayed()) {
            return GameState.TIE;
        } else {
            return GameState.PLAYING;
        }
    }

    public void resetValues() {
        this.board = new Board();
        this.winner = null;
        this.playersTurn = Player.PLAYER_ONE.ordinal();
    }

    public void dropDisc(int column) {
        board.drop(getCurrentPlayer(), column);
        this.changeTurn();
    }
}

