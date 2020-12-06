function getNotaTexto(numero) {
	var res = "<p class='suspenso'><strong>FALLA EL JAVASCRIPT</strong></p>";
	var nota = Number(numero);

	if (nota < 5) {
		res = "<p class='suspenso'><strong>Suspenso</strong></p>";
	} else if (5 <= nota && nota < 7) {
		res = "<p class='aprobado'><strong>Aprobado</strong></p>";
	} else if (7 <= nota && nota < 9) {
		res = "<p class='notable'><strong>Notable</strong></p>";
	} else if (9 <= nota && nota <= 10) {
		res = "<p class='sobresaliente'><strong>Sobresaliente</strong></p>";
	}

	return document.write(res);
}
