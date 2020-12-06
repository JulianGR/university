package fp.musica;

import java.time.Duration;

public interface Cancion {
	String getID();

	Artista getArtista();

	Duration getDuration();

	String getNombre();

	Integer getNumeroPista();

	Integer getPopularidad();
	
	void setPopularidad(Integer Popularidad);

}
