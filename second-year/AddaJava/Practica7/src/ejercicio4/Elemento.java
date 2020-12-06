package ejercicio4;

//Creamos una clase de sorporte simple y tontorrona, un Plain Old Java Object (POJO)
public class Elemento {
	private Integer tamanyo;

	public static Elemento create(String s) {

		return new Elemento(s);
	}

	private Elemento(String s) {
		String[] t = s.split(",");
		tamanyo = Integer.parseInt(t[0]);

	}

	public Integer getTamanyo() {
		return tamanyo;
	}

}
