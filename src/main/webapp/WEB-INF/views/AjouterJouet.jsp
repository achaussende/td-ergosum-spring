<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ajouter un jouet</title>
    <%@include file="metas.jsp" %>
</head>
<body>
<%@include file="header.jsp" %>
<div class="container contenu">
    <div class="row">
        <h1>Ajout du jouet</h1>

        <div class="col-lg-7">
            <form role="form" method="post" action="/sauverJouet.htm">
                <div class="form-group">
                    <label>Numéro</label>
                    <input type="number" class="form-control" placeholder="Entrez le numéro du jouet ici...">
                </div>
                <div class="form-group">
                    <label>Libellé</label>
                    <input type="text" class="form-control" placeholder="Insérer le libellé ici...">
                </div>
                <div class="form-group">
                    <label>Catégorie</label>
                    <select id="codecateg" class="form-control">
                        <option disabled selected>Sélectionner la catégorie</option>
                        <c:forEach items="${categories}" var="item">
                            <option value="${item.codecateg}">${item.libcateg}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label>Tranche d'âge</label>
                    <select id="codetranche" class="form-control">
                        <option disabled selected>Sélectionner la tranche d'âge</option>
                        <c:forEach items="${tranches}" var="item">
                            <option value="${item.codetranche}">${item.agemin} - ${item.agemax} ans</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label>Quantité de distribution</label>
                    <input id="quantiteDistribution" type="number" class="form-control">
                </div>
                <button type="submit" class="btn btn-default">Ajouter jouet</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>