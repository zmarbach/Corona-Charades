<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <body>
      <div>
          <h1>Edit Team One Player Names</h1>
          <form:form action="/player-names-team-one" method="POST" modelAttribute="playerForm">

              <c:forEach items="${playerForm.players}" var="player" varStatus="status">
                  <form:input path="players[${status.index}].name" value="${player.name}" type="text"/>
              </c:forEach>

              <input type="submit" value="Save Player Names" />

          </form:form>
      </div>

      <div>
        <c:forEach items="${game.words}" var="word">
            <h3>Game Word List: </h3>
            <h6>${word}</h6>
            <br />
        </c:forEach>
      </div>

      <div>
          <c:forEach items="${game.teamOne.players}" var="player">
              <h3>Team One Player List: </h3>
              <h6>${player.name}</h6>
              <br />
          </c:forEach>
      </div>

      <div>
        <c:forEach items="${game.teamTwo.players}" var="player">
            <h3>Team Two Player List: </h3>
            <h6>${player.name}</h6>
            <br />
        </c:forEach>
      </div>
  </body>
</html>
