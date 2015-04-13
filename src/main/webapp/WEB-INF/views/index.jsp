<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@page import="java.util.Date,java.text.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Accueil</title>
    <%@include file="metas.jsp"%>
</head>
<body>
<%@include file="header.jsp"%>
<div class="container contenu">
    <div class="row">
        <div class="col-lg-6">
            <h2> Nous sommes le <%= new SimpleDateFormat("EEEE d MMMM yyyy").format(new Date())%></h2>
            <div id="menu">
                <ul class="nav nav-pills nav-stacked">
                    <li><a href="afficherJouets.htm">Afficher les <strong>jouets</strong></a></li>
                    <li><a href="/afficherTrancheAge.htm">Afficher les <strong>tranches d'age</strong></a></li>
                    <li><a href="/afficherCatégories.htm">Afficher les <strong>catégories</strong></a></li>
                    <li><a href="/ajouterJouet.htm">Ajouter un <strong>jouet</strong></a></li>
                    <li><a href="/addCategorie.htm">Ajouter une <strong>catégorie</strong></a></li>
                    <li><a href="/addTranche.htm">Ajouter une <strong>tranche d'age</strong></a></li>
                    <li>
                        <form class="form-horizontal" action="/afficherCatalogues.htm" method="post" id="form1">
                            <fieldset>

                                <!-- Form Name -->
                                <legend>Afficher le Catalogue</legend>

                                <!-- Text input-->
                                <div class="control-group">
                                    <label class="control-label" for="anneeDebut">Année de départ</label>

                                    <div class="controls">
                                        <input id="anneeDebut" name="anneeDebut" type="number" placeholder="année de départ" min="2000" max="2100"
                                               class="form-control" value="2000" required="">
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="nbAnnees">Nom de la catégorie</label>

                                    <div class="controls">
                                        <input id="nbAnnees" name="nbAnnees" type="number" placeholder="nombre d'années" min=1 max=20 class="form-control"
                                               value="6" required="">
                                    </div>
                                </div>
                            </fieldset>
                            <br>
                            <button class="btn btn-info" type="submit" form="form1" value="Submit">Afficher le catalogue</button>
                        </form>
                       </li>
                </ul>
            </div>
        </div>
        <div class="col-lg-6">
            <img alt="Image d'un jouet en bois" src="resources/images/jeux-bois.jpg" class="img-responsive img-thumbnail"/>
        </div>
    </div>
</div>
<%@include file="script.jsp"%>
</body>
</html> 
