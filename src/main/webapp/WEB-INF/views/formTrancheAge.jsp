<form action="ajouterTrancheAge.htm" method="post" id="trancheAge" >
  <fieldset>

    <!-- Form Name -->
    <legend>Editer Tranche Age</legend>

    <!-- Text input-->
    <div class="control-group">
      <label class="control-label" for="ID">ID</label>
      <div class="controls">
        <input id="ID" name="ID" type="text" placeholder="placeholder" class=" form-control" required="">

      </div>
    </div>

    <!-- Spinbox -->
    <div class="control-group">
      <label class="control-label" for="agemin">Age minimum</label>
      <div class="controls">
        <div class="spinbox" data-initialize="spinbox">
          <input type="text" class="form-control input-mini spinbox-input" value="1" id="agemin">
          <div class="spinbox-buttons btn-group btn-group-vertical">
            <button class="btn btn-default spinbox-up btn-xs">
              <span class="glyphicon glyphicon-chevron-up"></span><span class="sr-only">Increase</span>
            </button>
            <button class="btn btn-default spinbox-down btn-xs">
              <span class="glyphicon glyphicon-chevron-down"></span><span class="sr-only">Decrease</span>
            </button>
          </div>

        </div>
      </div>
    </div>
    <!-- Spinbox -->
    <div class="control-group">
      <label class="control-label" for="agemax">Age Maximum</label>
      <div class="controls">
        <div class="spinbox" data-initialize="spinbox">
          <input type="text" class="form-control input-mini spinbox-input" value="1" id="agemax">
          <div class="spinbox-buttons btn-group btn-group-vertical">
            <button class="btn btn-default spinbox-up btn-xs">
              <span class="glyphicon glyphicon-chevron-up"></span><span class="sr-only">Increase</span>
            </button>
            <button class="btn btn-default spinbox-down btn-xs">
              <span class="glyphicon glyphicon-chevron-down"></span><span class="sr-only">Decrease</span>
            </button>
          </div>

        </div>
      </div>
    </div>
  </fieldset>
  <button type="submit" class="btn btn-success">Valider</button>
</form>
