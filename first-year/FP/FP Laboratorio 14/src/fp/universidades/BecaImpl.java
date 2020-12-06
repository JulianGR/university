package fp.universidades;

import fp.utiles.Checkers;


public class BecaImpl implements Beca {
	private static final Double CUANTIA_MINIMA = 1500.0;
	private String codigo;
	private Double cuantiaTotal;
	private Integer duracion;
	private TipoBeca tipo;
	private static final String R_CODIGO = "El Codigo deben ser 7 carácteres 3 alfabeticos iniciales y 4 al final digitos";
	private static final String R_CUANTIA_TOTAL = "La cuantia total debe ser mayor o igual a :" + CUANTIA_MINIMA;
	private static final String R_DURACION = "Duración  debe ser mayor de cero";

	public BecaImpl(String codigo, TipoBeca tipo) {
		Checkers.checkNoNull(codigo, tipo);
		Checkers.check(R_CODIGO, esCodigoOK(codigo));
		this.codigo = codigo;
		this.tipo = tipo;
		this.cuantiaTotal = CUANTIA_MINIMA;
		this.duracion = 1;
	}

	public BecaImpl(String codigo, Double cuantiaTotal, Integer duracion,
			TipoBeca tipo) {
		Checkers.checkNoNull(codigo,cuantiaTotal,duracion,tipo);
		Checkers.check(R_CODIGO, esCodigoOK(codigo));
		Checkers.check(R_CUANTIA_TOTAL, esCuantiaTotalOK(cuantiaTotal));
		Checkers.check(R_DURACION, esDuracionOK(duracion));

		this.codigo = codigo;
		this.tipo = tipo;
		this.cuantiaTotal = cuantiaTotal;
		this.duracion = duracion;
	}

	private Boolean esCodigoOK(String codigo) {
		boolean esCorrecto= codigo.length() == 7 && 
				codigo.substring(0,4).matches("[A-Za-z]+") &&
				codigo.substring(4, codigo.length()).matches("[0-9]+");
		
		return esCorrecto;
	
	}

	private Boolean esCuantiaTotalOK(Double cuantiaTotal){
		return (cuantiaTotal >= CUANTIA_MINIMA) ;
	}
	
	private Boolean esDuracionOK(Integer duracion){
		return (duracion >= 1);
	}
	public String getCodigo() {
		return codigo;
	}

	public Double getCuantiaTotal() {
		return cuantiaTotal;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public TipoBeca getTipo() {
		return tipo;
	}

	public Double getCuantiaMensual() {
		return getCuantiaTotal() / getDuracion();
	}

	public void setCuantiaTotal(Double cuantiaTotal) {
		Checkers.checkNoNull(cuantiaTotal);
		Checkers.check(R_CUANTIA_TOTAL, esCuantiaTotalOK(cuantiaTotal));
		this.cuantiaTotal = cuantiaTotal;
	}

	public void setDuracion(Integer duracion) {
		Checkers.checkNoNull(duracion);
		Checkers.check(R_DURACION, esDuracionOK(duracion));
		this.duracion = duracion;
	}

	public String toString() {
		return "[" + getCodigo() + "," + getTipo()
				+ "]";
	}
	public boolean equals(Object o) {
		boolean iguales = false;

		if (o instanceof Beca) {
			Beca b = (Beca) o;
			iguales = getCodigo().equals(b.getCodigo())
					&& getTipo().equals(b.getTipo());
		}

		return iguales;
	}

	public int hashCode() {
		return getCodigo().hashCode() + getTipo().hashCode() * 31;
	}
	
	public int compareTo(Beca b) {
		int res = getCodigo().compareTo(b.getCodigo());

		if (res == 0) {
			res = getTipo().compareTo(b.getTipo());
		}

		return res;
	}
}
