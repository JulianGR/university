package fp.ejercicio45;

import java.util.Scanner;

public class Ejercicio45 {
	public static void main(String[] args) {
		ModeloConsola modelo;
		Double precioBase = 99.99;
		Scanner sc = new Scanner(System.in);
		System.out.println("Modelo de la consola deseada: ");
		modelo = ModeloConsola.valueOf(sc.next());
		switch (modelo) {
		case XBOXONE:
			precioBase = precioBase + 200;
			break;
		case PS4:
			precioBase = precioBase + 300;
		case WIIU:
			precioBase = precioBase + 100;
		default:
			precioBase = precioBase + 0.0;
			break;
		}
		System.out.println("Precio de la consola: " + precioBase);
		sc.close();
	}
}
