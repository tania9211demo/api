package api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import api.document.CityEntry;

/**
 * Hello world!
 *
 */
public class App {
	private final static Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		if (args == null || args.length < 1 || args[0] == null || args[0].equals("")) {
			logger.info("You should specify parameter STRING");
			return;
		}

		final JerseyClient jerseyClient = new JerseyClient();
		logger.debug("Get json from url");
		List<CityEntry> entryList = jerseyClient.getResource(args[0]);
		final CsvConverter converter = new CsvConverter();
		logger.debug("Convert object to csv");
		final String path = System.getProperty("user.dir");
		converter.convert(entryList, path);

		logger.debug("JSON has been converted to CSV file");
	}
}
