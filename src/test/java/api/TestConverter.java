package api;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import api.document.CityEntry;
import api.document.GeoPosition;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

/**
 * Unit test for simple App.
 */
public class TestConverter {

	@Test
	/**
	 * Put parameters with incorrect symbols.
	 * 
	 * @result test should run without exceptions.
	 */
	public void testInncorrectSymbols() {
		JerseyClient jerseyClient = new JerseyClient();
		List<CityEntry> entryList = jerseyClient.getResource("Germany}");
		assertNotNull(entryList);

		jerseyClient = new JerseyClient();
		entryList = jerseyClient.getResource("{Germany}^");
		assertNotNull(entryList);

		jerseyClient = new JerseyClient();
		entryList = jerseyClient.getResource("Germany|~");
		assertNotNull(entryList);
	}

	@Test
	/**
	 * Create object and write this object to csv. 
	 * Then check csv file.
	 * @result csv file should include object.
	 */
	public void testConvertCsv() throws JsonProcessingException, IOException {
		final String name = "Großräschen";
		final String type = "location";
		final CityEntry cityEntry = new CityEntry();
		cityEntry.set_id(345);
		cityEntry.set_type("");
		cityEntry.setName(name);
		cityEntry.setType(type);

		final GeoPosition geoPosition = new GeoPosition();
		geoPosition.setLatitude(50.7);
		geoPosition.setLongitude(11.6);

		cityEntry.setGeoPosition(geoPosition);
		final List<CityEntry> list = new ArrayList<CityEntry>();
		list.add(cityEntry);

		final CsvConverter converter = new CsvConverter();
		final String path = System.getProperty("user.dir");
		converter.convert(list, path);

		CsvSchema schema = CsvSchema.emptySchema().withHeader();
		CsvMapper csvMapper = new CsvMapper();
		csvMapper.disable(CsvParser.Feature.TRIM_SPACES);
		MappingIterator<CityEntry> mappingIterator = csvMapper
				.reader(CityEntry.class).with(schema)
				.readValues(path + "/" + "result.csv");
		while (mappingIterator.hasNext()) {
			CityEntry tempEntry = mappingIterator.next();
			assertTrue(type.equals(cityEntry.getType()));
			assertTrue(name.equals(tempEntry.getName()));
		}
	}
}
