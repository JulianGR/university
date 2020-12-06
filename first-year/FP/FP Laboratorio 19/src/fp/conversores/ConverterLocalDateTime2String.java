package fp.conversores;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.databind.util.StdConverter;

public class ConverterLocalDateTime2String extends StdConverter<LocalDateTime, String> {

	public String convert(LocalDateTime value) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		return formatter.format(value);
	}

}
