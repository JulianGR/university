package fp.audiovisuales;

import java.util.List;

import fp.utiles.Checkers;

public class UtilesAudiovisuales {

	private static Integer idGenerado = 0;

	private static String siguienteID() {
		idGenerado++;
		return idGenerado.toString();
	}

	public static MiembroStaff getStaff(String nombreMiembroStaff, ClienteTMDB cliente) {
		List<MiembroStaff> personas = cliente.getPersonas(nombreMiembroStaff);
		MiembroStaff res = null;
		if (personas.size() > 0) {
			res = personas.get(0);
		}
		return res;
	}

	public static GestorSeriesFavoritas generaSeriesTitulo(String titulo, ClienteTMDB cliente) {
		GestorSeriesFavoritas res = Audiovisuales.createGestorSeriesFavoritas(siguienteID(),
				"Series con nombre " + titulo);
		List<SerieTV> series = cliente.getSeriesTV(titulo);
		res.nuevasSeries(series);

		return res;
	}

	public static GestorSeriesFavoritas generaSeriesTopAnyo(Integer anyo, ClienteTMDB cliente) {
		GestorSeriesFavoritas res = Audiovisuales.createGestorSeriesFavoritas(siguienteID(),
				"Series top del año " + anyo);
		List<SerieTV> series = cliente.getSeriesTVTopDeAnyo(anyo);
		res.nuevasSeries(series);

		return res;
	}
	
	
	/**
	 * @param anyoInicial 
	 * 			Año inicial del intervalo temporal (se incluye en el resultado)
	 * @param anyoFinal 
	 * 			Año final del intervalo temporal (no se incluye en el resultado)
	 * @param cliente 
	 * 			Cliente de la API TMDB
	 * @return Un objeto de tipo GestorSeriesFavoritas con las series TOP que se estrenaron en el 
	 *      intervalo temporal [anyoInicial, anyoFinal)
	 */
	public static GestorSeriesFavoritas generaSeriesTopAnyos(Integer anyoInicial, Integer anyoFinal,
			ClienteTMDB cliente) {
		Checkers.check("Año iniciales es porterior al final", anyoInicial < anyoFinal);
		GestorSeriesFavoritas res = Audiovisuales.createGestorSeriesFavoritas(siguienteID(),
				"Series Top De Años " + anyoInicial + "-" + anyoFinal);
		for (int i = anyoInicial; i < anyoFinal; i++) {
			List<SerieTV> series = cliente.getSeriesTVTopDeAnyo(i);
			res.nuevasSeries(series);
		}

		return res;
	}
	
	
	
	
	
	// TODO: Método a resolver en L18
	/**
	 * @param nombre
	 *   Nombre del gestor creado.
	 * @param nomFichero 
	 *   Nombre del fichero desde el que se lee la información. El fichero contiene
	 *   en cada linea el nombre de una serie.
	 * @return Un objeto de tipo GestorSeriesFavoritas con las series cuyo título aparece como parámetro cargadas
	 * en su lista de series
	 */
	public static GestorSeriesFavoritas generaSeriesFavoritasDeFichero(String nombre, String nomFichero, ClienteTMDB cliente){
		return null;
	}
		
}
