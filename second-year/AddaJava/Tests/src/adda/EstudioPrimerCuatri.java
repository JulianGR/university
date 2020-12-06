package adda;

public class EstudioPrimerCuatri {
	public static void main(String[] args) {

		Integer acum = 1;

		System.out.println(fNoFin(7));
		System.out.println(fMedio(7, acum));
		System.out.println(fFin(7, acum));

	}

	public static Integer fNoFin(Integer n) {

		if (n == 0) {
			return 2;
		} else if (n <= 2) {
			return 1;

		} else {

			return 2 * fNoFin(n - 1) + 3 * fNoFin(n - 2) - fNoFin(n - 3);

		}

	}

	public static Integer fMedio(Integer n, Integer acum) {

		if (n == 0) {
			return acum + 1;
		} else if (n <= 2) {
			return acum;

		} else {

			return fMedio(n - 1, 2 * acum) + fMedio(n - 2, 3 * acum) + fMedio(n - 3, -1 * acum);

		}
	}

	public static Integer fFin(Integer n, Integer acum) {

		if (n == 0) {
			return acum + 1;
		} else if (n <= 2) {
			return acum;

		} else {

			return fFin(2 * acum + (n - 1) + 3 * acum + (n - 2) - acum * (n - 3), acum);

		}

	}

}