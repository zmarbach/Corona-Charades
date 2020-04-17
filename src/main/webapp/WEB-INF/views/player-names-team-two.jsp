<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <body>
      <div>
          <h1>Edit Team Two Player Names</h1>
          <form:form action="/player-names-team-two" method="POST" modelAttribute="playerForm">

              <c:forEach items="${playerForm.players}" var="player" varStatus="status">
                  <input name="players[${status.index}].name" value="${player.name}" />
              </c:forEach>

              <input type="submit" value="Save Player Names!" />

          </form:form>
      </div>
  </body>
</html>