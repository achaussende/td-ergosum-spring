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
                    <input name="id" type="number" class="form-control" placeholder="Entrez le numéro du jouet ici...">
                </div>
                <div class="form-group">
                    <label>Libellé</label>
                    <input name="libelle" type="text" class="form-control" placeholder="Insérer le libellé ici...">
                </div>
                <div class="form-group">
                    <label>Catégorie</label>
                    <select name="codecateg" class="form-control">
                        <option disabled selected>Sélectionner la catégorie</option>
                        <c:forEach items="${categories}" var="item">
                            <option value="${item.codecateg}">${item.libcateg}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label>Tranche d'âge</label>
                    <select name="codetranche" class="form-control">
                        <option disabled selected>Sélectionner la tranche d'âge</option>
                        <c:forEach items="${tranches}" var="item">
                            <option value="${item.codetranche}">${item.agemin} - ${item.agemax} ans</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label>Quantité de distribution</label>
                    <input name="quantiteDistribution" type="number" class="form-control">
                </div>
                <div class="form-group">
                    <label>Année du catalogue</label>
                    <select name="codecatalogue" class="form-control">
                        <option disabled selected>Sélectionner l'année</option>
                        <c:forEach items="${catalogues}" var="item">
                            <option value="${item.annee}">${item.annee}</option>
                        </c:forEach>
                    </select>
                </div>
                <button type="submit" class="btn btn-default">Ajouter jouet</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>
