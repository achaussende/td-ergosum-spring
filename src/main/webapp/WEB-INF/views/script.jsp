<script src="resources/bootstrap-3.3.4-dist/js/bootstrap.min.js"></script>
<script src="resources/fuelux/js/fuelux.min.js"></script>
<script type="text/javascript">
  $(document).ready(function () {
    $('#mySearch').search();

    $('#mySearch').on('searched.fu.search', function () {
      console.log($('#mySearchValue').val());
      $('form').submit();
    });
  });
</script>
