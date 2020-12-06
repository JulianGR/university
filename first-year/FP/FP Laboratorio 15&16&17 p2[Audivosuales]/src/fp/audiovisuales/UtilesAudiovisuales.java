package fp.audiovisuales;

import java.util.List;

public class UtilesAudiovisuales {

	private static Integer idGenerado = 0;

	private static String siguienteID() {
		idGenerado++;
		return idGenerado.toString();
	}

	public static GestorPeliculasFavoritas generaPeliculasActor(String nombreActor, ClienteTMDB cliente) {
		GestorPeliculasFavoritas res = new GestorPeliculasFavoritasImpl2(siguienteID(), 
				"Peliculas de " + nombreActor);
		List<MiembroStaff> personas = cliente.getPersonas(nombreActor);
		MiembroStaff actor = personas.get(0);
	
		List<Pelicula> peliculasActor = cliente.getPeliculasComoActor(actor.getId());
		res.nuevasPeliculas(peliculasActor);
		
		return res;
	}
	
	public static GestorPeliculasFavoritas generaPeliculasTopAnyo(Integer anyo, ClienteTMDB cliente) {
		GestorPeliculasFavoritas res = new GestorPeliculasFavoritasImpl2(siguienteID(), 
				"Peliculas Top de " + anyo);

		List<Pelicula> peliculasTop= cliente.getPeliculasTopDeAnyo(anyo);
		res.nuevasPeliculas(peliculasTop);
		
		return res;
	}
	
	

}
