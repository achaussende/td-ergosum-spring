<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
    <h5>Bonjour, j'affiche les stagees</h5>

    <div class="table-responsive">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Numero</th>
                <th>Libellé</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${mesJouets}" var="item">
                <tr>
                    <td>${item.numero}</td>
                    <td>${item.libelle}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
