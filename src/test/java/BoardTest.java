import com.example.connectFour.*;
import org.junit.jupiter.api.*;

import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


class BoardTest {
    private ConnectFour game;
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

/*    @Test
    @DisplayName("Game.getCurrentGameState returns TIE when all discs are placed")
    void getCurrentGameStateWhenAllDiscsArePlaced() {
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                game.dropDisc(i);
            }
        }
        assertThat(game.getCurrentGameState()).isEqualTo(GameState.TIE);
    }*/

    @Test
    @DisplayName("Game.getCurrentGameState returns WIN when a player has 4 connected discs")
    void getCurrentGameStateWhenAPlayerHasWon() {
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
    @DisplayName("Game.getCurrentGameState returns state playing when no disc was placed")
    void getCurrentGameStateWhenNoDiscsWerePlaced() {
        assertThat(game.getCurrentGameState()).isEqualTo(GameState.PLAYING);

        game.dropDisc(0);
        assertThat(game.getCurrentGameState()).isEqualTo(GameState.PLAYING);
    }
}
