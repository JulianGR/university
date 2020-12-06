package ejercicio1;

import java.util.List;
import java.util.stream.Collectors;

public class Barrio {
	private String nombre;
	private List<Barrio> adyacencias;

	public Barrio(String nombre, List<Barrio> adyacencias) {
		super();
		this.nombre = nombre;
		this.adyacencias = adyacencias;
	}

	@Override
	public String toString() {
		return getNombre();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Barrio other = (Barrio) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	public String getNombre() {
		return nombre;
	}

	public List<Barrio> getAdyacencias() {
		return adyacencias;
	}

	public static Barrio create(String[] formato) {
		return new Barrio(formato[0], null);
	}

	public static Barrio create(String nombre) {
		return new Barrio(nombre, null);
	}

	public static Barrio create() {
		return new Barrio("", null);
	}

	// create clave para que funcione
	public static Barrio create(String nombre, List<String> ls) {
		return new Barrio(nombre, ls.stream().map(s -> Barrio.create(s)).collect(Collectors.toList()));
	}
}
