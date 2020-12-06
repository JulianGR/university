package fp.universidades;

public interface Asignatura extends Comparable<Asignatura> {
	String getNombre();

	String getCodigo();

	Double getCreditos();

	TipoAsignatura getTipo();

	Integer getCurso();

}
