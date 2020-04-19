<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <body>
      <div>
          <table>
            <tr>
                <td>Current player: ${gamePlayViewForm.currentPlayer.name}</td>
                <td>Current team: ${gamePlayViewForm.currentTeam.name}</td>
            </tr>
            <tr>
                <td>Team One score: ${gamePlayViewForm.teamOneScore}</td>
                <td>Team Two score: ${gamePlayViewForm.teamTwoScore}</td>
            </tr>
          </table>

          <br />

            <form action="/start-turn" method="post">
                <input type="submit" value="Start turn" />
            </form>

          <br />

<%-- Add if statement here to show red text somewhere on page to indicate no more words --%>

          <h1>${gamePlayViewForm.currentWord}</h1>

          <br />

          <form action="/correct" method="post">
                <input type="submit" value="Correct!" />
          </form>
          <form action="/skip" method="post">
                <input type="submit" value="Skip" />
          </form>

          <br />
          <br />
          <br />

          <form action="/next-player" method="post">
                <input type="submit" value="Next Player" />
          </form>
          <form action="/next-round" method="post">
                <input type="submit" value="Next Round" />
          </form>

          <br />
          <br />
          <br />

          <form action="/end-game" method="post">
                <input type="submit" value="End Game" />
          </form>

      </div>
  </body>
</html>
