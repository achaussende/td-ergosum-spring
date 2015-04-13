<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Liste des Jouets</title>
    <%@include file="metas.jsp" %>
</head>
<body>
<%@include file="header.jsp" %>
<div class="container contenu">
    <div class="row">
        <h1>Liste des jouets</h1>
        <div class="table-responsive">
            <table class="table table-bordered table-striped">
                <thead>
                <tr>
                    <th>Numero</th>
                    <th>Libellé</th>
                    <th>Age minimum</th>
                    <th>Age maximum</th>
                    <th>Catégorie</th>
                    <th>Supprimer</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${mesJouets}" var="item">
                    <tr>
                        <td>${item.numero}</td>
                        <td>${item.libelle}</td>
                        <td>${item.trancheage.agemin}</td>
                        <td>${item.trancheage.agemax}</td>
                        <td>${item.categorie.libcateg}</td>
                        <td><a href="/deleteJouet.htm?DJouet=${item.numero}" class="btn btn-danger" role="button">Supprimer Jouet </a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<%@include file="script.jsp" %>
</body>
</html>
