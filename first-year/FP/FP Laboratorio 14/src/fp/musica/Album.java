package fp.musica;

import java.util.List;

/**
 * Tipo que representa un album con las canciones de uno o varios artistas. Los
 * albumes se pueden ordenar. El criterio de orden de un album es por su id.
 * 
 * @author reinaqu
 */
public interface Album extends Comparable<Album> {
	/**
	 * @return El tipo de album: ALBUM, SINGLE o COMPILATION
	 */
	TipoAlbum getTipo();

	
	/**
	 * @return Una lista con los artistas que intervienen en el album.
	 */
	List<Artista> getArtistas();

	
	/**
	 * @return Una lista con los géneros de las canciones que forman el album.
	 */
	List<String> getGeneros();

	/**
	 * @return El nombre del album.
	 */
	String getNombre();

	/**
	 * @return Un entero entre 1 y 100 que representa la popularidad del album. 
	 */
	Integer getPopularidad();

	/**
	 * @return Una cadena de 22 caracteres en base 62 que sirve para identificar de forma única al álbum.
	 */
	String getId();

	/**
	 * @return El año en el que se publicó el álbum.
	 */
	Integer getAnyoPublicacion();

	/**
	 * @return La lista con las canciones que están incluidas en el álbum.
	 */
	List<Cancion> getCanciones();

	
	/**
	 * @return Lista con urls de imágenes del álbum. Todas las urls comienzan por http.
	 */
	List<String> getURLImagenes();
}
