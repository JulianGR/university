package fp.universidades;

import fp.utiles.Checkers;

public class NotaImpl implements Nota {
	private Asignatura asignatura;
	private Integer curso;
	private Convocatoria convocatoria;
	private Double valor;
	private Boolean mencionHonor;

	public NotaImpl(Asignatura asignatura, Integer curso, Convocatoria convocatoria, Double valor,
			Boolean mencionHonor) {
		Checkers.checkNoNull(asignatura, curso, convocatoria, valor, mencionHonor);
		Checkers.check(R_NOTA, restriccionNota(valor));
		Checkers.check(R_MENCION_HONOR, restriccionMencionHonor(valor));

		this.asignatura = asignatura;
		this.curso = curso;
		this.convocatoria = convocatoria;
		this.valor = valor;
		this.mencionHonor = mencionHonor;
	}

	public NotaImpl(Asignatura asignatura, Integer curso, Convocatoria convocatoria, Double valor) {
		this(asignatura, curso, convocatoria, valor, false);
	}

	private static final String R_NOTA = "La nota debe estar en el intervalo [0, 10]";
	private static final String R_MENCION_HONOR = "La mención de honor solo puede darse si la nota es mayor o igual a 9";

	private static Boolean restriccionNota(Double valor) {
		return valor >= 0 && valor <= 10;
	}

	private static Boolean restriccionMencionHonor(Double valor) {
		return valor >= 9;
	}

	public boolean equals(Object o) {
		boolean res = false;
		if (o instanceof Nota) {
			Nota nt = (Nota) o;
			{
				res = this.getCursoAcademico().equals(nt.getCursoAcademico())
						&& this.getAsignatura().equals(nt.getAsignatura())
						&& this.getConvocatoria().equals(nt.getConvocatoria());
			}
		}
		return res;
	}

	public int hashCode() {
		return this.getAsignatura().hashCode() + 31 * this.getCursoAcademico().hashCode()
				+ 31 * 31 * this.getConvocatoria().hashCode();
	}

	public int compareTo(Nota nt) {
		int res = this.getCursoAcademico().compareTo(nt.getCursoAcademico());
		if (res == 0) {
			res = getValor().compareTo(nt.getValor());
		}
		if (res == 0) {
			res = getConvocatoria().compareTo(nt.getConvocatoria());
		}
		return res;
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public Integer getCursoAcademico() {
		return curso;
	}

	public Convocatoria getConvocatoria() {
		return convocatoria;
	}

	public Double getValor() {
		return valor;
	}

	public Calificacion getCalificacion() {
		Calificacion res = null;
		if (getValor() < 5) {
			res = Calificacion.SUSPENSO;
		} else if (getValor() < 7) {
			res = Calificacion.APROBADO;
		} else if (getValor() < 9) {
			res = Calificacion.NOTABLE;
		} else if (!getMencionHonor()) {
			res = Calificacion.SOBRESALIENTE;
		} else {
			res = Calificacion.MATRICULA_DE_HONOR;
		}
		return res;
	}

	public Boolean getMencionHonor() {
		return mencionHonor;
	}

	public String toString() {
		String aux = "" + (getCursoAcademico() + 1);
		aux = aux.substring(aux.length() - 2);
		return getAsignatura() + ", " + getCursoAcademico() + "-" + aux + ", " + getConvocatoria() + ", " + getValor()
				+ ", " + getCalificacion();
	}
}
