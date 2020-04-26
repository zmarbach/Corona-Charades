<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link href="https://unpkg.com/bootstrap@4.3.1/dist/css/bootstrap.min.css" rel="stylesheet" />
  <body>
      <div class="container">
      <div class="card">
        <div class="card-body">
            <form:form action="/teams" method="POST" modelAttribute="teamsViewForm" autocomplete="off">
                <div class="form-group">
                    <label for="numPlayersTeamOne">How many players on Team One?</label>
                    <input id="numPlayersTeamOne" class="form-control" type="text" name="numPlayersTeamOne" autofocus="autofocus" placeholder="0"/>
                </div>

                <div class="form-group">
                    <label for="numPlayersTeamTwo">How many players on Team Two?</label>
                    <input id="numPlayersTeamTwo" class="form-control" type="text" name="numPlayersTeamTwo" placeholder="0"/>
                </div>

                <div class="form-group">
                    <label for="numWordsPerPlayer">How many words per player?</label>
                    <input id="numWordsPerPlayer" class="form-control" type="text" name="numWordsPerPlayer" placeholder="0"/>
                </div>

                <input class="btn btn-outline-primary" type="submit" value="Submit" />
            </form:form>
        </div>
      </div>
      </div>
  </body>
</html>
