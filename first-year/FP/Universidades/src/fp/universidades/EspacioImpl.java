package fp.universidades;

import fp.utiles.Checkers;

public class EspacioImpl implements Espacio {
	private String nombre;
	private Integer planta;
	private TipoEspacio tipo;
	private Integer capacidad;

	public EspacioImpl(TipoEspacio tipo, String nombre, Integer capacidad, Integer planta) {
		Checkers.checkNoNull(tipo, nombre, capacidad, planta);
		this.nombre = nombre;
		this.planta = planta;
		this.tipo = tipo;
		this.capacidad = capacidad;
	}

	private static final String R_CAPACIDAD = "La capacidad debe ser mayor que cero";

	private static Boolean restriccionCapacidad(Integer capacidad) {
		return capacidad > 0;
	}

	public boolean equals(Object o) {
		boolean res = false;
		if (o instanceof Espacio) {
			Espacio es = (Espacio) o;
			{
				res = this.getPlanta().equals(es.getPlanta()) && this.getNombre().equals(es.getNombre());
			}
		}
		return res;
	}

	public int hashCode() {
		return this.getNombre().hashCode() + 31 * this.getPlanta().hashCode();
	}

	public int compareTo(Espacio es) {
		int res = this.getPlanta().compareTo(es.getPlanta());
		if (res == 0) {
			res = getNombre().compareTo(es.getNombre());
		}
		return res;
	}

	public String getNombre() {
		return nombre;
	}

	public Integer getPlanta() {
		return planta;
	}

	public TipoEspacio getTipo() {
		return tipo;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;

	}

	public void setTipo(TipoEspacio tipo) {
		this.tipo = tipo;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
		Checkers.checkNoNull(capacidad);
		Checkers.check(R_CAPACIDAD, restriccionCapacidad(capacidad));
	}

	public String toString() {
		return getNombre() + "(planta " + getPlanta() + ")";
	}
}
