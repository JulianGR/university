package fp.ejercicio48;

import java.util.Scanner;

public class Ejercicio48 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Peso (en kg): ");
		Double peso = sc.nextDouble();
		System.out.println("Altura (en metros, con coma para decimales): ");
		Double altura = sc.nextDouble();
		Double imc = peso / (altura * altura);
		System.out.println("IMC: " + imc);
		EstadoSalud estado = obtenerEstadoSalud(imc);
		System.out.println("Estado de salud: " + estado);
		sc.close();

	}

	private static EstadoSalud obtenerEstadoSalud(Double imc) {

		EstadoSalud res = null;
		if (imc > 0.0 && imc < 18.5) {
			res = EstadoSalud.DELGADEZ;
		} else if (imc >= 18.5 && imc < 25) {
			res = EstadoSalud.NORMAL;
		} else if (imc >= 25 && imc < 30) {
			res = EstadoSalud.PREOBESIDAD;
		} else if (imc >= 30 && imc < 99.00) {
			res = EstadoSalud.OBESIDAD;
		}
		return res;
	}
}
