#include <stdio.h>
#include <stdlib.h>
#include "ejercicio77.h"

int main(void) {
	int v[] = { 0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };

	printf("El v[k] = k  es %d", conArrays(v, 12));
	printf("\nEl v[k] = k  es %d", conArraysRecursivo(v, 12));
}
int conArrays(int v[], int tam) {

	int i = 0;

	int j = tam;
	int k = (i + j) / 2;

	while (v[k] != k && i < j) {
		if (v[k] > k) {
			j = k - 1;
		} else {
			i = k + 1;
		}
		k = (i + j) / 2;
	}

	if (v[k] != k) {
		k = -1;
	}
	return k;
}

int conArraysRecursivo(int v[], int tam) {
	return conArraysRecursivo1(v, tam, 0, tam, tam / 2);
}

int conArraysRecursivo1(int v[], int tam, int i, int j, int k) {
	if (i >= j) {
		return -1;
	} else if (v[k] == k) {
		return k;
	} else if (v[k] > k) {
		return conArraysRecursivo1(v, tam, i, k - 1, (i + k - 1) / 2);
	} else {
		return conArraysRecursivo1(v, tam, k + 1, j, (k + 1 + j) / 2);
	}
}
