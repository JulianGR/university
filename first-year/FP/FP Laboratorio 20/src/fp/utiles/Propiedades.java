package fp.utiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Properties;

public class Propiedades {

	public static String getProperty(String ficheroPropiedades, String propiedad) {
		Properties properties = leerPropiedadesFichero(ficheroPropiedades);
		return properties.getProperty(propiedad);
	}

	public static String getProperty(File fichero, String propiedad) {
		Properties properties = leerPropiedadesFichero(fichero);
		return properties.getProperty(propiedad);
	}
	
	public static Properties leerPropiedadesFichero(String ficheroPropiedades) {
		Properties prop = new Properties();
		InputStream inputStream = null;
		
		try {
			
			inputStream = Propiedades.class.getClassLoader().getResourceAsStream(ficheroPropiedades);
 
			if (inputStream != null) {
				prop.load(inputStream);
				inputStream.close();
			} else {
				throw new FileNotFoundException("El fichero de propiedades '" + ficheroPropiedades + "' no se ha encontrado en el classpath");
			}			
			
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} 
		return prop;
	}	

	public static Properties leerPropiedadesFichero(File ficheroPropiedades) {
		Properties prop = new Properties();
		
		try {
				prop.load(new FileReader(ficheroPropiedades));
			
		} catch (Exception e) {
			System.err.println("Exception: " + e);
		} 
		return prop;
	}
}
