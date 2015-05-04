package api.document;

import org.codehaus.jackson.annotate.JsonProperty;

public class GeoPosition {
	@JsonProperty("latitude")
	private double latitude;
	@JsonProperty("longitude")
	private double longitude;

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
