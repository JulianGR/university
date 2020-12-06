package fp.musica;

import java.util.ArrayList;
import java.util.List;

import fp.utiles.Checkers;

/**
 * Clase que implementa al tipo Album.
 * @author reinaqu
 *
 */
public class AlbumImpl implements Album{
	private String id,nombre;
	private TipoAlbum tipo;
	private List<Artista> artistas;
	private List<String> generos,urlImagenes;	
	private Integer popularidad,anyoPublicacion;		
	private List<Cancion> canciones;	

	
	private static final String R_ID = "El Id deben ser 22 carácteres en base 62";
	private static final String R_POPULARIDAD = "La popularidad tiene que estar entre 1 y 100";
	private static final String R_URLIMAGEN = "La URL debe comenzar por http";
	
	/**
	 * @param id Identificador del álbum. Debe ser una cadena de 22 caracteres en base 62.
	 * @param nombre Nombre del álbum.
 	 * @param tipo Tipo del álbum: ALBUM, SINGLE, COMPILATION
	 * @param artistas Lista de artistas que intervienen en el álbum.
	 * @param generos Lista de géneros musicales del álbum.
	 * @param popularidad Popularidad del álbum. Debe estar entre 1 y 100.
	 * @param anyoPublicacion Año de publicación del álbum.
	 * @param canciones Lista de las canciones incluidas en el álbum.
	 * @param urlImagenes Lista de urls que dan acceso a imágenes del álbum. Todas las urls deben comenzar por http.
	 * @throws IllegalArgumentException
	 *             si alguno de los parámetros es nulo.
	 * @throws IllegalArgumentException
	 *             si el id no es una cadena de 22 caracteres en base 62
	 * @throws IllegalArgumentException
	 *             si la popularidad no está entre 1 y 100
	 * @throws IllegalArgumentException
	 *             si alguna de las urls de la lista no comienza por http.

	 */
	public AlbumImpl(String id, String nombre, TipoAlbum tipo, List<Artista> artistas, List<String> generos,
			Integer popularidad, Integer anyoPublicacion, List<Cancion> canciones, List<String> urlImagenes) {
		Checkers.checkNoNull(id,nombre,tipo,artistas,generos,popularidad,anyoPublicacion,canciones,urlImagenes);
		Checkers.check(R_ID, restriccionId(id));
		Checkers.check(R_POPULARIDAD, restriccionPopularidad(popularidad));
		Checkers.check(R_URLIMAGEN, restriccionURLImagenes(urlImagenes));
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.artistas = new ArrayList<Artista>(artistas); // Lo copiamos porque es sólo consultable
		this.generos = new ArrayList<String>(generos); // Lo copiamos porque es sólo consultable
		this.popularidad = popularidad;
		this.anyoPublicacion = anyoPublicacion;
		this.canciones = new ArrayList<Cancion>(canciones); // Lo copiamos porque es sólo consultable
		this.urlImagenes = new ArrayList<String>(urlImagenes); // Lo copiamos porque es sólo consultable
	}
	
	private static Boolean restriccionPopularidad(Integer p) {
		return p >= 0 && p <= 100;
	}

	private static Boolean restriccionId(String id) {
		return id.length() == 22;
	}
	
	private static Boolean restriccionURLImagenes(List<String> urlImagenes) {
		Boolean res = true;
		for(String url:urlImagenes){
			if(!url.startsWith("http")){
				res=false;
				break;
			}
		}
		return res;
	}

	public String getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public TipoAlbum getTipo() {
		return tipo;
	}

	public List<Artista> getArtistas() {
		return new ArrayList<>(artistas);
	}

	public List<String> getGeneros() {
		return new ArrayList<>(generos);
	}

	public Integer getPopularidad() {
		return popularidad;
	}

	public Integer getAnyoPublicacion() {
		return anyoPublicacion;
	}

	public List<Cancion> getCanciones() {
		return new ArrayList<>(canciones);
	}

	public List<String> getURLImagenes() {
		return new ArrayList<>(urlImagenes);
	}

	public String toString(){
		return getNombre()+" ("+getId()+")";
	}
	
	public boolean equals(Object o) {
		boolean res = false;
		if (o instanceof Album) {
			Album a = (Album) o;
			res = this.getId().equals(a.getId());
		}
		return res;
	}
	
	public int hashCode(){
		return getId().hashCode();
	}

	public int compareTo(Album a) {
		return this.getId().compareTo(a.getId());
	}	
}
