package tipos;

public class Carretera {
	// private static int numObjetos = 0;
	private Ciudad source, target;
	private String nombre;
	private Double kms;
	// private int id;

	public static Carretera create(Ciudad v1, Ciudad v2, String[] formato) {

		return new Carretera(v1, v2, formato[2], Double.parseDouble(formato[3]));

	}

	public static Carretera create() {
		return new Carretera(null, null, "", null);
	}

	private Carretera(Ciudad source, Ciudad target, String nombre, Double kms) {
		super();
		this.source = source;
		this.target = target;
		this.nombre = nombre;
		this.kms = kms;
		// id = ++numObjetos;
	}

	public Ciudad getSource() {
		return source;
	}

	public Ciudad getTarget() {
		return target;
	}

	public String getNombre() {
		return nombre;
	}

	public Double getKms() {
		return kms;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((kms == null) ? 0 : kms.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	// Equals solo con el nombre. Ahora mismo está con nombre y con kms
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carretera other = (Carretera) obj;
		if (kms == null) {
			if (other.kms != null)
				return false;
		} else if (!kms.equals(other.kms))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getNombre() + "(" + getKms() + ")";
	}

}
