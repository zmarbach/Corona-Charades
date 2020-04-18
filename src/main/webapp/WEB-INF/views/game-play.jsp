<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <body>
      <div>
          <table>
            <tr>
                <td>Placeholder for current player name</td>
                <td>Placeholder for current team name</td>
            </tr>
            <tr>
                <td>Placeholder for Team One score</td>
                <td>Placeholder for Team Two score</td>
            </tr>
          </table>

          <br />

          <button type="button"> Start turn </button>

          <br />

          <h1> CURRENT WORD </h1>

          <br />

          <button type="button"> Correct! </button>
          <button type="button"> Skip </button>

          <br />

          <button type="button"> Next Player </button>
          <button type="button"> Next Round </button>

          <br />

          <h2>${test}</h2>
      </div>
  </body>
</html>
