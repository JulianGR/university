package fp.universidades;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;

import fp.utiles.Checkers;

public class TutoriaImpl implements Tutoria {
	private LocalTime horaComienzo;
	private LocalTime horaFin;
	private DayOfWeek dia;

	public TutoriaImpl(DayOfWeek dia, LocalTime horaInicio, LocalTime horaFin) {
		Checkers.checkNoNull(dia, horaInicio, horaFin);
		Checkers.check(R_HORA_COMIENZO, restriccionHoraComienzo(horaInicio, horaFin));
		Checkers.check(R_HORA_FIN, restriccionHoraFin(horaInicio, horaFin));
		Checkers.check(R_DIA_SEMANA, restriccionDiaSemana(dia));
		this.horaComienzo = horaInicio;
		this.horaFin = horaFin;
		this.dia = dia;
	}

	public TutoriaImpl(DayOfWeek dia, LocalTime horaInicio, Duration duracion) {
		this.horaComienzo = horaInicio;
		this.horaFin = horaInicio.plusMinutes(duracion.toMinutes());
		this.dia = dia;

	}

	private static final String R_HORA_COMIENZO = "La hora de inicio no puede ser posterior a la de fin ni anterior a las 8:30";
	private static final String R_HORA_FIN = "La hora de fin no puede ser anterior a la de inicio ni posterior a las 21:30";
	private static final String R_DIA_SEMANA = "El día de la semana no puede ser sábado ni domingo";

	// Devuelve true si la hora es antes que la hora de fin y después de las 8:30
	private static Boolean restriccionHoraComienzo(LocalTime horaComienzo, LocalTime horaFin) {
		return horaComienzo.isBefore(horaFin) && horaComienzo.isAfter(LocalTime.of(8, 30));
	}

	private static Boolean restriccionHoraFin(LocalTime horaComienzo, LocalTime horaFin) {
		return horaFin.isAfter(horaComienzo) && horaFin.isBefore(LocalTime.of(21, 30));
	}

	private static Boolean restriccionDiaSemana(DayOfWeek dia) {

		return dia.equals(DayOfWeek.MONDAY) || dia.equals(DayOfWeek.TUESDAY) || dia.equals(DayOfWeek.WEDNESDAY)
				|| dia.equals(DayOfWeek.THURSDAY) || dia.equals(DayOfWeek.FRIDAY);
	}

	public boolean equals(Object o) {
		boolean res = false;
		if (o instanceof Tutoria) {
			Tutoria t = (Tutoria) o;
			{
				res = this.getHoraComienzo().equals(t.getHoraComienzo())
						&& this.getDiaSemana().equals(t.getDiaSemana());
			}
		}
		return res;
	}

	public int hashCode() {
		return this.getHoraComienzo().hashCode() + 31 * this.getDiaSemana().hashCode();
	}

	public int compareTo(Tutoria t) {
		int res = this.getHoraComienzo().compareTo(t.getHoraComienzo());
		if (res == 0) {
			res = getDiaSemana().compareTo(t.getDiaSemana());
		}

		return res;
	}

	public LocalTime getHoraComienzo() {
		return horaComienzo;
	}

	public LocalTime getHoraFin() {
		return horaFin;
	}

	public Duration getDuracion() {
		return Duration.between(horaComienzo, horaFin);
	}

	public DayOfWeek getDiaSemana() {
		return dia;
	}

	public String toString() {
		return getDiaCharacter() + " " + getHoraComienzo() + "-" + getHoraFin();
	}

	private Character getDiaCharacter() {
		Character res = null;

		switch (getDiaSemana()) {
		case MONDAY:
			res = 'L';
			break;
		case TUESDAY:
			res = 'M';
			break;
		case WEDNESDAY:
			res = 'X';
			break;
		case THURSDAY:
			res = 'J';
			break;
		case FRIDAY:
			res = 'V';
			break;
		default:
			res = '?';
		}

		return res;
	}
}
