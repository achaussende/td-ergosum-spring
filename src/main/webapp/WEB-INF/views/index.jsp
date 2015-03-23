<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@page import="java.util.Date,java.text.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Accueil</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="//www.fuelcdn.com/fuelux/3.5.0/css/fuelux.min.css">
    <link rel="stylesheet" href="css/style.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.jsp">Gestion ErgoSum</a>
        </div>

        <div class="col-xs-4 pull-right">
            <form class="navbar-form" role="form" method="post" action="Controleur?action=rechercheStage">
                <div class="search input-group" role="search" id="mySearch">
                    <input id="mySearchValue" type="search" class="form-control" name="search"
                           placeholder="Rechercher un Stage"/>
                              <span class="input-group-btn">
                                <button class="btn btn-default" type="button">
                                    <span class="glyphicon glyphicon-search"></span>
                                    <span class="sr-only">Rechercher un jouet</span>
                                </button>
                              </span>
                </div>
            </form>
        </div>
        <!--/.nav-collapse -->
    </div>
</nav>
<div class="container contenu">
    <div class="row">
        <div class="col-lg-12">
            <h2> Nous sommes le <%= new SimpleDateFormat("EEEE d MMMM yyyy").format(new Date())%>
            </h2>
        </div>
        <div id="menu">
            <ul class="nav nav-pills nav-stacked">
                <li><a href="afficherJouets.htm">Afficher les jouets</a></li>
            </ul>
        </div>
    </div>
</div>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="//www.fuelcdn.com/fuelux/3.5.0/js/fuelux.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#mySearch').search();

        $('#mySearch').on('searched.fu.search', function () {
            console.log($('#mySearchValue').val());
            $('form').submit();
        });
    });
</script>
</body>
</html> 
