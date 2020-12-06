package fp.musica;

import fp.utiles.Checkers;

public class ArtistaImpl implements Artista {
	private String id;
	private String nombre;
	private String genero;
	private Integer popularidad;
	private String urlImagen;

	// RESTRICCIONES
	private static final String R_ID = "El Id deben ser 22 carácteres";
	private static final String R_POPULARIDAD = "La popularidad tiene que estar entre 0 y 100";
	private static final String R_URL = "La URL debe comenzar por http";

	private static Boolean restriccionId(String id) {
		return id.length() == 22;
	}

	private static Boolean restriccionPopularidad(Integer popularidad) {
		return popularidad >= 0 && popularidad <= 100;
	}

	private static Boolean restriccionURL(String urlImagen) {
		return urlImagen.startsWith("http");
	}

	public ArtistaImpl(String id, String nombre, String genero, Integer popularidad, String urlImagen) {
		Checkers.checkNoNull(id);
		Checkers.check(R_ID, restriccionId(id));
		this.id = id;
		this.nombre = nombre;
		this.genero = genero;
		this.popularidad = popularidad;
		this.urlImagen = urlImagen;
	}

	public boolean equals(Object o) {
		boolean res = false;
		if (o instanceof Artista) {
			Artista a = (Artista) o;
			{
				res = this.getId().equals(a.getId());
			}
		}
		return res;
	}

	public int hashCode() {
		return this.getId().hashCode();
	}

	public int compareTo(Artista a) {
		int res = this.getId().compareTo(a.getId());
		if (res == 0) {
			res = getId().compareTo(a.getId());
		}

		return res;
	}

	public String getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getGenero() {
		return genero;
	}

	public Integer getPopularidad() {
		return popularidad;
	}

	public String getURLImagen() {
		return urlImagen;
	}

	public void setURLImagen(String URLImagen) {
		this.urlImagen = URLImagen;
		Checkers.checkNoNull(URLImagen);
		Checkers.check(R_URL, restriccionURL(URLImagen));

	}

	public void setPopularidad(Integer Popularidad) {
		this.popularidad = Popularidad;
		Checkers.checkNoNull(popularidad);
		Checkers.check(R_POPULARIDAD, restriccionPopularidad(Popularidad));
	}

	public String toString() {
		return "[" + getNombre() + "]" + "[" + getId() + "]";

	}

}
