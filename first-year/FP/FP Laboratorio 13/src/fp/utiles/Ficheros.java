package fp.utiles;

import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Ficheros {
	public static List<String> leeFichero(String path) {
		List<String> res = null;
		try {
			res = Files.readAllLines(Paths.get(path));
		} catch (IOException e) {
			e.printStackTrace();
			res = new ArrayList<String>();
		}
		return res;
	}
}
