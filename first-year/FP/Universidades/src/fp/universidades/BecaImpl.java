package fp.universidades;

import fp.utiles.Checkers;

public class BecaImpl implements Beca {
	private static final Double CUANTIA_MINIMA = 1500.0;
	private static final Integer DURACION_MINIMA = 1;
	private String codigo;
	private Double cuantiaTotal;
	private Integer duracion;
	private TipoBeca tipo;

	public BecaImpl(String codigo, TipoBeca tipo) {
		Checkers.checkNoNull(codigo, tipo);
		Checkers.check(R_CODIGO, restriccionCodigo(codigo));
		this.codigo = codigo;
		this.tipo = tipo;
		this.cuantiaTotal = CUANTIA_MINIMA;
		this.duracion = 1;
	}

	public BecaImpl(String codigo, Double cuantiaTotal, Integer duracion, TipoBeca tipo) {
		Checkers.checkNoNull(codigo, cuantiaTotal, duracion);
		Checkers.check(R_CODIGO, restriccionCodigo(codigo));
		this.codigo = codigo;
		this.tipo = tipo;
		this.cuantiaTotal = cuantiaTotal;
		this.duracion = duracion;
	}

	private static final String R_CODIGO = "El c�digo est� formado por tres letras y cuatro d�gitos";
	private static final String R_CUANTIA_TOTAL = "El valor de la cuant�a total debe ser mayor o igual que la cuant�a m�nima";
	private static final String R_DURACION = "El valor de la duraci�n debe ser mayor o igual que la duraci�n minima";

	private static Boolean restriccionCuantiaTotal(Double cuantiaTotal) {
		return cuantiaTotal >= CUANTIA_MINIMA;
	}

	private static Boolean restriccionDuracion(Integer duracion) {
		return duracion >= DURACION_MINIMA;
	}

	private static Boolean restriccionCodigo(String codigo) {
		return codigo.length() == 7 && Character.isLetter(0) && Character.isLetter(1) && Character.isLetter(2)
				&& Character.isDigit(3) && Character.isDigit(4) && Character.isDigit(5) && Character.isDigit(6);
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
		this.cuantiaTotal = cuantiaTotal;
		Checkers.checkNoNull(cuantiaTotal);
		Checkers.check(R_CUANTIA_TOTAL, restriccionCuantiaTotal(cuantiaTotal));
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
		Checkers.checkNoNull(duracion);
		Checkers.check(R_DURACION, restriccionDuracion(duracion));
	}

	public String toString() {
		return "[" + getCodigo() + "," + getTipo() + "]";
	}
}
