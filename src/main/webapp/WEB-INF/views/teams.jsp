<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>JavaSpringWebApp</title>
    <meta name="description" content="" />
  </head>
  <body>
      <div>
        <form:form action="/add-num-of-players" method="POST" modelAttribute="teamPlayerNumbers">
            <form:label path="numPlayersTeamOne">How many players on Team One?</form:label>
            <form:input type="text" path="numPlayersTeamOne"/>
            <br />

            <form:label path="numPlayersTeamTwo">How many players on Team Two?</form:label>
            <form:input type="text" path="numPlayersTeamTwo"/>
            <br />

            <input type="submit" value="Submit" />
        </form:form>
      </div>
  </body>
</html>
