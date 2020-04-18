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

          <h1>${gamePlayViewForm.currentWord}</h1>

          <br />

          <button type="button"> Correct! </button>
          <button type="button"> Skip </button>

          <br />
          <br />
          <br />

          <button type="button"> Next Player </button>
          <button type="button"> Next Round </button>

          <br />
          <br />
          <br />

          <button type="button"> End Game </button>
      </div>
  </body>
</html>
