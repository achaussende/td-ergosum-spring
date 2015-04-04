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
        <h1>Informations sur le catalogue</h1>
        <div class="col-lg-7">
            <h2>Détails des distributions</h2>
            <div class="table-responsive ">
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>Année</th>
                        <th>Quantité Distribuée</th>
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
        <div class="col-lg-3">
            <div id="catalogue_chart"></div>
        </div>
        <div class="col-lg-7">
            <h2>Détails des affectations</h2>
            <div class="table-responsive ">
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>Année</th>
                        <th>Quantité Affectée</th>
                        <th>Catégorie</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${catégorieQuantité}" var="item">
                        <tr>
                            <td>${item.id}</td>
                            <td>${item.quantiteDistribuee}</td>
                            <td>${item.quantite}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
    google.load('visualization', '1.0', {'packages':['corechart']});
    google.setOnLoadCallback(drawChart);
    function drawChart() {
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Année');
        data.addColumn('number', 'Ventes');
        data.addRows([
            <c:forEach items="${mesCataloguesQuantites}" var="item" varStatus="loop">
                ['${item.annee}',${item.quantiteDistribuee}] <c:if test="${!loop.last}">,</c:if>
            </c:forEach>
        ]);
        var options = {'title':'Evolution des distributions du catalogue par année',
            'width':500,
            'height':400};
        var chart = new google.visualization.BarChart(document.getElementById('catalogue_chart'));
        chart.draw(data, options);
    }
</script>
<%@include file="script.jsp"%>
</body>
</html>
