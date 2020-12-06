#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "list.h"
#include "sorted_list.h"

int main(void) {

	list ls = { "Lorem", "ipsum", "dolor", "sit", "amet", "consectetur",
			"adipiscing", "elit", "Vestibulum", "eget", "porttitor", "ante",
			"Aliquam", "eleifend", "mi", "palabraMasLargaConMinusculasFinal" };

	printf(
			"+Min�sculas de \"Test\" con while: "
					+ cuentaCuantasMinusculasWhile("Test"));
	printf("+String con m�s min�sculas while: " + conMasMinusculasWhile(ls));
	printf(
			"+String con m�s min�sculas recursivo: "
					+ conMasMinusculasRecursivo(ls));

	return 0;
}

/*
 * ENUNCIADO: PI1.1 (57). Dada una lista de String buscar la cadena que tiene un
 * mayor n�mero de caracteres en min�scula
 */

int cuentaCuantasMinusculasWhile(char s[]) {
	int res = 0;
	int i = 0;

	while (strlen(s) > i) {

		if (islower(s[i])) {
			res++;
		}
		i++;

	}

	return res;
}

char* conMasMinusculasWhile(list lista) {
	int i = 0;
	char* res = "No hay palabras con min�sculas";
	int contadorMinusculas = cuentaCuantasMinusculasWhile(lista[i]);

	// hasta que se llene, el �ndice avanza, si la palabra del �ndice tiene m�s
	// min�sculas que la del acumulador, entonces se la queda

	while (size(lista) > i) {

		if (cuentaCuantasMinusculasWhile(lista[i]) >= contadorMinusculas) {
			res = lista[i];
			contadorMinusculas = cuentaCuantasMinusculasWhile(lista[i]);

		}
		i++;
	}

	return res;
}

int cuentaCuantasMinusculasRecursivo1(char* s) {
	return cuentaCuantasMinusculasRecursivo(s, 0, 0);
}

int cuentaCuantasMinusculasRecursivo(char* s, int res, int i) {

	if (strlen(s) > i) {
		if (islower(s[i])) {
			return cuentaCuantasMinusculasRecursivo(s, res + 1, i + 1);
		} else {

			return cuentaCuantasMinusculasRecursivo(s, res, i + 1);
		}

	}
	return res;
}

char* conMasMinusculasRecursivo1(list ls) {
	return conMasMinusculasRecursivo(ls, 0, "No hay palabras con min�sculas", 0);
}

//generalizaci�n
char* conMasMinusculasRecursivo(list ls, int i, char* res,
		int contadorMinusculas) {
//caso base: si estamos al principio, cojo la palabra directamente
	if (i == 0) {
		return conMasMinusculasRecursivo(ls, i + 1, ls[i],
				cuentaCuantasMinusculasWhile(ls[i]));

//si estamos avanzando, tengo que ver si la palabra del �ndice es m�s grande que la del acumulador
	} else if (i > 0 && size(ls) > i) {
		if (cuentaCuantasMinusculasWhile(ls[i]) > contadorMinusculas) {
			return conMasMinusculasRecursivo(ls, i + 1, ls[i]), cuentaCuantasMinusculasWhile(
					ls[i]);
		} else {
			return conMasMinusculasRecursivo(ls, i + 1, res, contadorMinusculas);
		}
	}

	return res;
}

