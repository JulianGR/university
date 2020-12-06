package fp.audiovisuales;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import fp.utiles.Checkers;

public class GestorPeliculasFavoritasImpl implements GestorPeliculasFavoritas {
	private String id, nombre;
	private Set<Pelicula> peliculas;

	public GestorPeliculasFavoritasImpl(String id, String nombre) {
		this.id = id;
		this.nombre = nombre;
		this.peliculas = new HashSet<>();
	}

	public GestorPeliculasFavoritasImpl(String id, String nombre, Set<Pelicula> peliculas) {
		this.id = id;
		this.nombre = nombre;
		this.peliculas = new HashSet<>(peliculas);
	}

	public String getId() {
		return this.id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public Set<Pelicula> getPeliculas() {
		return new HashSet<>(this.peliculas);
	}

	public void nuevaPelicula(Pelicula pelicula) {
		Checkers.checkNoNull(pelicula);
		this.peliculas.add(pelicula);
	}

	public void nuevasPeliculas(Collection<Pelicula> peliculas) {
		Checkers.checkNoNull(peliculas);
		this.peliculas.addAll(peliculas);
	}

	public void eliminaPelicula(Pelicula pelicula) {
		Checkers.checkNoNull(pelicula);
		this.peliculas.remove(pelicula);
	}

	public Integer cuentaPeliculas(String nombreActor) {
		Checkers.checkNoNull(nombreActor);
		int res = 0;
		for (Pelicula p : peliculas) {
			for (MiembroStaff m : p.getActores())
				if (m.getNombre().equals(nombreActor)) {
					res++;
				}
		}
		return res;
	}

	public MiembroStaff getActorMasPeliculas() {
		Checkers.checkNoNull(peliculas);
		MiembroStaff res = null;
		for (Pelicula p : peliculas) {
			for (MiembroStaff m : p.getActores()) {
				Integer acumulado = cuentaPeliculas(res.getNombre());
				if (res == null || acumulado < cuentaPeliculas(m.getNombre()))
					res = m;
			}
		}
		return res;
	}

	public Boolean hayAlgunaPeliculaDirigidaPor(String nombreDirector) {
		Checkers.checkNoNull(nombreDirector);
		Boolean res = false;

		for (Pelicula p : peliculas) {
			for (MiembroStaff m : p.getDirectores()) {
				if (m.getNombre().equals(nombreDirector)) {
					res = true;
					break;
				}
			}
		}
		return res;
	}

	public Pelicula getPeliculaMasActores() {
		Pelicula res = null;
		Integer acumulado = 0;
		for (Pelicula p : peliculas) {
			if (res == null || acumulado < p.getActores().size()) {
				acumulado = p.getActores().size();
				res = p;
			}
		}
		return res;
	}

	public Set<String> getGeneros() {
		Set<String> res = new HashSet<String>();
		for (Pelicula p : peliculas) {
			res.addAll(p.getGeneros());
		}
		return res;
	}

	public Set<MiembroStaff> seleccionaActoresParticipantesTodas() {
		Set<MiembroStaff> res = new HashSet<MiembroStaff>();
		for (Pelicula p : peliculas) {
			for (MiembroStaff m : p.getActores()) {
				if (p.getActores().toString().contains(m.getNombre())) {
					res.add(m);
				}

			}
		}
		return res;
	}

	public Set<Pelicula> getPeliculasDirigidasPor(String nombreDirector) {
		Set<Pelicula> res = new HashSet<>();
		for (Pelicula p : peliculas) {
			// if (peliculas.contains(nombreDirector)) {
			if (p.esDirector(nombreDirector)) {
				res.add(p);

			}

		}
		return res;
	}

	public Set<Pelicula> getPeliculasAnyo(Integer anyo) {
		Checkers.checkNoNull(anyo);
		Set<Pelicula> res = new HashSet<>();
		for (Pelicula p : peliculas) {
			Integer a = (Integer) p.getFechaEstreno().getYear();
			if (a.equals(anyo)) {
				res.add(p);

			}

		}
		return res;
	}

	public Set<Pelicula> getPeliculasDeActor(String nombreActor) {
		Set<Pelicula> res = new HashSet<>();
		for (Pelicula p : peliculas) {
			if (p.esActor(nombreActor)) {
				res.add(p);
			}

		}
		return res;
	}

	public Set<String> getPaises() {
		Set<String> res = new HashSet<>();
		for (Pelicula p : peliculas) {
			res.addAll(p.getPaises());

		}
		return res;
	}

	public boolean equals(Object obj) {
		boolean res = false;
		if (obj instanceof GestorPeliculasFavoritas) {
			GestorPeliculasFavoritas gestor = (GestorPeliculasFavoritas) obj;
			res = this.getId().equals(gestor.getId());
		}
		return res;
	}

	public int hashCode() {
		return this.getId().hashCode();
	}

	public String toString() {
		return getNombre() + " [" + getId() + "]";
	}

}
