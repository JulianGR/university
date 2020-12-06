package tipos;

public class Ciudad {
	private String nombre;
	private Double poblacion;

	public static Ciudad create(String[] formato) {
		return new Ciudad(formato[0], Double.parseDouble(formato[1]));

	}

	public static Ciudad create() {
		return new Ciudad("", null);

	}

	private Ciudad(String nombre, Double poblacion) {
		super();
		this.nombre = nombre;
		this.poblacion = poblacion;
	}

	public String getNombre() {
		return nombre;
	}

	public Double getPoblacion() {
		return poblacion;
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
		Ciudad other = (Ciudad) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getNombre();
	}

}
