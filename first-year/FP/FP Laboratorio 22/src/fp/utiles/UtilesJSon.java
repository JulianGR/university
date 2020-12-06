package fp.utiles;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;



/**
 * Clase de utilicad con m�todos para ayudar a serializar y deserilizar tipos en Jackson.
 * @author To�i Reina, Ferm�n Cruz
 *
 */
public class UtilesJSon {
	private static final ObjectMapper MAPPER = new ObjectMapper();

	/**
	 * @param o 
	 *     Objeto que se va a serializar
	 * @return Cadena con el objeto o serializado en formato JSON
	 */
	public static String toJSON(Object o) {
		String res = null;
		try {
			res = MAPPER.writerWithDefaultPrettyPrinter()
					    .writeValueAsString(o);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * @param o 
	 *     Objeto que se va a serializar
	 * @param filename
	 *     Nombre del fichero en el que se va a guardar el objeto serializado
	 * Crea un fichero con nombre filename, que contiene la serializaci�n JSON del objeto o    
	 */
	public static void toJSONFile(Object o, String filename) {
		try {
			MAPPER.writeValue(new File(filename), o);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * @param jsonAsString 
	 *    Cadena en formato JSON a partir de la que se quiere crear un objeto.
	 * @param tipo Referencia a la clase que se debe usar para crear el objeto.
	 * @return Objeto de tipo T creado a partir de la cadena jsonAsString.
	 *  
	 * */
	public static <T> T fromJSON(String jsonAsString, Class<T> tipo) {
		T res = null;
		try {
			res =  MAPPER.readValue(jsonAsString, tipo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * @param fileName
	 *    Nombre y ruta del fichero JSON a partir del que se quiere crear un objeto.
	 * @param tipo Referencia a la clase que se debe usar para crear el objeto.
	 * @return Objeto de tipo T creado a partir de la cadena jsonAsString.
	 */
	public static <T> T fromJSONFile(String fileName, Class<T> tipo) {
		T res = null;
		try {
			res =  MAPPER.readValue(new File(fileName), tipo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}


	/**
	 * @param url
	 *    URL del recurso a partir del que se quiere crear un objeto.
	 * @param tipo Referencia a la clase que se debe usar para crear el objeto.
	 * @return Objeto de tipo T creado a partir de la cadena jsonAsString.
	 */
	public static <T> T fromJSON_URL(String url, Class<T> tipo) {
		T res = null;
		try {
			res =  MAPPER.readValue(new URL(url), tipo);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return res;
	}


	
	/**
	 * @param url
	 *    URL del recurso a partir del que se quiere crear un objeto.
	 * @param tipo Referencia a la clase que se debe usar para crear el objeto.
	 * @return Objeto de tipo T creado a partir de la petici�n con URL base y par�metros.
	 */
	public static <T> T fromJSON_URL(String urlBase,  String[] params, String[] values, Class<T> tipo) {
		String url = UtilesURL.codificaURL(urlBase, params, values);
		return fromJSON_URL(url, tipo);
	}
	/**
	 * @param jsonAsString
	 *    Cadena en formato JSON a partir de la que se quiere crear un objeto.
	 * @param tipo 
	 *    Objeto  de tipo TypeReference que representa una instancia de una clase gen�rica.
	 * @return Un objeto de tipo T creado a partir de la cadena jsonAsString.
	 * Esta llamada se debe usar cuando el objeto T que se quiere crear es instancia de una clase que tiene tipos gen�ricos.
	 * 
	 * Un ejemplo de llamada a este m�todo es:
	 *  <code>Page &ltTMDBMovie&gt pagina = UtilesJSon.fromJSON(cadenaJSON, new TypeReference&ltPage&ltTMDBMovie&gt&gt())</code>;
	 * En este caso, el objeto a crear es de tipo Page &ltTMDBMovie&gt, que es una instancia del tipo gen�ricoPage &ltT&gt.  
	 */
	public static <T> T fromJSON(String jsonAsString, TypeReference<T> tipo) {
		T res = null;
		try {
			res =  MAPPER.readValue(jsonAsString, tipo);
		} catch (IOException e) {			
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * @param fileName
	 *       Nombre y ruta del fichero JSON a partir del que se quiere crear un objeto.
	 * @param tipo
	 *    Objeto  de tipo TypeReference que representa una instancia de una clase gen�rica.
	 * @return Un objeto de tipo T creado a partir de la cadena jsonAsString.
	 * Esta llamada se debe usar cuando el objeto T que se quiere crear es instancia de una clase que tiene tipos gen�ricos.
	 * 
	 * Un ejemplo de llamada a este m�todo es:
	 *  <code>Page &ltTMDBMovie&gt pagina = UtilesJSon.fromJSON(ficheroJSON, new TypeReference&ltPage&ltTMDBMovie&gt&gt())</code>;
	 * En este caso, el objeto a crear es de tipo Page &ltTMDBMovie&gt, que es una instancia del tipo gen�ricoPage &ltT&gt.  
	 */
	public static <T> T fromJSONFile(String fileName, TypeReference<T> tipo) {
		T res = null;
		try {
			res =  MAPPER.readValue(new File(fileName), tipo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * @param url
	 *    URL del recurso a partir del que se quiere crear un objeto.
	 * @param tipo
	 *    Objeto  de tipo TypeReference que representa una instancia de una clase gen�rica.
	 * @return Un objeto de tipo T creado a partir de la cadena jsonAsString.
	 * Esta llamada se debe usar cuando el objeto T que se quiere crear es instancia de una clase que tiene tipos gen�ricos.
	 * 
	 * Un ejemplo de llamada a este m�todo es:
	 *  <code>Page &ltTMDBMovie&gt pagina = UtilesJSon.fromJSON(urlJSON, new TypeReference&ltPage&ltTMDBMovie&gt&gt())</code>;
	 * En este caso, el objeto a crear es de tipo Page &ltTMDBMovie&gt, que es una instancia del tipo gen�ricoPage &ltT&gt.  
	 */
	public static <T> T fromJSON_URL(String url, TypeReference<T> tipo) {
		T res = null;
		try {
			res =  MAPPER.readValue(new URL(url), tipo);
		} catch (IOException e) {
			System.err.println(e.getMessage());
//			e.printStackTrace();
		}
		return res;
	}


	public static <T> T fromJSON_URL(String urlBase,  String[] params, String[] values, TypeReference<T> tipo) {
		String url = UtilesURL.codificaURL(urlBase, params, values);
		return fromJSON_URL(url, tipo);

	}
}
