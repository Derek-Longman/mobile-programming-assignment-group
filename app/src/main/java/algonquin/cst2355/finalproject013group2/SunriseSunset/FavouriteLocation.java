package algonquin.cst2355.finalproject013group2.SunriseSunset;

/**
 * Represents a favorite location with attributes related to various aspects of sunrise and sunset.
 * This class includes details such as time of sunrise, sunset, solar noon, and the length of the day.
 */
public class FavouriteLocation {
    /**
     * The unique identifier of the location.
     */
    private int id;

    /**
     * The latitude of the location.
     */
    private String latitude;

    /**
     * The longitude of the location.
     */
    private String longitude;

    /**
     * The date associated with the sunrise and sunset times.
     */
    private String date;

    /**
     * The time of sunrise at the location.
     */
    private String sunrise;

    /**
     * The time of sunset at the location.
     */
    private String sunset;

    /**
     * The time of first light in the morning.
     */
    private String firstLight;

    /**
     * The time of last light in the evening.
     */
    private String lastLight;

    /**
     * The time of dawn at the location.
     */
    private String dawn;

    /**
     * The time of dusk at the location.
     */
    private String dusk;

    /**
     * The time of solar noon at the location.
     */
    private String solarNoon;

    /**
     * The time of the golden hour, usually a period shortly after sunrise or before sunset.
     */
    private String goldenHour;

    /**
     * The length of the day from sunrise to sunset.
     */
    private String dayLength;

    /**
     * The timezone of the location.
     */
    private String timezone;

    /**
     * Gets the unique identifier of the location.
     *
     * @return The unique identifier of the location.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the location.
     *
     * @param id The unique identifier to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the latitude of the location.
     *
     * @return The latitude of the location.
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * Sets the latitude of the location.
     *
     * @param latitude The latitude to set.
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * Gets the longitude of the location.
     *
     * @return The longitude of the location.
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * Sets the longitude of the location.
     *
     * @param longitude The longitude to set.
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * Gets the date associated with the location.
     *
     * @return The date.
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date associated with the location.
     *
     * @param date The date to set.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets the time of sunrise at the location.
     *
     * @return The time of sunrise.
     */
    public String getSunrise() {
        return sunrise;
    }

    /**
     * Sets the time of sunrise at the location.
     *
     * @param sunrise The time of sunrise to set.
     */
    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    /**
     * Gets the time of sunset at the location.
     *
     * @return The time of sunset.
     */
    public String getSunset() {
        return sunset;
    }

    /**
     * Sets the time of sunset at the location.
     *
     * @param sunset The time of sunset to set.
     */
    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    /**
     * Gets the time of first light in the morning at the location.
     *
     * @return The time of first light.
     */
    public String getFirstLight() {
        return firstLight;
    }

    /**
     * Sets the time of first light in the morning at the location.
     *
     * @param firstLight The time of first light to set.
     */
    public void setFirstLight(String firstLight) {
        this.firstLight = firstLight;
    }

    /**
     * Gets the time of last light in the evening at the location.
     *
     * @return The time of last light.
     */
    public String getLastLight() {
        return lastLight;
    }

    /**
     * Sets the time of last light in the evening at the location.
     *
     * @param lastLight The time of last light to set.
     */
    public void setLastLight(String lastLight) {
        this.lastLight = lastLight;
    }

    /**
     * Gets the time of dawn at the location.
     *
     * @return The time of dawn.
     */
    public String getDawn() {
        return dawn;
    }

    /**
     * Sets the time of dawn at the location.
     *
     * @param dawn The time of dawn to set.
     */
    public void setDawn(String dawn) {
        this.dawn = dawn;
    }

    /**
     * Gets the time of dusk at the location.
     *
     * @return The time of dusk.
     */
    public String getDusk() {
        return dusk;
    }

    /**
     * Sets the time of dusk at the location.
     *
     * @param dusk The time of dusk to set.
     */
    public void setDusk(String dusk) {
        this.dusk = dusk;
    }

    /**
     * Gets the time of solar noon at the location.
     *
     * @return The time of solar noon.
     */
    public String getSolarNoon() {
        return solarNoon;
    }

    /**
     * Sets the time of solar noon at the location.
     *
     * @param solarNoon The time of solar noon to set.
     */
    public void setSolarNoon(String solarNoon) {
        this.solarNoon = solarNoon;
    }

    /**
     * Gets the time of the golden hour at the location.
     *
     * @return The time of the golden hour.
     */
    public String getGoldenHour() {
        return goldenHour;
    }

    /**
     * Sets the time of the golden hour at the location.
     *
     * @param goldenHour The time of the golden hour to set.
     */
    public void setGoldenHour(String goldenHour) {
        this.goldenHour = goldenHour;
    }

    /**
     * Gets the length of the day at the location, from sunrise to sunset.
     *
     * @return The length of the day.
     */
    public String getDayLength() {
        return dayLength;
    }

    /**
     * Sets the length of the day at the location, from sunrise to sunset.
     *
     * @param dayLength The day length to set.
     */
    public void setDayLength(String dayLength) {
        this.dayLength = dayLength;
    }

    /**
     * Gets the timezone of the location.
     *
     * @return The timezone of the location.
     */
    public String getTimezone() {
        return timezone;
    }

    /**
     * Sets the timezone of the location.
     *
     * @param timezone The timezone to set.
     */
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

}