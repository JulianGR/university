package fp.conversores;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.databind.util.StdConverter;

public class ConverterString2LocalDateTime extends StdConverter<String, LocalDateTime> {

	public LocalDateTime convert(String value) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		return LocalDateTime.parse(value, formatter);
	}
}
