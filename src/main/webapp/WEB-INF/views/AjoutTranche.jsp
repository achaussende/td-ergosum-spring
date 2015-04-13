<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Ajouter une tranche d'age</title>
  <%@include file="metas.jsp" %>
</head>
<body>
<%@include file="header.jsp" %>
<div class="container contenu">
  <div class="row">
    <form class="form-horizontal" action="/addTranche.htm" method="post" id="form1">
      <fieldset>

        <!-- Form Name -->
        <legend>Tranche d'age</legend>

        <!-- Text input-->
        <div class="control-group">
          <label class="control-label" for="tranche">Nom tranche d'age</label>
          <div class="controls">
            <input id="tranche" name="tranche" type="number" placeholder="nom" class="form-control" value="${tranche.codetranche}" required="">
          </div>
        </div>
        <div class="control-group">
          <label class="control-label" for="agemin">Age minimum</label>
          <div class="controls">
            <input id="agemin" name="agemin" type="number" placeholder="age" min="0" max="99" class="form-control" value="${tranche.agemin}" required="">
          </div>
        </div>
        <div class="control-group">
          <label class="control-label" for="agemax">Age maximum</label>
          <div class="controls">
            <input id="agemax" name="agemax" type="number" placeholder="age" min="0" max="99" class="form-control" value="${tranche.agemax}" required="">
          </div>
        </div>
      </fieldset>
      <br>
      <button class="btn btn-info" type="submit" form="form1" value="Submit">Submit</button>
    </form>
  </div>
</div>
<%@include file="script.jsp" %>
</div>

</body>
</html>
