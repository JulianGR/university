package ejercicio4;

public class Desplazamiento {

	private Monumento source, target;
	private Double tiempo;

	public Monumento getSource() {
		return source;
	}

	public Monumento getTarget() {
		return target;
	}

	public Double getTiempo() {
		return tiempo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((target == null) ? 0 : target.hashCode());
		result = prime * result + ((tiempo == null) ? 0 : tiempo.hashCode());
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
		Desplazamiento other = (Desplazamiento) obj;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		if (target == null) {
			if (other.target != null)
				return false;
		} else if (!target.equals(other.target))
			return false;
		if (tiempo == null) {
			if (other.tiempo != null)
				return false;
		} else if (!tiempo.equals(other.tiempo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Desplazamiento de " + getSource() + "a " + getTarget() + "con tiempo " + getTiempo();
	}

	public Desplazamiento(Monumento source, Monumento target, Double tiempo) {
		super();
		this.source = source;
		this.target = target;
		this.tiempo = tiempo;
	}

	public static Desplazamiento create(Monumento m1, Monumento m2, String[] formato) {

		return new Desplazamiento(m1, m2, Double.parseDouble(formato[2]));

	}

	public static Desplazamiento create() {
		return new Desplazamiento(null, null, null);
	}
}
