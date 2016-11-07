
package it.contactlab.hub.sdk.java.models.base;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * the address information
 * 
 */
public class Address {

    /**
     * the street
     * 
     */
    @SerializedName("street")
    @Expose
    private String street;
    /**
     * the city
     * 
     */
    @SerializedName("city")
    @Expose
    private String city;
    /**
     * the country
     * 
     */
    @SerializedName("country")
    @Expose
    private String country;
    /**
     * the province
     * 
     */
    @SerializedName("province")
    @Expose
    private String province;
    /**
     * the zip code
     * 
     */
    @SerializedName("zip")
    @Expose
    private String zip;
    /**
     * the geographic localization
     * 
     */
    @SerializedName("geo")
    @Expose
    private Geo geo;

    /**
     * the street
     * 
     * @return
     *     The street
     */
    public String getStreet() {
        return street;
    }

    /**
     * the street
     * 
     * @param street
     *     The street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * the city
     * 
     * @return
     *     The city
     */
    public String getCity() {
        return city;
    }

    /**
     * the city
     * 
     * @param city
     *     The city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * the country
     * 
     * @return
     *     The country
     */
    public String getCountry() {
        return country;
    }

    /**
     * the country
     * 
     * @param country
     *     The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * the province
     * 
     * @return
     *     The province
     */
    public String getProvince() {
        return province;
    }

    /**
     * the province
     * 
     * @param province
     *     The province
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * the zip code
     * 
     * @return
     *     The zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * the zip code
     * 
     * @param zip
     *     The zip
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * the geographic localization
     * 
     * @return
     *     The geo
     */
    public Geo getGeo() {
        return geo;
    }

    /**
     * the geographic localization
     * 
     * @param geo
     *     The geo
     */
    public void setGeo(Geo geo) {
        this.geo = geo;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(street).append(city).append(country).append(province).append(zip).append(geo).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Address) == false) {
            return false;
        }
        Address rhs = ((Address) other);
        return new EqualsBuilder().append(street, rhs.street).append(city, rhs.city).append(country, rhs.country).append(province, rhs.province).append(zip, rhs.zip).append(geo, rhs.geo).isEquals();
    }

}
