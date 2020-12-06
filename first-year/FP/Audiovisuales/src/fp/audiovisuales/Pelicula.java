package fp.audiovisuales;

import java.time.Duration;
import java.time.LocalDate;

public interface Pelicula {
	Integer getId();

	String getTitulo();

	String getTituloOriginal();

	void setTituloOriginal(String tituloOriginal);

	String getIdiomaOriginal();

	void setIdiomaOriginal(String idiomaOriginal);

	LocalDate getFechaEstreno();

	void setFechaEstreno(LocalDate fechaEstreno);

	Duration getDuracion();

	void setDuracion(Duration duracion);

	TipoMetraje getTipoDeMetraje();

	String getGenero();

	void setGenero(String genero);

	String getProductora();

	void setProductora(String productora);

	String getPais();

	void setPais(String pais);

}
