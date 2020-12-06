package fp.universidades;

// Representa una entrada del expediente de un alumno
public interface Nota{
	Asignatura getAsignatura();
	Integer getCursoAcademico(); //el a�o del comienzo del curso al que corresponde la nota, por ejemplo, 2014 ser�a para el curso 14-15
	Convocatoria getConvocatoria();
	Double getValor();
	Boolean getMencionHonor();	
	Calificacion getCalificacion();
}
