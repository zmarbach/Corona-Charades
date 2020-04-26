<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="refresh" content="3">
</head>
<link href="https://unpkg.com/bootstrap@4.3.1/dist/css/bootstrap.min.css" rel="stylesheet" />
  <body>
      <div class="container">
        <div class="row">
              <div class="col-4">
                  <div class="card text-white text-center bg-dark m-3" style="max-width: 15rem;">
                    <h5 class="card-header">Current Player</h5>
                    <div class="card-body">
                        <h6 class="card-text"> ${gamePlayViewForm.currentPlayer.name} </h6>
                        <h6 class="card-text"> ${gamePlayViewForm.currentPlayer.team.name} </h6>
                    </div>
                  </div>
              </div>
              <div class="col-4">
                  <div class="card text-white text-center bg-primary m-3" style="max-width: 10rem;">
                      <h5 class="card-header">Team One</h5>
                      <div class="card-body">
                          <h2 class="card-text"> ${gamePlayViewForm.teamOneScore} </h2>
                      </div>
                  </div>
              </div>
              <div class="col-4">
                  <div class="card text-white text-center bg-danger m-3" style="max-width: 10rem;">
                    <h5 class="card-header">Team Two</h5>
                    <div class="card-body">
                        <h2 class="card-text"> ${gamePlayViewForm.teamTwoScore} </h2>
                    </div>
                  </div>
              </div>
        </div>

            <form action="/start-turn" method="post">
                <input class="btn btn-outline-primary" type="submit" ${empty gamePlayViewForm.activeWords ? 'disabled="disabled"' : ''} value="Start turn" />
            </form>

          <br />
          <div class="card text-center border-dark m-3" style="max-width: 40rem;">
              <div class="card-body">
                  <c:if test="${!gamePlayViewForm.newTurn}">
                    <c:if test= "${not empty gamePlayViewForm.activeWords}">
                    <h1>${gamePlayViewForm.currentWord}</h1>
                    </c:if>
                  </c:if>
              </div>
          </div>

          <c:if test="${empty gamePlayViewForm.activeWords}">
                <h3>All words have been guessed. Click next round to continue.</h3>
            </c:if>
          <br />

          <form action="/correct" method="post">
                <c:if test="${!gamePlayViewForm.newTurn}">
                    <input class="btn btn-success" type="submit" ${empty gamePlayViewForm.activeWords ? 'disabled="disabled"' : ''} value="Correct!" />
                </c:if>
          </form>
          <form action="/skip" method="post">
                <c:if test="${!gamePlayViewForm.newTurn}">
                    <input class="btn btn-danger" type="submit" ${empty gamePlayViewForm.activeWords ? 'disabled="disabled"' : ''} value="Skip" />
                </c:if>
          </form>

          <br />
          <br />
          <br />

          <form action="/next-player" method="post">
            <c:if test="${!gamePlayViewForm.newTurn}" >
              <input class="btn btn-outline-secondary" type="submit" ${empty gamePlayViewForm.activeWords ? 'disabled="disabled"' : ''} value="Next Player" />
            </c:if>
          </form>
          <form action="/next-round" method="post">
            <c:if test="${empty gamePlayViewForm.activeWords}" >
                <input class="btn btn-outline-secondary" type="submit" value="Next Round" />
            </c:if>
          </form>

          <br />
          <br />
          <br />

          <form action="/end-game" method="post">
                <input class="btn btn-outline-danger" type="submit" value="End Game" />
          </form>
      </div>
  </body>
</html>
