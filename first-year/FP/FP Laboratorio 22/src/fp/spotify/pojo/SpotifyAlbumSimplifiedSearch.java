package fp.spotify.pojo;

/**
 * Este tipo sirve para almacenar la informaciï¿½n devuelta cuando se hace una
 * bï¿½squeda de artista.
 * 
 * @author Fermín Cruz
 *
 */

public class SpotifyAlbumSimplifiedSearch {
	private SpotifyPage<SpotifyAlbum> albums;

	public SpotifyAlbumSimplifiedSearch() {

	}

	public SpotifyPage<SpotifyAlbum> getAlbums() {
		return albums;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((albums == null) ? 0 : albums.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SpotifyAlbumSimplifiedSearch other = (SpotifyAlbumSimplifiedSearch) obj;
		if (albums == null) {
			if (other.albums != null)
				return false;
		} else if (!albums.equals(other.albums))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SpotifyAlbumSearch [getAlbums()=" + getAlbums() + ", hashCode()=" + hashCode() + "]";
	}

}
