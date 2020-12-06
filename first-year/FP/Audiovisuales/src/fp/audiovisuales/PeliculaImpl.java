package fp.audiovisuales;

import java.time.Duration;
import java.time.LocalDate;

import fp.utiles.Checkers;

public class PeliculaImpl implements Pelicula {
	private static final String R_DURACION = "La duración debe ser mayor que 0";
	private Integer id;
	private String titulo;
	private String tituloOriginal;
	private String idiomaOriginal;
	private LocalDate fechaEstreno;
	private Duration duracion;
	private String genero;
	private String productora;
	private String pais;

	public PeliculaImpl(Integer id, String titulo, String tituloOriginal, String idiomaOriginal, LocalDate fechaEstreno,
			Duration duracion, String genero, String productora, String pais) {
		Checkers.checkNoNull(id, titulo);
		this.id = id;
		this.titulo = titulo;
		this.tituloOriginal = tituloOriginal;
		this.idiomaOriginal = idiomaOriginal;
		this.fechaEstreno = fechaEstreno;
		this.duracion = duracion;
		this.genero = genero;
		this.productora = productora;
		this.pais = pais;
	}

	public PeliculaImpl(Integer id, String titulo) {
		Checkers.checkNoNull(id, titulo);
		Checkers.check(R_DURACION, restriccionDuracion(duracion));
		this.id = id;
		this.titulo = titulo;
		this.tituloOriginal = null;
		this.idiomaOriginal = null;
		this.fechaEstreno = null;
		this.duracion = null;
		this.genero = null;
		this.productora = null;
		this.pais = null;
	}

	private static Boolean restriccionDuracion(Duration duracion) {
		return duracion.toMinutes() > 0;
	}

	public boolean equals(Object o) {
		boolean res = false;
		if (o instanceof Pelicula) {
			Pelicula p = (Pelicula) o;
			{
				res = this.getId().equals(p.getId());
			}
		}
		return res;
	}

	public int hashCode() {
		return this.getId().hashCode();
	}

	public int compareTo(Pelicula p) {
		int res = this.getId().compareTo(p.getId());
		if (res == 0) {
			res = getId().compareTo(p.getId());
		}

		return res;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		Checkers.checkNoNull(id);
		this.id = id;
	}

	public String getTitulo() {
		Checkers.checkNoNull(titulo);
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTituloOriginal() {
		return tituloOriginal;
	}

	public void setTituloOriginal(String tituloOriginal) {
		this.tituloOriginal = tituloOriginal;
	}

	public String getIdiomaOriginal() {
		return idiomaOriginal;
	}

	public void setIdiomaOriginal(String idiomaOriginal) {
		this.idiomaOriginal = idiomaOriginal;
	}

	public LocalDate getFechaEstreno() {
		return fechaEstreno;
	}

	public void setFechaEstreno(LocalDate fechaEstreno) {
		this.fechaEstreno = fechaEstreno;
	}

	public Duration getDuracion() {
		return duracion;
	}

	public void setDuracion(Duration duracion) {
		Checkers.check(R_DURACION, restriccionDuracion(duracion));
		this.duracion = duracion;
	}

	public TipoMetraje getTipoDeMetraje() {
		TipoMetraje tipoDeMetraje = null;
		if (duracion.toMinutes() < 30) {
			tipoDeMetraje = TipoMetraje.CORTOMETRAJE;
		} else if (duracion.toMinutes() <= 30 && duracion.toMinutes() >= 60) {
			tipoDeMetraje = TipoMetraje.MEDIOMETRAJE;
		} else if (duracion.toMinutes() > 60) {
			tipoDeMetraje = TipoMetraje.LARGOMETRAJE;
		}
		return tipoDeMetraje;

	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getProductora() {
		return productora;
	}

	public void setProductora(String productora) {
		this.productora = productora;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String toString() {
		return +getId() + "-" + getTitulo() + "(" + getFechaEstreno() + ")";

	}

}
