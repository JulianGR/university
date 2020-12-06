package fp.spotify.pojo;

public class SpotifyAlbumSimplified {
	private SpotifyPage<SpotifyAlbumSimplified> albums;

	public SpotifyAlbumSimplified() {

	}

	public SpotifyPage<SpotifyAlbumSimplified> getAlbums() {
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
		SpotifyAlbumSimplified other = (SpotifyAlbumSimplified) obj;
		if (albums == null) {
			if (other.albums != null)
				return false;
		} else if (!albums.equals(other.albums))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SpotifyAlbumSimplified [getAlbums()=" + getAlbums() + ", hashCode()=" + hashCode() + "]";
	}

}
