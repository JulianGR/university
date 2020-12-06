package fp.rest;

import com.fasterxml.jackson.core.type.TypeReference;

import fp.utiles.UtilesJSon;

public class ClienteRESTImpl implements ClienteREST {

	public ClienteRESTImpl() {

	}

	/* (non-Javadoc)
	 * @see fp.utiles.ClienteREST#get(java.lang.String, java.lang.Class)
	 */
	public <T> T get(String url, Class<T> type) {
		
		return UtilesJSon.fromJSON_URL(url, type);
	}
	
	/* (non-Javadoc)
	 * @see fp.utiles.ClienteREST#get(java.lang.String, java.lang.String[], java.lang.String[], java.lang.Class)
	 */
	public <T> T get(String urlBase,  String[] params, String[] values, Class<T> tipo) {
		return UtilesJSon.fromJSON_URL(urlBase, params, values, tipo);
	}
	
	/* (non-Javadoc)
	 * @see fp.utiles.ClienteREST#get(java.lang.String, com.fasterxml.jackson.core.type.TypeReference)
	 */
	public <T> T get(String url, TypeReference<T> type) {
		
		return UtilesJSon.fromJSON_URL(url, type);
	}
	

	/* (non-Javadoc)
	 * @see fp.utiles.ClienteREST#get(java.lang.String, java.lang.String[], java.lang.String[], com.fasterxml.jackson.core.type.TypeReference)
	 */
	public <T> T get (String urlBase,  String[] params, String[] values, TypeReference<T> tipo) {
		return UtilesJSon.fromJSON_URL(urlBase, params, values, tipo);
	}

	
}
