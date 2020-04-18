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

          <button type="button"> Start turn </button>

          <br />

          <h1> CURRENT WORD ${gamePlayViewForm.currentWord}</h1>

          <br />

          <button type="button"> Correct! </button>
          <button type="button"> Skip </button>

          <br />

          <button type="button"> Next Player </button>
          <button type="button"> Next Round </button>
      </div>
  </body>
</html>
