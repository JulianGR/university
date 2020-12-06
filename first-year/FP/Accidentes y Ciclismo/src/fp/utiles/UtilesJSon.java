package fp.utiles;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;



/**
 * Clase de utilicad con métodos para ayudar a serializar y deserilizar tipos en Jackson.
 * @author Toñi Reina, Fermín Cruz
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
	 * Crea un fichero con nombre filename, que contiene la serialización JSON del objeto o    
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


	}
