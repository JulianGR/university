/*
 ============================================================================
 Name        : Practica1.c
 Author      : 
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "library.h"

string invertirCadenaWhile(string cadena) {
	int size = strlen(cadena);
	int i = size - 1;
	char res[size];
	int j = 0;
	while (i >= 0) {
		res[j] = cadena[i];
		i--;
		j++;
	}
	res[size] = ' \0'
	return res;
}
