package fp.musica;

public interface Artista {
	String getId();
	String getNombre();
	String getGenero();
	Integer getPopularidad();
	String getURLImagen();
	void setURLImagen(String URLImagen);
	void setPopularidad(Integer Popularidad);
}
