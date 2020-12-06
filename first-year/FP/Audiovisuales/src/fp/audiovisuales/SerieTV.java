package fp.audiovisuales;

import java.time.LocalDate;

public interface SerieTV {
	Integer getId();
	String getNombre();
	LocalDate getPrimeraEmision();
	LocalDate getUltimaEmision();
	void setPrimeraEmision(LocalDate primeraEmision);
	void setUltimaEmision(LocalDate ultimaEmision);
	String getCadena();
	void setCadena(String cadena);
	String getGenero();
	void setGenero(String genero);
	String getNombreOriginal();
	void setNombreOriginal(String nombreOriginal);
	Double getPopularidad();
	void setPopularidad(Double popularidad);
	EstadoSerie getEstado();
	void setEstado(EstadoSerie estado);
	Integer getNumeroTemporadas();
	void setNumeroTemporadas(Integer temporadas);

}
