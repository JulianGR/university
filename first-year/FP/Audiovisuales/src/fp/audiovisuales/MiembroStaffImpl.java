package fp.audiovisuales;

import java.time.LocalDate;
import java.time.Period;

import fp.utiles.Checkers;

public class MiembroStaffImpl implements MiembroStaff {

	private Integer id;
	private String nombre;
	private LocalDate nacimiento;
	private LocalDate defuncion;
	private String lugar;
	private String alias;

	private static final String R_IDNOMBRE = "Ni el ID ni el nombre pueden tener valores null";
	private static final String R_FECHA = "La fecha de defunción debe ser igual o posterior a la de nacimiento";

	private static Boolean restriccionFecha(LocalDate nacimiento, LocalDate defuncion) {
		return defuncion.isAfter(nacimiento) || defuncion.isEqual(nacimiento);
	}

	private static Boolean restriccionIdNombre(Integer id, String nombre) {
		return (id != null) && (nombre != null);
	}

	public boolean equals(Object o) {
		boolean res = false;
		if (o instanceof MiembroStaff) {
			MiembroStaff m = (MiembroStaff) o;
			res = getId().equals(m.getId());
		}
		return res;
	}

	public int hashCode() {
		return getId().hashCode();
	}

	public Integer compareTo(MiembroStaff m) {
		Integer res = getId().compareTo(m.getId());
		return res;
	}

	public MiembroStaffImpl(Integer id, String nombre, LocalDate nacimiento, LocalDate defuncion, String lugar,
			String alias) {
		Checkers.checkNoNull(id, nombre);
		Checkers.check(R_IDNOMBRE, restriccionIdNombre(id, nombre));
		Checkers.check(R_FECHA, restriccionFecha(nacimiento, defuncion));
		this.id = id;
		this.nombre = nombre;
		this.nacimiento = nacimiento;
		this.defuncion = defuncion;
		this.alias = alias;
	}

	public MiembroStaffImpl(Integer id, String nombre, LocalDate nacimiento, String lugar, String alias) {
		this(id, nombre, nacimiento, null, lugar, alias);
		Checkers.checkNoNull(id, nombre);
		Checkers.check(R_IDNOMBRE, restriccionIdNombre(id, nombre));
		Checkers.check(R_FECHA, restriccionFecha(nacimiento, defuncion));
	}

	public MiembroStaffImpl(Integer id, String nombre) {
		this(id, nombre, null, null, null, null);
		Checkers.checkNoNull(id, nombre);
		Checkers.check(R_IDNOMBRE, restriccionIdNombre(id, nombre));
	}

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		Checkers.check(R_IDNOMBRE, restriccionIdNombre(id, nombre));
		this.nombre = nombre;
	}

	public LocalDate getFechaNacimiento() {
		return nacimiento;
	}

	public void setFechaNacimiento(LocalDate nacimiento) {
		Checkers.check(R_FECHA, restriccionFecha(nacimiento, defuncion));
		this.nacimiento = nacimiento;
	}

	public LocalDate getFechaDefuncion() {
		return defuncion;
	}

	public void setFechaDefuncion(LocalDate defuncion) {
		Checkers.check(R_FECHA, restriccionFecha(nacimiento, defuncion));
		this.defuncion = defuncion;
	}

	public Integer getEdad() {
		Integer edad = null;
		if (LocalDate.now().isAfter(defuncion)) {
			edad = Period.between(defuncion, nacimiento).getYears();
		} else {
			edad = Period.between(LocalDate.now(), nacimiento).getYears();
		}
		return edad;
	}

	public String getLugarNacimiento() {
		return lugar;
	}

	public void setLugarNacimiento(String lugar) {
		this.lugar = lugar;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String toString() {
		return nombre + "(" + alias + ")" + " - " + id;
	}

}
