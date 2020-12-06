package fp.musica;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import fp.utiles.Checkers;
import fp.utiles.Imagenes;

public class ListaReproduccionImpl implements ListaReproduccion {
	private String nombre;
	private List<Cancion> canciones;

	public ListaReproduccionImpl(String nombreUsuario) {
		this.nombre = nombreUsuario;
		this.canciones = new ArrayList<Cancion>();

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Cancion> getCanciones() {
		return new ArrayList<Cancion>(canciones);
	}

	public Integer getNumeroCanciones() {
		return canciones.size();
	}

	public void aleatoriza() {
		Collections.shuffle(canciones);
	}

	public void incorpora(Cancion c) {
		canciones.add(c);
	}

	public void incorpora(Album a) {
		canciones.addAll(a.getCanciones());
	}

	public void incorpora(List<Cancion> canciones) {
		this.canciones.addAll(canciones);
	}

	public void eliminaPrimera(Cancion c) {
		Checkers.check("La canción no se encuentra en la lista de reproducción", canciones.contains(c));
		canciones.remove(c);
	}

	public void eliminaUltima(Cancion c) {
		Checkers.check("La canción no se encuentra en la lista de reproducción", canciones.contains(c));
		canciones.remove(canciones.lastIndexOf(c));
	}

	public void eliminaTrozo(int primeraPosicion, int ultimaPosicion) {
		Checkers.check("Primera peticion no es un valor valido",
				(primeraPosicion >= 0 && ultimaPosicion < getNumeroCanciones() && primeraPosicion < ultimaPosicion));
		canciones.subList(primeraPosicion, ultimaPosicion).clear();
	}

	public String toString() {
		return getNombre() + " (" + getNumeroCanciones() + " canciones)";
	}

	public boolean equals(Object o) {
		boolean res = false;
		if (o instanceof ListaReproduccion) {
			ListaReproduccion l = (ListaReproduccion) o;
			res = canciones.equals(l.getCanciones());
		}
		return res;
	}

	public int hashCode() {
		return canciones.hashCode();
	}

	// ==============================TRATAMIENTOS
	// SECUENCIALES=============================
	public Boolean esAntologia(String artista) {
		Boolean res = true;
		for (Cancion c : canciones) {
			res = (c.getArtistas().toString().equals(artista));
			if (!res) {
				break;
			}
		}
		return res;
	}

	public Boolean contieneArtista(String artista) {

		Boolean res = false;
		for (Cancion c : canciones) {
			res = (c.getArtistas().toString().contains(artista));
			if (res) {
				res = true;
				break;
			}
		}
		return res;
	}

	public int getPosicionCancion(String tituloCancion) {
		int res = -1;
		int pos = 0;
		for (Cancion c : canciones) {
			if (c.getNombre().equals(tituloCancion))
				res = pos;
			break;
		}
		pos++;
		return res;
	}

	public Cancion getCancionMasLarga() {
		Cancion res = null;
		for (Cancion c : canciones) {
			if (res == null) {
				res = c;
			} else if ((c.getDuracion()).compareTo(res.getDuracion()) < 0)
				res = c;
		}

		if (res == null) {
			throw new NoSuchElementException("Elemento no encontrado");

		}
		return res;
	}

	public Duration getDuracion() {
		Duration res = Duration.ZERO;
		for (Cancion c : canciones) {
			res = res.plus(c.getDuracion());

		}
		return res;
	}

	public ListaReproduccion getSublistaArtista(String artista) {
		List<Cancion> cancionesSubArtista = new ArrayList<Cancion>();
		for (Cancion c : canciones) {
			if (c.getArtistas().toString().equals(artista)) {
				cancionesSubArtista.add(c);
			}
		}
		return  (ListaReproduccion) cancionesSubArtista;

	}

	public Set<Artista> getArtistas() {
		Set<Artista> res = new HashSet<>();
		for (Cancion c : canciones) {
			res.addAll(c.getArtistas());

		}
		return res;
	}

	public void muestraFotosArtistas() {
		for (Artista a : getArtistas()) {
			if (a.getURLImagenes().isEmpty()) {
				Imagenes.show(a.getNombre(), a.getURLImagenes().get(0));
			}
		}

	}
	// MODO EXAMEN

	public Boolean tieneTodasLasCancionesDuracionInferiorA(Duration d) {
		Boolean res = true;
		for (Cancion c : canciones) {

			if (c.getDuracion().compareTo(d) < 0) {
				res = false;
				break;
			}
		}
		return res;
	}
}
