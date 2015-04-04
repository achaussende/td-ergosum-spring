<%--
  Created by IntelliJ IDEA.
  User: Antoine
  Date: 04/04/2015
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Aucun jouets trouvé</title>
    <%@include file="metas.jsp" %>
</head>
<body>
<%@include file="header.jsp" %>
<div class="container contenu">
    <div class="row">
        <div class="alert alert-warning">
            <strong>Dommage!</strong> Aucun jouet contenant  "${search}" n'a été trouvé dans notre base de donnée.
        </div>
    </div>
</div>