package fp.utiles;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UtilesURL {
	
	public static String codificaURL(String urlString, String[] params, String[] values) {
		Checkers.check("Los arrays de par�metros y valores no tienen el mismo n�mero de elementos.", params.length == values.length);
		String res = "";
		try {
			String urlStringParams = urlString + "?";
			for (int i = 0; i < params.length; i++) {
				urlStringParams += params[i] + "=" + URLEncoder.encode(values[i], "UTF-8");
				if (i < params.length - 1) {
					urlStringParams += "&";
				}
			}
			res = urlStringParams;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return res;
	}
	

}
