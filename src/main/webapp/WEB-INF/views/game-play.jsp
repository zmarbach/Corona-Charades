<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script type ="text/javascript">
         const TIME_LIMIT = 30;
         let timePassed = 0;
         let timeLeft = TIME_LIMIT;
         let timerInterval = null;

         function updateTimer(timeLeft) {
            document.getElementById("timeLeft").innerHTML = timeLeft;
         }

         function addClassToTimerContainer(className) {
             document.getElementById("timer-container").classList.add(className);
         }

         function removeClassFromTimerContainer(className) {
             document.getElementById("timer-container").classList.remove(className);
         }

         function startTimer() {
             timerInterval = setInterval(() => {
                   timePassed = timePassed += 1;
                   timeLeft = TIME_LIMIT - timePassed;

                   if (timeLeft <= 10) {
                     if (timeLeft <= 0) {
                       timeLeft = 0;
                       addClassToTimerContainer("blink");
                     }
                       addClassToTimerContainer("red");
                     updateTimer(timeLeft);
                   } else {
                     updateTimer(timeLeft);
                   }
             }, 1000);
         }

         function resetTimer() {
             clearInterval(timerInterval);
             timePassed = 0;
             timeLeft = TIME_LIMIT;
             removeClassFromTimerContainer("red");
             removeClassFromTimerContainer("blink");
             updateTimer(timeLeft);
         }


         $(document).ready(()=> {
            updateTimer(timeLeft);
         });
    </script>
</head>
<link rel="stylesheet" type="text/css" href="/resources/timer.css" />
<link href="https://unpkg.com/bootstrap@4.3.1/dist/css/bootstrap.min.css" rel="stylesheet" />
  <body>
      <div class="container mt-3">
        <div class="row">
              <div class="col-4">
                  <div class="card text-white text-center bg-dark m-3" style="height: 10rem; width: 12rem;">
                    <h5 class="card-header">Current Player</h5>
                    <div class="card-body">
                        <div class="card-text" style="font-size:25px;"> ${gamePlayViewForm.currentPlayer.name} </div>
                        <div class="card-text" style="font-size:15px;"> ${gamePlayViewForm.currentPlayer.team.name} </div>
                    </div>
                  </div>
              </div>
              <div class="col-4">
                  <div class="card text-white text-center bg-primary m-3" style="height: 10rem; width: 12rem;">
                      <h5 class="card-header">Team One</h5>
                      <div class="card-body">
                          <div class="card-text" style="font-size:50px;"> ${gamePlayViewForm.teamOneScore} </div>
                      </div>
                  </div>
              </div>
              <div class="col-4">
                  <div class="card text-white text-center bg-danger m-3" style="height: 10rem; width: 12rem;">
                    <h5 class="card-header">Team Two</h5>
                    <div class="card-body">
                        <div class="card-text" style="font-size:50px;"> ${gamePlayViewForm.teamTwoScore} </div>
                    </div>
                  </div>
              </div>
        </div>

        <form:form action="/start-turn" method="POST">
            <input type="hidden" name="gameUUID" value="${gamePlayViewForm.gameUUID}" />

            <c:if test="${gamePlayViewForm.newTurn}">
                <input id="startTurnButton" onclick="startTimer()" class="btn btn-lg btn-outline-primary m-3" type="submit" ${empty gamePlayViewForm.activeWords ? 'disabled="disabled"' : ''} value="Start turn" />
            </c:if>

        </form:form>

          <div class="card text-center border-dark m-3" style="max-width: 40rem;">
              <div class="card-body">
                  <c:if test="${!gamePlayViewForm.newTurn}">
                    <c:if test= "${not empty gamePlayViewForm.activeWords}">
                    <div style="font-size:72px; font-weight: bold;"> ${gamePlayViewForm.currentWord}</div>
                    </c:if>
                  </c:if>
              </div>
          </div>

          <c:if test="${empty gamePlayViewForm.activeWords}">
                <h6 style="color: red; font-style: italic;">All words have been guessed. Click next round to continue.</h6>
            </c:if>

          <div class="row">
              <div class="col-6">
                  <form action="/correct" method="post">
                  <input type="hidden" name="gameUUID" value="${gamePlayViewForm.gameUUID}" />
                        <c:if test="${!gamePlayViewForm.newTurn}">
                            <input class="btn btn-lg btn-success m-3" type="submit" ${empty gamePlayViewForm.activeWords ? 'disabled="disabled"' : ''} value="Correct!" />
                        </c:if>
                  </form>
              </div>
              <div class="col-6">
                  <form action="/skip" method="post">
                  <input type="hidden" name="gameUUID" value="${gamePlayViewForm.gameUUID}" />
                        <c:if test="${!gamePlayViewForm.newTurn}">
                            <input class="btn btn-lg btn-danger m-3" type="submit" ${empty gamePlayViewForm.activeWords ? 'disabled="disabled"' : ''} value="Skip" />
                        </c:if>
                  </form>
              </div>
          </div>

          <form action="/next-player" method="post">
            <input type="hidden" name="gameUUID" value="${gamePlayViewForm.gameUUID}" />
            <c:if test="${!gamePlayViewForm.newTurn}" >
              <input id="nextPlayerButton" onclick="resetTimer()" class="btn btn-lg btn-outline-secondary m-3" type="submit" ${empty gamePlayViewForm.activeWords ? 'disabled="disabled"' : ''} value="Next Player" />
            </c:if>
          </form>
          <form action="/next-round" method="post">
            <input type="hidden" name="gameUUID" value="${gamePlayViewForm.gameUUID}" />
            <c:if test="${empty gamePlayViewForm.activeWords}" >
                <input id="nextRoundButton" onclick="resetTimer()" class="btn btn-lg btn-outline-secondary m-3" type="submit" value="Next Round" />
            </c:if>
          </form>

          <form action="/end-game" method="post">
            <input type="hidden" name="gameUUID" value="${gamePlayViewForm.gameUUID}" />
            <input class="btn btn-lg btn-outline-danger m-3" type="submit" value="End Game" />
          </form>

          <div id="timer-container" class="timer-container">
                <span id="timeLeft">${timeLeft}</span>
                <span>secs</span>
          </div>
      </div>
  </body>
</html>
