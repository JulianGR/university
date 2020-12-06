package fp.conversores;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.databind.util.StdConverter;

public class ConverterLocalDate2String extends StdConverter<LocalDate, String> {

	public String convert(LocalDate value) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return formatter.format(value);
	}

}
