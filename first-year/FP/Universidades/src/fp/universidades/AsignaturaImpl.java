package fp.universidades;

import fp.utiles.Checkers;

public class AsignaturaImpl implements Asignatura {
	private String nombre;
	private String codigo;
	private Double creditos;
	private TipoAsignatura tipo;
	private Integer curso;

	public AsignaturaImpl(String nombre, String codigo, Double creditos, TipoAsignatura tipo, Integer curso) {
		Checkers.checkNoNull(nombre, codigo, creditos, tipo, curso);
		Checkers.check(R_CREDITOS, restriccionCreditos(creditos));
		Checkers.check(R_CURSO, restriccionCurso(curso));
		Checkers.check(R_CODIGO, restriccionCodigo(codigo));
		this.nombre = nombre;
		this.codigo = codigo;
		this.creditos = creditos;
		this.tipo = tipo;
		this.curso = curso;
	}

	private static final String R_CREDITOS = "El valor de la propiedad créditos debe ser estrictamente mayor que cero";
	private static final String R_CURSO = "El valor de la propiedad curso debe ser estrictamente mayor que cero";
	private static final String R_CODIGO = "El codigo debe estar formado por siete caracteres, todos dígitos";

	private static Boolean restriccionCreditos(Double creditos) {
		return creditos > 0;
	}

	private static Boolean restriccionCurso(Integer curso) {
		return curso > 0;
	}

	private static Boolean restriccionCodigo(String codigo) {
		return codigo.length() == 7 && Character.isDigit(0) && Character.isDigit(1) && Character.isDigit(2)
				&& Character.isDigit(3) && Character.isDigit(4) && Character.isDigit(5) && Character.isDigit(6);
	}

	public String getNombre() {
		return nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public Double getCreditos() {
		return creditos;
	}

	public TipoAsignatura getTipo() {
		return tipo;
	}

	public Integer getCurso() {
		return curso;
	}

	public String toString() {
		return "(" + getCodigo() + ") " + getNombre();
	}
}
