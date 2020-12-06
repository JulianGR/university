package fp.musica;

import java.time.Duration;

import fp.utiles.Checkers;

public class CancionImpl implements Cancion {
	String id;
	Artista artista;
	Duration duration;
	String nombre;
	Integer numeroPista;
	Integer popularidad;

	private static final String R_ID = " El id está formado por 22 caracteres, igual que el id del Artista";
	private static final String R_DURATION = "El valor en segundos de la duración de una canción siempre es positivo";
	private static final String R_NUMERO_PISTA = "El número de la pista siempre es un número positivo";
	private static final String R_POPULARIDAD = "El valor de la popularidad se debe encontrar en el intervalo [0, 100]";

	public CancionImpl(String id, Artista artista, Duration duration, String nombre, Integer numeroPista,
			Integer popularidad) {
		Checkers.checkNoNull(id, artista, duration, nombre, numeroPista, popularidad);
		Checkers.check(R_ID, restriccionId(id));
		Checkers.check(R_DURATION, restriccionDuration(duration));
		Checkers.check(R_NUMERO_PISTA, restriccionNumeroPista(numeroPista));
		Checkers.check(R_POPULARIDAD, restriccionPopularidad(popularidad));

		this.id = id;
		this.artista = artista;
		this.nombre = nombre;
		this.duration = duration;
		this.popularidad = popularidad;
		this.numeroPista = numeroPista;
	}

	public boolean equals(Object o) {
		boolean res = false;
		if (o instanceof Cancion) {
			Cancion c = (Cancion) o;
			{
				res = this.getID().equals(c.getID());
			}
		}
		return res;
	}

	public int hashCode() {
		return this.getID().hashCode();
	}

	public int compareTo(Cancion c) {
		int res = this.getID().compareTo(c.getID());
		if (res == 0) {
			res = getID().compareTo(c.getID());
		}

		return res;
	}

	private static Boolean restriccionId(String Id) {
		return Id.length() == 22;
	}

	private static Boolean restriccionDuration(Duration duration) {
		return duration.getSeconds() > 0;
	}

	private static Boolean restriccionNumeroPista(Integer numeroPista) {
		return numeroPista > 0;
	}

	private static Boolean restriccionPopularidad(Integer popularidad) {
		return popularidad >= 0 && popularidad <= 100;
	}

	public String getID() {
		return id;
	}

	public Artista getArtista() {
		return artista;
	}

	public Duration getDuration() {
		return duration;
	}

	public String getNombre() {
		return nombre;
	}

	public Integer getNumeroPista() {
		return numeroPista;
	}

	public Integer getPopularidad() {
		return popularidad;
	}
	
	public void setPopularidad(Integer Popularidad) {
		this.popularidad = Popularidad;
		Checkers.checkNoNull(popularidad);
		Checkers.check(R_POPULARIDAD, restriccionPopularidad(Popularidad));
	}

	public String toString() {
		return "[" + this.getNombre() + "]" + "(" + this.getArtista() + ")";

	}
}
