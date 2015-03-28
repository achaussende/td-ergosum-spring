<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Afficher catalogues</title>
    <%@include file="metas.jsp"%>
</head>
<body>
<%@include file="header.jsp"%>
<div class="container contenu">
    <div class="row">
        <div class="table-responsive">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Année</th>
                    <th>Quantité</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${mesCataloguesQuantites}" var="item">
                    <tr>
                        <td>${item.annee}</td>
                        <td>${item.quantiteDistribuee}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%@include file="script.jsp"%>
</body>
</html>
