package com.example.connectFour;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(urlPatterns = "/GameServlet")
public class GameServlet extends HttpServlet {
    private ConnectFour game;
    private GameState currentState = GameState.PLAYING;

    public void init() throws ServletException {
        System.out.println("ConnectFourServlet initialized()");
    }

    @Override
    public void destroy() {
        System.out.println("ConnectFourServlet destroyed()");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        try (PrintWriter out = response.getWriter()) {

            if (request.getParameter("start") != null) {

                response.setContentType("text/html");

                String player1 = (request.getParameter("player1").toString());
                String player2 = (request.getParameter("player2").toString());

                game = new ConnectFour(player1, player2);

                printGame(response);
            } else if (request.getParameter("reset") != null) {
                    this.currentState = GameState.PLAYING;
                    game.resetValues();
                    printGame(response);
            } else if (request.getParameter("drop") != null) {
                try {
                    int column = Integer.parseInt(request.getParameter("drop"));
                    game.dropDisc(column);
                    this.currentState = game.getCurrentGameState();
                    printGame(response);
                } catch (Exception e)
                {
                    System.out.println(e);
                }
            } else {
                throw new IllegalArgumentException();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void printGame(HttpServletResponse response) throws ServletException, IOException {

        try (PrintWriter out = response.getWriter()) {

            out.println("<html>");

            out.println("<body>");

            out.println("<div style='text-align: center;'>");

            out.println("<h1>==== Connect Four ====</h1>");
            out.printf("<p style='font-size: larger;'>Player 1 (%s) is playing <span style='color: red;'>red</span> and Player 2 (%s) is playing <span style='color: yellow;'>yellow</span>.</p>", game.getPlayerOneName(), game.getPlayerTwoName());

            out.printf("<p>It is " + game.getCurrentPlayerName() + " 's turn!");

            out.println("<div style='display: inline-flex;'>");
            if (this.currentState == GameState.WIN) {
                    out.println("<h1>The winner is " + game.getWinner() + "!</h1>");

            } else if (this.currentState == GameState.TIE) {
                    out.println("<h1>All discs are placed - the game ends as a tie!</h1>");
            } else {
                // Buttons for putting disc into grid
                for (int i = 0; i < Board.SIZE; i++) {
                    out.println("<form action='GameServlet' method='POST'>");
                    // TODO: Disabled=isFullRow()
                    out.printf("<button type='submit' name='drop' value='" + i + "'  style='margin: 5px;'>DROP</button>");
                    out.println("</form>");
                }
            }


            out.println("</div>");

            out.printf("</br>");

            // Printing the grid with its disc colors
            for (int i = 0; i < Board.SIZE; i++) {
                for (int j = 0; j < Board.SIZE; j++) {
                    switch (game.getDiscColorOfPosition(i, j)) {
                        case WHITE:
                            out.printf("<button disabled='true' style='font-size: 36px; margin: 10px;'>O</button>");
                            break;
                        case RED:
                            out.printf("<button disabled='true' style='font-size: 36px; color:red; margin: 10px;'>O</button>");
                            break;
                        case YELLOW:
                            out.printf("<button disabled='true' style='font-size: 36px; color:yellow; margin: 10px;'>O</button>");
                            break;
                    }
                }
                out.printf("</br>");
            }

            // Reset Button
            out.println("<form action='GameServlet' method='POST'>");
            out.printf("<button type='submit' name='reset' style='margin: 5px;'>Play again</button>");
            out.println("</form>");

            out.println("</div>");

            out.println("</body>");

            out.println("</html>");
        }
    }
}

