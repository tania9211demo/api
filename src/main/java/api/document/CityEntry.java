package api.document;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(value = { "_type", "_id", "name", "type", "latitude",
		"longitude" })
@JsonIgnoreProperties(ignoreUnknown = true)
public class CityEntry {
	@JsonProperty("_type")
	private String _type;
	@JsonProperty("_id")
	private long _id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("type")
	private String type;
	@JsonIgnore
	@JsonProperty("geo_position")
	private GeoPosition geoPosition;
	@JsonProperty("latitude")
	private double latitude;
	@JsonProperty("longitude")
	private double longitude;

	public void setLatitude(double latitude) {
		this.geoPosition.setLatitude(latitude);
	}

	public void setLongitude(double longitude) {
		this.geoPosition.setLongitude(longitude);
		;
	}

	public double getLatitude() {
		return geoPosition.getLatitude();
	}

	public double getLongitude() {
		return geoPosition.getLongitude();
	}

	public String get_type() {
		return _type;
	}

	public void set_type(String _type) {
		this._type = _type;
	}

	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGeoPosition() {
		return geoPosition.getLatitude() + ";" + geoPosition.getLongitude();
	}

	public void setGeoPosition(GeoPosition geoPosition) {
		this.geoPosition = geoPosition;
	}
}
