package fp.musica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fp.musica.Album;
import fp.musica.Cancion;
import fp.utiles.Checkers;

public class ListaReproduccionImpl implements ListaReproduccion {
	private String nombre;
	private List<Cancion> canciones;
	
	public ListaReproduccionImpl(String nombre){
		this.nombre=nombre;
		this.canciones=new ArrayList<>();
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre=nombre;
	}
	
	public List<Cancion> getCanciones(){
		return new ArrayList<>(canciones);
	}
	
	public Integer getNumeroCanciones(){
		return canciones.size();
	}
	
	public void aleatoriza(){
		Collections.shuffle(canciones);
	}
	
	public void incorpora(Cancion c){
		canciones.add(c);
	}
	
	public void incorpora(Album a){
		canciones.addAll(a.getCanciones());
	}
	
	public void incorpora(List<Cancion> canciones){
		this.canciones.addAll(canciones);
	}
	
	public void eliminaPrimera(Cancion c){
		Checkers.check("La canción que se pretende eliminar no pertenece a la lista de reproducción", canciones.contains(c));
		canciones.remove(c);
	}
	
	public void eliminaUltima(Cancion c){
		Checkers.check("La canción que se pretende eliminar no pertenece a la lista de reproducción", canciones.contains(c));
		canciones.remove(canciones.lastIndexOf(c));
	}
	
	public void eliminaTrozo(int primeraPosicion, int ultimaPosicion){
		Checkers.check("La primera posición no es un valor válido.",primeraPosicion >= 0 && ultimaPosicion < getNumeroCanciones() && primeraPosicion <= ultimaPosicion);
		canciones.subList(primeraPosicion, ultimaPosicion+1).clear();
	}
	
	public String toString(){
		return getNombre()+" ("+getNumeroCanciones()+" canciones)";
	}
	
	public boolean equals(Object o){
		boolean res = false;
		if(o instanceof ListaReproduccion){
			ListaReproduccion l = (ListaReproduccion)o;
			res = canciones.equals(l.getCanciones());
		}
		return res;
	}
	
	public int hashCode(){
		return canciones.hashCode();
	}
}
