package com.example.connectFour;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(urlPatterns = "/GameServlet")
public class GameServlet extends HttpServlet {
    private ConnectFour game;

    public void init() throws ServletException {
        System.out.println("HelloServlet initialized()");
    }

    @Override
    public void destroy() {
        System.out.println("HelloServlet destroyed()");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        try (PrintWriter out = response.getWriter()) {


            if (request.getParameter("start") != null) {

                response.setContentType("text/html");

                String player1 = (request.getParameter("player1").toString());
                String player2 = (request.getParameter("player2").toString());

                game = new ConnectFour(player1, player2);
                game.initializeGame();

                printGame(response);

            } else if (request.getParameter("drop") != null) {
                try {
                    int disc = Integer.parseInt(request.getParameter("drop"));
                    printGame(response);
                    game.changeTurn();
                } catch (Exception e)
                {
                    System.out.println(e);
                }
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

            out.printf("<p>It is " + (game.getCurrentPlayer().ordinal() == 0 ? game.getPlayerOneName() : game.getPlayerTwoName()) + " 's turn!");

            // Buttons for putting disc into grid
            out.println("<div style='display: inline-flex;'>");
            for (int i = 0; i < Board.SIZE; i++) {
                out.println("<form action='GameServlet' method='POST'>");
                // TODO: Disabled=isFullRow()
                out.printf("<button type='submit' name='drop' value='" + game.getPlayersTurn() + "' style='margin: 5px;'>DROP</button>");
                out.println("</form>");
            }
            out.println("</div>");

            out.printf("</br>");

            // Printing the grid with its disc colors
            for (int i = 0; i < Board.SIZE; i++) {
                for (int j = 0; j < Board.SIZE; j++) {

                    switch (game.getDiscOfPosition(i, j)) {

                        // no disc set
                        case 0:
                            out.printf("<button disabled='true' style='font-size: 36px; margin: 10px;'>O</button>");
                            break;
                        // disc of player one (red)
                        case 1:
                            out.printf("<button disabled='true' style='font-size: 36px; color:red; margin: 10px;'>O</button>");
                            break;
                        // disc of player two (yellow)
                        case 2:
                            out.printf("<button disabled='true' style='font-size: 36px; color:yellow; margin: 10px;'>O</button>");
                            break;
                    }
                }
                out.printf("</br>");
            }

            out.println("</div>");

            out.println("</body>");

            out.println("</html>");
        }
    }
}
