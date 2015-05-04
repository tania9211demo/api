package api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import api.document.CityEntry;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class JerseyClient {
	private static String path = "http://www.goeuro.com/GoEuroAPI/rest/api/v2/position/suggest/en/";
	private Logger logger = LoggerFactory.getLogger(JerseyClient.class);

	@Consumes({MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public List<CityEntry> getResource(String url) {
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,
				Boolean.TRUE);
		clientConfig.getClasses().add(JacksonJsonProvider.class);
		
		Client client = Client.create(clientConfig);
		try {
			url = URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("Error when encoding input parameter", e);
		}

		final WebResource webResource = client.resource(path + url);

		List<CityEntry> pojoList = webResource.accept(
				MediaType.APPLICATION_JSON).header("content-type", MediaType.APPLICATION_JSON).get(
				new GenericType<List<CityEntry>>() {
				});

		return pojoList;
	}
}
