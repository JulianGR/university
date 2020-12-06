package fp.universidades;

public interface Beca extends Comparable<Beca> {
	String getCodigo();
	Double getCuantiaTotal();
	Integer getDuracion();
	TipoBeca getTipo();
	Double getCuantiaMensual();
	void setCuantiaTotal(Double cuantia);
	void setDuracion(Integer duracion);
}
