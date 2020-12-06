package fp.universidades;

import java.util.ArrayList;
import java.util.List;

import fp.utiles.Ficheros;

public class AsignaturaFactoria {
	public static Asignatura createAsignatura(String nombre, String codigo, Double creditos, TipoAsignatura tipo,
			Integer curso) {
		return new AsignaturaImpl(nombre, codigo, creditos, tipo, curso);

	}

	public static Asignatura createAsignatura(String s) {
		return new AsignaturaImpl(s);
	}

	public static Asignatura createAsignatura(Asignatura a) {
		return new AsignaturaImpl(a.getNombre(), a.getCodigo(), a.getCreditos(), a.getTipo(), a.getCurso());
	}

	public static List<Asignatura> creaAsignatura(String path) {
		List<Asignatura> res = new ArrayList<>();
		List<String> lineas = Ficheros.leeFichero(path);
		for (String s : lineas) {
			res.add(new AsignaturaImpl(s));
		}
		return res;

	}

}
