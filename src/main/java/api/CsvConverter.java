package api;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import api.document.CityEntry;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class CsvConverter {
	private Logger logger = LoggerFactory.getLogger(CsvConverter.class);

	public void convert(List<CityEntry> entryList, String path) {
		logger.debug("File path : " + path);
		try {
			final CsvMapper mapper = new CsvMapper();

			CsvSchema schema = mapper.schemaFor(CityEntry.class).withHeader();
			schema = schema.withColumnSeparator(';');
			final ObjectWriter objectWriter = mapper.writer(schema);
			logger.debug(objectWriter.writeValueAsString(entryList));

			File tempFile = new File(path + "/" +"result.csv");
			FileOutputStream tempFileOutputStream = new FileOutputStream(
					tempFile);
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
					tempFileOutputStream, 1024);
			OutputStreamWriter writerOutputStream = new OutputStreamWriter(
					bufferedOutputStream, "UTF-8");
			objectWriter.writeValue(writerOutputStream, entryList);
		} catch (JsonProcessingException exception) {
			logger.error("Error when processing JSON", exception);
		} catch (IOException exception) {
			logger.error("Error when processing CSV file", exception);
		}
	}
}
