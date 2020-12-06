#include <stdio.h>
#include <stdlib.h>
#include "p1.h"

long fRecNoFinSinMem(int n) {
	long res;
	int v[] = { 2, 1, 1 }; //con vectores para que no haya una linea por caso base

	if (n <= 2) {
		res = v[n];
	} else {
		res = 2 * fRecNoFinSinMem(n - 1) + 3 * fRecNoFinSinMem(n - 2)
				- fRecNoFinSinMem(n - 3);
	}
	return res;

}

long fWhile(int n) {
	long res;
	int i = 2, v[] = { 2, 1, 1 };

	if (n <= 2) {
		res = v[n];
	} else {
		while (i++ < n) {
			res = 2 * v[2] + 3 * v[1] - v[0];
			v[0] = v[1]; //v[0]= F(n-3)
			v[1] = v[2]; //v[1]= F(n-2)
			v[2] = res; //v[2]= F(n-1)
		}
	}
	return res;
}

long fRecFinal(int n) { //la FINAL lleva un parametro i que va creciendo hasta el valor que nos interesa y por otro lado un paramtroc que se va a cutalizando en cada llamada
	int v[] = { 2, 1, 1 };

	if (n <= 2) {
		return v[n];
	} else {
		return fRecFinal2(n, 3, v, 2 * v[2] + 3 * v[1] - v[0]);
	}
}
long fRecFinal2(int n, int i, int*v, long res) {

	if (i == n) {
		return res;
	} else {
		v[0] = v[1];
		//*v = (v + 1);
		v[1] = v[2];
		//*(v + 1) = *(v + 2);
		v[2] = res;
		//*v(v + 2) = res;
	}
	return fRecFinal2(n, i + 1, v, 2 * v[2] + 3 * v[1] - v[0]);
}

long fRecNoFinConMem(int n) {
	//vamos a dar una solucion con el tipo LongList de miguel toro
	long v[n];
	int size;
	v[0] = 2;
	v[1] = 1;
	v[2] = 1;
	if (n <= 2) {
		return v[n];

	} else {
		return fRecNoFinConMem2(n, v, &size);

	}
}

long fRecNoFinConMem2(int n, long*v, int*i) {
	if (*i == n - 1) {
		*(v+n) = 2 * v[i] + 3 * v[i - 1] - v[i - 2];
		*i = *i + 1;

		return v[n];

	} else {

		return 2 * fRecNoFinConMem2(n - 1, v) + 3 * fRecNoFinConMem2(n - 2, v)
				- fRecNoFinConMem2(n - 3, v);

	}

