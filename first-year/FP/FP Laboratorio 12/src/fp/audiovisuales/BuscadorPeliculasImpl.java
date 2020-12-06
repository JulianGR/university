package fp.audiovisuales;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import fp.ejercicios.audiovisuales.GestorPeliculasFavoritas;
import fp.utiles.Checkers;

public class BuscadorPeliculasImpl implements BuscadorPeliculas {
	private Map<String, Set<Pelicula>> indice;
	
	//C1
	public BuscadorPeliculasImpl() {
		indice = new HashMap<>();
	}
	
	//C2
	public BuscadorPeliculasImpl(GestorPeliculasFavoritas favoritas) {
		this(favoritas.getPeliculas());
	}
	
	//C3
	public BuscadorPeliculasImpl(Collection<Pelicula> pelicula) {
		this();	//Invocamos a C1
		indexaPeliculas(pelicula);
	}

	private String[] extraePalabras(String s) {
		return s.replaceAll("[^A-Za-záéíóú]+", " ").replaceAll(" +", " ").toLowerCase().split(" ");
	}

	public boolean equals(Object o) {
		boolean res = false;
		if (o instanceof BuscadorPeliculas) {
			res = ((BuscadorPeliculas) o).getPeliculas().equals(getPeliculas());
		}
		return res;
	}

	public int hashCode() {
		return getPeliculas().hashCode();
	}

	public String toString() {
		return getPeliculas().size() + " películas indexadas";
	}

	public void indexaPeliculas(Collection<Pelicula> peliculas) {
		for(Pelicula p: peliculas) {
			indexaPelicula(p);
		}
		
	}

	public void indexaPelicula(Pelicula p) {
		String[] palabras = extraePalabras(p.getTitulo());
		for(String palabra: palabras) {
			if(indice.containsKey(palabra)) {
				indice.get(palabra).add(p);
			} else {
				Set<Pelicula> aux = new HashSet<>();
				aux.add(p);
				indice.put(palabra, aux);
				
			}
		}
		
	}

	public Map<String, Set<Pelicula>> getIndicePeliculas() {
		return new HashMap<>(indice);
	}

	public Set<Pelicula> getPeliculas() {
		Set<Pelicula> res = new HashSet<>();
		for(Set<Pelicula> peliculas: indice.values()) {
			res.addAll(peliculas);
		}
		return res;
	}
	
	public Set<Pelicula> buscaPeliculasPorTitulo(String palabra) {
		Set<Pelicula> res = new HashSet<>();
		Set<Pelicula> peliculas = indice.get(palabra.toLowerCase());
		if(peliculas != null)
			res.addAll(peliculas);
		return res;
	}

	public Set<Pelicula> buscaPeliculasPorTitulo(String[] palabras) {
	Checkers.check("La busqueda debe contener al menos una pelicula", palabras.length > 0);
	Set<Pelicula> res = indice.get(palabras[0].toLowerCase());
	for(int i = 1; i < palabras.length; i++) {
		Set<Pelicula> aux = indice.get(palabras[i].toLowerCase());
		if(aux == null) { //Si la busqueda no produce resultados, borramos el conjunto resultado y salimos del bucle
			res.clear();
			break;
		} else { //Si la busqueda produce resultados, hacemos la interseccion con lo anterior
			res.retainAll(aux);
		}
	}
		return res;
	}
	
	public SortedMap<String, Set<Pelicula>> getPeliculasPorActor(){
		SortedMap<String, Set<Pelicula>> res = new TreeMap<>();
		//Por hacer
		return res;
	}
}
