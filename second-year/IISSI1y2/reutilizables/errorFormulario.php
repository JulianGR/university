<?php
//en caso de errores de validacion en PHP
if (isset($errores) && count($errores) > 0) {

?>

<div class="card border-danger mb-3">
	<div class="card-header">
		Hay errores en el formulario:
	</div>
	<div class="card-body text-danger">

		<p class="card-text">
			<ul class='list-group list-group-flush'>

				<?php
				foreach ($errores as $error) {
					echo "<li class='list-group-item'>$error</li>";
				}
				?>
			</ul>
		</p>
	</div>
</div>
<?php } ?>