<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>JavaSpringWebApp</title>
    <meta name="description" content="" />
  </head>
  <body>
      <div>
          <h1>Team One Players</h1>
          <c:forEach items="${game.teamOne.players}" var"player">
            <h3>${player.name}</h3>
          <c:forEach>

          <h1>Team Two Players</h1>
          <c:forEach items="${game.teamTwo.players}" var"player">
            <h3>${player.name}</h3>
          <c:forEach>
      </div>
  </body>
</html>
