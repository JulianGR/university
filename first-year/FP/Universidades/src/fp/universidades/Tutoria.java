package fp.universidades;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;

public interface Tutoria{
	LocalTime getHoraComienzo();
	LocalTime getHoraFin();
	Duration getDuracion(); 
	DayOfWeek getDiaSemana();
}
