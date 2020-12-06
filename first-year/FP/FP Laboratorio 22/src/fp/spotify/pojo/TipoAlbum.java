package fp.spotify.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TipoAlbum {
	/* Para poder anotar los valores del enumerado, es necesario Jackson 2.7.2 */
	@JsonProperty("album")
	ALBUM,
	@JsonProperty("single")
	SINGLE,
	@JsonProperty("compilation")
	COMPILATION;
}
