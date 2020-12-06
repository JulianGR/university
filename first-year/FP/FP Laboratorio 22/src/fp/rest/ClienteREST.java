package fp.rest;

import com.fasterxml.jackson.core.type.TypeReference;

public interface ClienteREST {
	/**
	 * @param url URL mediante la que se hace la petici�n
	 * @param type Referencia al tipo en el que se va a deserializar
	 * 		el objeto.
	 * @return El objeto obtenido como resultado de deserializar
	 * 		la respuesta obtenida en formato JSON, o null si 
	 * 		el objeto no se ha podido deserializar.
	 */
	<T> T get(String url, Class<T> type);
	
	/**
	 * @param urlBase
	 *    URL del recurso a partir del que se quiere crear un objeto.
	 * @param params Array con par�metros que se pasan en la url.
	 * @param values Array con valores que se pasan en la url.
	 * @param tipo Referencia al tipo en el que se va a deserializar
	 * 		el objeto.
	 * @return El objeto obtenido como resultado de deserializar
	 * 		la respuesta obtenida en formato JSON, o null si 
	 * 		el objeto no se ha podido deserializar.
	 */
	<T> T get(String urlBase,  String[] params, String[] values, Class<T> tipo);

	/**
	 * @param url
	 *    URL del recurso a partir del que se quiere crear un objeto.
	 * @param tipo
	 *    Objeto  de tipo TypeReference que representa una instancia de una clase gen�rica.
	 * @return Un objeto de tipo T creado a partir de la cadena jsonAsString.
	 * Esta llamada se debe usar cuando el objeto T que se quiere crear es instancia de una clase que tiene tipos gen�ricos.
	 * 
	 * Un ejemplo de llamada a este m�todo es:
	 *  <code>Page &ltTMDBMovie&gt pagina = cliente.get(urlJSON, new TypeReference&ltPage&ltTMDBMovie&gt&gt())</code>;
	 * En este caso, el objeto a crear es de tipo Page &ltTMDBMovie&gt, que es una instancia del tipo gen�ricoPage &ltT&gt.  
	 */
	<T> T get(String url, TypeReference<T> type);
	
	/**
	 * @param urlBase
	 *    URL del recurso a partir del que se quiere crear un objeto.
	 * @param params Array con par�metros que se pasan en la url.
	 * @param values Array con valores que se pasan en la url.
	 * @param tipo Referencia a la clase que se debe usar para crear el objeto.
	 * @return Objeto de tipo T creado a partir de la petici�n con URL base y par�metros. Si no se puede crear el objeto
	 *   devuelve null.
	 */
	<T> T get (String urlBase,  String[] params, String[] values, TypeReference<T> tipo);
}