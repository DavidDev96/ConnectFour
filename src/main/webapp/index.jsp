<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Connect Four</title>
    <link href="css/styles.css" rel="stylesheet">
</head>
    <body>
        <h1 style="text-align: center">Welcome to the Connect-Four Browser Game!</h1>
        <br/>
        <div style="text-align: center">
            <form action="GameServlet" method="POST">
                <p>
                    <label>Player Name 1:
                    <input type="text" name="player1" required>
                    </label>
                </p>
                <p>
                    <label>Player Name 2:
                    <input type="text" name="player2" required>
                    </label>
                </p>
                <p>
                    <input type="submit" name="start" value="Start the game"/>
                </p>
            </form>
        </div>

    </body>
</html>