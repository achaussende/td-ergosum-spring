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
