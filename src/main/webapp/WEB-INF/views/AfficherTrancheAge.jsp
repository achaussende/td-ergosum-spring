<%--
  Created by IntelliJ IDEA.
  User: Antoine
  Date: 04/04/2015
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tranches Age</title>
    <%@include file="metas.jsp" %>
</head>
<body>
<%@include file="header.jsp" %>
<div class="container contenu">
    <div class="row">
        <div class="col-lg-7">
            <h1>Liste des tranches d'age</h1>
            <a href="/addTranche.htm" class="btn btn-info" role="button">Ajouter une tranche d'age</a>
            <br>
            <div class="table-responsive ">
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Tranche</th>
                        <th>Modifier</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${mesTranchesAge}" var="item">
                        <tr>
                            <td>${item.codetranche}</td>
                            <td> De ${item.agemin} à ${item.agemax} ans</td>
                            <td><a href="/addTranche.htm?mtranche=${item.codetranche}" class="btn btn-info" role="button">Modifier la Tranche </a> </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="col-lg-5">
            <div id="chart"></div>
        </div>
    </div>
</div>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
    google.load('visualization', '1.0', {'packages':['corechart']});
    google.setOnLoadCallback(drawChart);
    function drawChart() {
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Tranche d\'Age');
        data.addColumn('number', 'Nombre de jouets');
        data.addRows([
            <c:forEach items="${mesTranchesAge}" var="item" varStatus="loop">
            ['De ${item.agemin} à ${item.agemax} ans',${item.nbJouets}] <c:if test="${!loop.last}">,</c:if>
            </c:forEach>
        ]);
        var options = {'title':'Nombre de jouets par tranche d\'age',
            'width':500,
            'height':400};
        var chart = new google.visualization.PieChart(document.getElementById('chart'));
        chart.draw(data, options);
    }
</script>
<%@include file="script.jsp" %>
</body>
</html>