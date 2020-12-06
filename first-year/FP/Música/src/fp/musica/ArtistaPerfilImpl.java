package fp.musica;

public class ArtistaPerfilImpl extends ArtistaImpl implements ArtistaPerfil {

	// Constructor
	public ArtistaPerfilImpl(String id, String nombre, String gener, Integer popularidad, String urlImagen,
			String nickname, RedSocial redSocial) {
		super(id, nombre, gener, popularidad, urlImagen);
		this.nickname = nickname;
		this.redSocial = redSocial;
	}

	private String nickname;
	private RedSocial redSocial;

	public String getNickname() {
		return this.nickname;
	}

	public RedSocial getRedSocial() {
		return this.redSocial;
	}

	public String toString() {
		return super.toString() + " - perfil en: " + this.getRedSocial() + ":" + this.getNickname();
	}

}
