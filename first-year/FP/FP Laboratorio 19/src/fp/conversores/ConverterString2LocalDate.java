package fp.conversores;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.databind.util.StdConverter;

public class ConverterString2LocalDate extends StdConverter<String, LocalDate> {

	public LocalDate convert(String value) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return LocalDate.parse(value, formatter);
	}
}
