import com.example.connectFour.*;
import org.junit.jupiter.api.*;

import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


class GameTest {

    @Mock
    ConnectFour gameMock;
    private ConnectFour game;
    private Service service;
    private Board board;

    @BeforeEach
    void initGame() {
        game = new ConnectFour("David", "Stefan");
        board = new Board();
        game.resetValues();
    }

    @AfterEach
    void destroyBoard() {
        game = null;
        board = null;

    }

    @Test
    @DisplayName("Board.ctor creates a board with empty/white discs")
    void ctorCreatesBoardWithEmptyDiscs() {
        for(int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                assertThat(board.getDiscColorOfPosition(i,j) == DiscColor.WHITE);
            }
        }
        assertThat(board).isNotNull();
    }

    @Test
    @DisplayName("Game.getDiscColorOfPosition returns the disc color of given position")
    void getDiscColorOfPositionReturnsDiscColor() {
        assertThat(game.getDiscColorOfPosition(3,3)).isEqualTo(DiscColor.WHITE);
        game.dropDisc(0);
        assertThat(game.getDiscColorOfPosition(6,0)).isEqualTo(DiscColor.RED);
        game.dropDisc(3);
        assertThat(game.getDiscColorOfPosition(6,3)).isEqualTo(DiscColor.YELLOW);
        game.dropDisc(6);
        assertThat(game.getDiscColorOfPosition(6,6)).isEqualTo(DiscColor.RED);
        game.dropDisc(0);
        assertThat(game.getDiscColorOfPosition(5,0)).isEqualTo(DiscColor.YELLOW);
        assertThat(game.getDiscColorOfPosition(5,1)).isEqualTo(DiscColor.WHITE);
    }

    @Test
    @DisplayName("Board.drop throws IllegalArgumentException when passing invalid player")
    void dropThrowsIllegalArgumentExceptionWithInvalidPlayer() {
        assertThrows(IllegalArgumentException.class, () -> {
            board.drop(Player.NONE, 4);
        });
    }


    @Test
    @DisplayName("Board.drop drops discs of current player")
    void dropDiscWithBothPlayer() {
        board.drop(Player.PLAYER_ONE, 0);
        assertThat(board.getDiscColorOfPosition(6,0)).isEqualTo(DiscColor.RED);
        board.drop(Player.PLAYER_TWO, 0);
        assertThat(board.getDiscColorOfPosition(5,0)).isEqualTo(DiscColor.YELLOW);
    }

    @Test
    @DisplayName("Game.resetValues resets all values")
    void resetValues() {
        game.dropDisc(0);
        assertThat(game.getDiscColorOfPosition(6,0)).isEqualTo(DiscColor.RED);

        game.resetValues();

        assertThat(game.getWinner()).isEqualTo(null);
        assertThat(game.getPlayersTurn()).isEqualTo(Player.PLAYER_ONE.ordinal());

        assertThat(game.getDiscColorOfPosition(6,0)).isEqualTo(DiscColor.WHITE);

    }

    @Test
    @DisplayName("Game.getCurrentGameState returns WIN when all discs are placed")
    void getCurrentGameStateWhenAllDiscsArePlaced() {
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                game.dropDisc(i);
            }
        }
        assertThat(game.getCurrentGameState()).isEqualTo(GameState.WIN);
        assertThat(game.getWinner()).isEqualTo(game.getPlayerTwoName());
        assertThat(game.allDiscsPlayed()).isTrue();
    }

    @Test
    @DisplayName("Game.getCurrentGameState returns TIE when all discs are placed")
    void getStateTieWhenAllDiscsArePlacedWithNoWinner() {
        for (int i = 0; i < Board.SIZE; i++) {
            game.dropDisc(0);
        }

        for (int i = 0; i < Board.SIZE; i++) {
            game.dropDisc(6);
        }

        for (int i = 0; i < Board.SIZE; i++) {
            game.dropDisc(2);
        }

        for (int i = 0; i < Board.SIZE; i++) {
            game.dropDisc(4);
        }

        for (int i = 0; i < Board.SIZE; i++) {
            game.dropDisc(1);
            game.dropDisc(3);
            game.dropDisc(5);
        }

        assertThat(game.getCurrentGameState()).isEqualTo(GameState.TIE);
        assertThat(game.getWinner()).isEqualTo(null);
        assertThat(game.allDiscsPlayed()).isTrue();
    }

    @Test
    @DisplayName("Game.getCurrentGameState returns WIN when a player has 4 connected discs")
    void getCurrentGameStateWhenAPlayerHasWon() {
        assertThat(game.getCurrentGameState()).isEqualTo(GameState.PLAYING);
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                game.dropDisc(i);
            }
        }
        assertThat(game.getCurrentGameState()).isEqualTo(GameState.WIN);

        game.resetValues();

        game.dropDisc(0);
        game.dropDisc(1);
        game.dropDisc(0);
        game.dropDisc(1);
        game.dropDisc(0);
        game.dropDisc(1);
        game.dropDisc(0);
        game.dropDisc(1);
        assertThat(game.getCurrentGameState()).isEqualTo(GameState.WIN);
    }

    @Test
    @DisplayName("Game.getCurrentGameState returns WIN when a player has 4 connected discs")
    void getCurrentGameStateWhenAPlayerHasFourHorizontal() {
        game.dropDisc(0);
        game.dropDisc(6);
        game.dropDisc(1);
        game.dropDisc(1);
        game.dropDisc(2);
        game.dropDisc(1);
        game.dropDisc(3);
        game.dropDisc(1);
        game.dropDisc(4);
        game.dropDisc(4);
        assertThat(game.getCurrentGameState()).isEqualTo(GameState.WIN);
    }

    @Test
    @DisplayName("Game.getCurrentGameState returns WIN when a player has 4 diagonal discs")
    void getCurrentGameStateWhenAPlayerHasFourRightDiagonal() {

    game.dropDisc(0);
    game.dropDisc(1);
    game.dropDisc(1);
    game.dropDisc(2);
    game.dropDisc(2);
    game.dropDisc(1);
    game.dropDisc(2);
    game.dropDisc(3);
    game.dropDisc(3);
    game.dropDisc(3);
    game.dropDisc(6);
    game.dropDisc(0);
    game.dropDisc(3);
    game.dropDisc(1);
    assertThat(game.getCurrentGameState()).isEqualTo(GameState.WIN);
    }

    @Test
    @DisplayName("Game.getCurrentGameState returns WIN when a player has 4 diagonal discs")
    void getCurrentGameStateWhenAPlayerHasFourRightDownDiagonal() {

        game.dropDisc(0);
        game.dropDisc(0);
        game.dropDisc(0);
        game.dropDisc(6);

        game.dropDisc(0);
        game.dropDisc(2);

        game.dropDisc(3);

        game.dropDisc(1);
        game.dropDisc(1);
        game.dropDisc(6);
        game.dropDisc(1);

        game.dropDisc(6);
        game.dropDisc(2);

        game.dropDisc(5);

        assertThat(game.getCurrentGameState()).isEqualTo(GameState.WIN);
    }

    @Test
    @DisplayName("Game.getCurrentGameState returns WIN when player 2 has 4 diagonal discs")
    void getCurrentGameStateWhenPlayerTwoHasFourLeftDiagonal() {

        game.dropDisc(0);
        game.dropDisc(0);
        game.dropDisc(0);
        game.dropDisc(0);
        game.dropDisc(0);
        game.dropDisc(3);
        game.dropDisc(2);
        game.dropDisc(2);
        game.dropDisc(2);
        game.dropDisc(1);
        game.dropDisc(1);
        game.dropDisc(1);
        game.dropDisc(1);
        game.dropDisc(1);
        game.dropDisc(1);
        assertThat(game.getCurrentGameState()).isEqualTo(GameState.WIN);
    }

    @Test
    @DisplayName("Game.getCurrentGameState returns WIN when player 1 has 4 diagonal discs")
    void getCurrentGameStateWhenPlayerOneHasFourLeftDiagonal() {

        game.dropDisc(6);
        game.dropDisc(5);
        game.dropDisc(5);
        game.dropDisc(4);
        game.dropDisc(4);
        game.dropDisc(0);
        game.dropDisc(4);
        game.dropDisc(4);
        game.dropDisc(3);
        game.dropDisc(3);
        game.dropDisc(3);
        game.dropDisc(0);
        game.dropDisc(3);
        game.dropDisc(0);
        assertThat(game.getCurrentGameState()).isEqualTo(GameState.WIN);
        assertThat(game.getWinner()).isEqualTo(game.getPlayerOneName());
    }

    @Test
    @DisplayName("Game.getCurrentPlayer returns currentPlayer")
    void getCurrentPlayerReturnsCurrentPlayer() {
        assertThat(game.getCurrentPlayer()).isEqualTo(Player.PLAYER_ONE);
        game.dropDisc(1);
        assertThat(game.getCurrentPlayer()).isEqualTo(Player.PLAYER_TWO);
        game.dropDisc(2);
    }

    @Test
    @DisplayName("Game.getCurrentPlayerName returns name")
    void getCurrentPlayerNameReturnsCurrentPlayer() {
        assertThat(game.getCurrentPlayerName()).isEqualTo("David");
        game.dropDisc(1);
        assertThat(game.getCurrentPlayerName()).isEqualTo("Stefan");
        game.dropDisc(2);
    }

    @Test
    @DisplayName("Game.getPlayersTurn returns current player")
    void getPlayersTurnReturnsCurrentPlayer(){
        assertThat(game.getPlayersTurn()).isEqualTo(1);
        game.dropDisc(1);
        assertThat(game.getPlayersTurn()).isEqualTo(2);
    }

    @Test
    @DisplayName("Game.getCurrentPlayerOneName returns name")
    void getCurrentPlayerOneNameReturnsCurrentPlayer() {
        assertThat(game.getPlayerOneName()).isEqualTo("David");
        assertThat(game.getPlayerOneName()).isNotEqualTo("Stefan");
        assertThat(game.getPlayerTwoName()).isEqualTo("Stefan");
        assertThat(game.getPlayerTwoName()).isNotEqualTo("David");

    }


    @Test
    @DisplayName("Game.getCurrentGameState returns state playing when no disc was placed")
    void getCurrentGameStateWhenNoDiscsWerePlaced() {
        assertThat(game.getCurrentGameState()).isEqualTo(GameState.PLAYING);

        game.dropDisc(0);
        assertThat(game.getCurrentGameState()).isEqualTo(GameState.PLAYING);
    }
    @Test
    void getPlayersTurnWithStubService(){
        service = new ServiceStub();
        assertThat(service.getPlayersTurn()).isEqualTo(1);
        game.dropDisc(1);
        //assertThat(service.getPlayersTurn()).isEqualTo(2);
    }
}

interface Service {
    int getPlayersTurn();
}

class ServiceStub implements Service {
    private ConnectFour game = new ConnectFour("David", "Stefan");

    @Override
    public int getPlayersTurn() {
        return game.getPlayersTurn();
    }
}

