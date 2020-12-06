package fp.audiovisuales;

import java.time.LocalDate;

public class SerieTVImpl implements SerieTV {

	private String cadena;
	private EstadoSerie estado;
	private String genero;
	private Integer id;
	private String nombre;
	private String nombreOriginal;
	private Integer temporadas;
	private Double popularidad;
	private LocalDate primeraEmision;
	private LocalDate ultimaEmision;
	
	public SerieTVImpl(Integer id, String nombre, LocalDate primeraEmision, LocalDate ultimaEmision,
			String cadena, String genero, String nombreOriginal, Double popularidad, EstadoSerie estado,
			Integer temporadas) {
		this.id = id;
		this.nombre = nombre;
		this.primeraEmision = primeraEmision;
		this.ultimaEmision = ultimaEmision;
		this.cadena = cadena;
		this.genero = genero;
		this.nombreOriginal = nombreOriginal;
		this.popularidad = popularidad;
		this.estado = estado;
		this.temporadas = temporadas;
	}
	
	public SerieTVImpl(Integer id, String nombre, EstadoSerie estado, Integer temporadas) {
		this(id, nombre, null, null, null, null, null, null, estado, temporadas);
	}
	
	public SerieTVImpl(Integer id, String nombre, Integer temporadas) {
		this(id, nombre, null, null, null, null, null, null, EstadoSerie.EN_CURSO, temporadas);
	}
	
	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public LocalDate getPrimeraEmision() {
		return primeraEmision;
	}

	public LocalDate getUltimaEmision() {
		return ultimaEmision;
	}

	public void setPrimeraEmision(LocalDate primeraEmision) {
		this.primeraEmision = primeraEmision;
	}

	public void setUltimaEmision(LocalDate ultimaEmision) {
		this.ultimaEmision = ultimaEmision;
	}

	public String getCadena() {
		return cadena;
	}

	public void setCadena(String cadena) {
		this.cadena = cadena;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getNombreOriginal() {
		return nombreOriginal;
	}

	public void setNombreOriginal(String nombreOriginal) {
		this.nombreOriginal = nombreOriginal;
	}

	public Double getPopularidad() {
		return popularidad;
	}

	public void setPopularidad(Double popularidad) {
		this.popularidad = popularidad;
	}

	public EstadoSerie getEstado() {
		return estado;
	}

	public void setEstado(EstadoSerie estado) {
		this.estado = estado;
	}

	public Integer getNumeroTemporadas() {
		return temporadas;
	}

	public void setNumeroTemporadas(Integer temporadas) {
		this.temporadas = temporadas;
	}

	public String toString() {
		return id + " - " + nombre + "(" + estado + ")";
	}
	
}
