<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <body>
      <div>
          <table>
            <tr>
                <td>Current player: ${gamePlayViewForm.currentPlayer.name}</td>
                <td>Current team: ${gamePlayViewForm.currentPlayer.team.name}</td>
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

          <c:if test="${!gamePlayViewForm.isBeginningOfNewTurn}" >
            <h1>${gamePlayViewForm.currentWord}</h1>
          </c:if>

          <c:if test="${empty gamePlayViewForm.activeWords}">
                <h3>All words have been guessed. Click next round to continue.</h3>
            </c:if>
          <br />

          <form action="/correct" method="post">
                <input type="submit" ${empty gamePlayViewForm.activeWords ? 'disabled="disabled"' : ''} value="Correct!" />
          </form>
          <form action="/skip" method="post">
                <input type="submit" ${empty gamePlayViewForm.activeWords ? 'disabled="disabled"' : ''} value="Skip" />
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