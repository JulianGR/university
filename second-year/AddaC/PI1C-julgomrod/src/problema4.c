#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "list.h"
#include "sorted_list.h"

int main(void) {
	list ls1 = list_empty;
	list ls2 = list_empty;
	int i = 0;
	int j = 0;

	while (i < 10) {
		list_add(ls1, i);
		i += 2;
	}

	while (j < 10) {
		list_add(ls2, j);
		j++;
	}

	printf("Fusion de listas con while\n" + fusionListasWhile(ls1, ls2) + "\n");
	printf(
			"Fusion de listas con recursividad\n"
					+ fusionListasRecursivo(ls1, ls2) + "\n");
	return 0;
}
/*
 * ENUNCIADO: PI1.4 (64). Dadas dos listas ordenadas obtener otra
 * cadena{entiendo que se refiere a otra lista} ordenada que contenga los
 * elementos de las dos anteriores (fusión de listas)
 */

list fusionListasWhile(list ls1, list ls2) {
	list res = list_empty;

	int i = 0;
	int j = 0;

	while (size(ls1) > i || size(ls2) > j) {

		// si la i ya ha llegado al final pero la j no, todos los de la j para dentro
	if (size(ls1) == i && size(ls2) > j) {
		list_add(res, ls2[j]);
		j++;

		// si la j ha llegado pero la i no, todos los de la i para dentro
	} else if (size(ls1) > i && size(ls2) == j) {
		list_add(res, ls1[i]);
		i++;

		// si han llegado los 2, fuera
	} else if (size(ls1) == i && size(ls2) == j) {
		return res;
	}

	// si los 2 están llegando
	else if (ls1[i]<=ls2[j])) {
		list_add(res, ls1[i]);
		i++;
	} else {
		list_add(res, ls2[j]);
		j++;
	}

}
return res;
}

list fusionListasRecursivo1(list ls1, list ls2) {

return fusionListasRecursivo(ls1, ls2, 0, 0, list_empty);
}

list fusionListasRecursivo(list ls1, list ls2, int i, int j, list res) {

if (size(ls1) > i || size(ls2) > j) {

	if (size(ls1) == i && size(ls2) > j) {
		list_add(res, ls2[j]);
		return fusionListasRecursivo(ls1, ls2, i, j + 1, res);

		// si la j ha llegado pero la i no, todos los de la i para dentro
	} else if (size(ls1) > i && size(ls2) == j) {
		list_add(res, ls1[i]);
		return fusionListasRecursivo(ls1, ls2, i + 1, j, res);

		// si han llegado los 2, fuera
	} else if (size(ls1) == i && size(ls2) == j) {
		return res;
	}

	// si los 2 están llegando
	else if (ls1[i] <= ls2[j]) {
		list_add(res, ls1[i]);
		return fusionListasRecursivo(ls1, ls2, i + 1, j, res);
	} else {
		list_add(res, ls2[j]);
		return fusionListasRecursivo(ls1, ls2, i, j + 1, res);
	}
}

return res;

}
