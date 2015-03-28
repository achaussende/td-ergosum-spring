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
            <a class="navbar-brand" href="/">Gestion ErgoSum</a>
        </div>

        <div class="col-xs-4 pull-right">
            <form class="navbar-form" role="form" method="post" action="/findJouets.htm">
                <div class="search input-group" role="search" id="mySearch">
                    <input id="mySearchValue" type="search" class="form-control" name="search"
                           placeholder="Rechercher un Jouet"/>
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