package fp.musica;

import java.time.Duration;
import java.util.List;

/**
 * Tipo que representa una canción y que tiene criterio de orden. El criterio de orden de las
 *         canciones es por el id de la canción.
 * @author reinaqu 
 * @version 1.0.0.
 * 
  */
public interface Cancion extends Comparable<Cancion> {
	/**
	 * @return Una cadena que se usará como identificador único de una canción.
	 *         La cadena está formada por 22 carácteres en base 62.
	 */
	String getId();

	/**
	 * @return Una lista con los artistas que interpretan la canción.
	 */
	List<Artista> getArtistas();

	/**
	 * @return La duración de la canción. Debe ser mayor que cero.
	 */
	Duration getDuracion();

	/**
	 * @return El nombre de la canción.
	 */
	String getNombre();

	/**
	 * @return El número de pista de la canción. Es un entero mayor que cero.
	 */
	Integer getNumeroPista();

	/**
	 * @return Un número entero entre 1 y 100 que representa la popularidad de
	 *         la canción.
	 */
	Integer getPopularidad();

	/**
	 * @param popularidad
	 *            Nueva popularidad de la canción. Debe estar entre 1 y 100.
	 * @throws IllegalArgumentException,
	 *             si el parámetro popularidad no está entre 1 y 100.
	 */
	void setPopularidad(Integer popularidad);

	/**
	 * @return Dirección web en formato URL para poder hacer una preescucha de
	 *         la canción. Comienza por http.
	 */
	String getURLPreescucha();
}
