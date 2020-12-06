package ejercicio2;

//Creamos una clase de sorporte simple y tontorrona, un Plain Old Java Object (POJO)
public class Oportunidad {
	private Integer capital;
	private Integer beneficio;

	public static Oportunidad create(String s) {

		return new Oportunidad(s);
	}

	private Oportunidad(String s) {
		String[] t = s.split(",");
		capital = Integer.parseInt(t[0]);
		beneficio = Integer.parseInt(t[1]);

	}

	public Integer getCapital() {
		return capital;
	}

	public Integer getBeneficio() {
		return beneficio;
	}

}
