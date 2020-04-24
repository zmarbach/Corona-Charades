<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link href="https://unpkg.com/bootstrap@4.3.1/dist/css/bootstrap.min.css" rel="stylesheet" />
  <body>
      <div class="container">
      <div class="card">
        <div class="card-body">
            <form:form action="/teams" method="POST" modelAttribute="teamPlayerNumbers">
                <div class="form-group">
                    <form:label for="numPlayersTeamOne" path="numPlayersTeamOne">How many players on Team One?</form:label>
                    <form:input id="numPlayersTeamOne" class="form-control" type="text" path="numPlayersTeamOne"/>
                </div>

                <div class="form-group">
                    <form:label for="numPlayersTeamTwo" path="numPlayersTeamTwo">How many players on Team Two?</form:label>
                    <form:input id="numPlayersTeamTwo" class="form-control" type="text" path="numPlayersTeamTwo"/>
                </div>

                <input class="btn btn-outline-primary" type="submit" value="Submit" />
            </form:form>
        </div>
      </div>
      </div>
  </body>
</html>
