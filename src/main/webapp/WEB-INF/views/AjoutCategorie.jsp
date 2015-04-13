<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Ajouter une catégorie</title>
  <%@include file="metas.jsp" %>
</head>
<body>
<%@include file="header.jsp" %>
<div class="container contenu">
  <div class="row">
    <form class="form-horizontal" action="/addCategorie.htm" method="post" id="form1">
      <fieldset>

        <!-- Form Name -->
        <legend>Catégorie</legend>

        <!-- Text input-->
        <div class="control-group">
          <label class="control-label" for="codecateg">Numéro de la catégorie</label>
          <div class="controls">
            <input id="codecateg" name="codecateg" type="number" placeholder="numéro" min="0" max="99" class="form-control" value="${categorie.codecateg}" required="">
          </div>
        </div>
        <div class="control-group">
          <label class="control-label" for="libcateg">Nom de la catégorie</label>
          <div class="controls">
            <input id="libcateg" name="libcateg" type="text" placeholder="nom"  class="form-control" value="${categorie.libcateg}" required="">
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
