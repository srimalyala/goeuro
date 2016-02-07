package model;

/**
 * A model class to hold city information
 *
 * @author srikanth.
 */
public class CityInfo {

    private int _id;
    private String name;
    private String fullName;
    private String iata_airposrt_code;
    private String type;
    private String country;
    private GeoPosition geo_position;
    private String location_id;
    private String inEurope;

    private String countryCode;
    private String coreCountry;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    private String distance;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getIata_airposrt_code() {
        return iata_airposrt_code;
    }

    public void setIata_airposrt_code(String iata_airposrt_code) {
        this.iata_airposrt_code = iata_airposrt_code;
    }


    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }

    public String getInEurope() {
        return inEurope;
    }

    public void setInEurope(String inEurope) {
        this.inEurope = inEurope;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCoreCountry() {
        return coreCountry;
    }

    public void setCoreCountry(String coreCountry) {
        this.coreCountry = coreCountry;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public GeoPosition getGeo_position() {
        return geo_position;
    }

    public void setGeo_position(GeoPosition geo_position) {
        this.geo_position = geo_position;
    }
}