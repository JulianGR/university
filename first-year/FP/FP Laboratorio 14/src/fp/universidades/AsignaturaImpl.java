package fp.universidades;

import fp.utiles.Checkers;



public class AsignaturaImpl implements Asignatura {
	private String nombre;
	private String codigo;
	private Double creditos;
	private TipoAsignatura tipo;
	private Integer curso;
	private static final String R_CODIGO = "El Codigo deben ser 7 carácteres todos digitos";
	private static final String R_CURSO = "El curso debe ser mayor de cero";
	private static final String R_CREDITOS = "Los creditos deben ser mayor de cero";

	
	// 4100002 # Fundamentos de Programación # 12.0 # ANUAL # 1
	public AsignaturaImpl(String s) {
		String[] trozos = s.split("#");
		Checkers.check("Formato incorrecto de la cadena de entrada", trozos.length==5);
		
		String codigo = trozos[0].trim();
		Double creditos = new Double(trozos[2].trim());
		Integer curso = new Integer(trozos[4].trim());
		Checkers.check(R_CODIGO, esCodigoOK(codigo));
		Checkers.check(R_CURSO, esCursoOK(curso));
		Checkers.check(R_CREDITOS, sonCreditosOK(creditos));
	
		this.nombre = trozos[1].trim();
		this.codigo = codigo;
		this.creditos = creditos;
		this.tipo = TipoAsignatura.valueOf(trozos[3].trim());
		this.curso = curso;
	}
	
	public AsignaturaImpl(String nombre, String codigo, Double creditos,
			TipoAsignatura tipo, Integer curso) {
		Checkers.checkNoNull(nombre,codigo, creditos,tipo, curso);
		Checkers.check(R_CODIGO, esCodigoOK(codigo));
		Checkers.check(R_CURSO, esCursoOK(curso));
		Checkers.check(R_CREDITOS, sonCreditosOK(creditos));
	
		this.nombre = nombre;
		this.codigo = codigo;
		this.creditos = creditos;
		this.tipo = tipo;
		this.curso = curso;
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

	public String toString2() {
		return "(" + getCodigo() + ") " + getNombre();
	}
	
	public String toString() {
		return "AsignaturaImpl [nombre=" + nombre + ", codigo=" + codigo
				+ ", creditos=" + creditos + ", tipo=" + tipo + ", curso="
				+ curso + "]";
	}

	public boolean equals(Object o) {
		boolean res = false;

		if (o instanceof Asignatura) {
			Asignatura a = (Asignatura) o;
			res = getCodigo().equals(a.getCodigo());
		}

		return res;
	}
	
	public int hashCode() {
		return getCodigo().hashCode();
	}

	public int compareTo(Asignatura a) {
		return getCodigo().compareTo(a.getCodigo());
	}
	private Boolean esCodigoOK(String codigo) {
		Boolean esCorrecto = codigo.length() == 7
				&& Character.isDigit(codigo.charAt(0))
				&& Character.isDigit(codigo.charAt(1))
				&& Character.isDigit(codigo.charAt(2))
				&& Character.isDigit(codigo.charAt(3))
				&& Character.isDigit(codigo.charAt(4))
				&& Character.isDigit(codigo.charAt(5))
				&& Character.isDigit(codigo.charAt(6));
		return esCorrecto;
	}
	private Boolean esCursoOK(Integer curso){
		return curso > 0;
		
	}
	private Boolean sonCreditosOK(Double creditos){
		return creditos > 0.0;
		
	}
}
