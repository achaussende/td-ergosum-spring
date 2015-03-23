<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<header>
    <nav class="navbar navbar-inverse navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.jsp">TP1 Informatique répartie</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-inverse navbar-nav">
                    <li><a href="index.jsp">Accueil</a></li>
                    <li><a href="Controleur?action=saisieStage">Saisie d'un stage</a></li>
                    <li><a href="Controleur?action=afficheStage">Affichage liste des stages</a></li>
                    <li><a href="Controleur?action=rechercheStage">Recherche d'un stage</a></li>
                    <li><a href="Controleur?action=modifierStage">Modifier un stage</a></li>
                </ul>
            </div>
        </div>
    </nav>
</header>