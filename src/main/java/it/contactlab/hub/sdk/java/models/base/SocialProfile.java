
package it.contactlab.hub.sdk.java.models.base;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * Social profile
 * 
 */
public class SocialProfile {

    /**
     * facebook
     * 
     */
    @SerializedName("facebook")
    @Expose
    private String facebook;
    /**
     * google+
     * 
     */
    @SerializedName("google")
    @Expose
    private String google;
    /**
     * instagram
     * 
     */
    @SerializedName("instagram")
    @Expose
    private String instagram;
    /**
     * linkedin
     * 
     */
    @SerializedName("linkedin")
    @Expose
    private String linkedin;
    /**
     * qzone
     * 
     */
    @SerializedName("qzone")
    @Expose
    private String qzone;
    /**
     * twitter
     * 
     */
    @SerializedName("twitter")
    @Expose
    private String twitter;

    /**
     * facebook
     * 
     * @return
     *     The facebook
     */
    public String getFacebook() {
        return facebook;
    }

    /**
     * facebook
     * 
     * @param facebook
     *     The facebook
     */
    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    /**
     * google+
     * 
     * @return
     *     The google
     */
    public String getGoogle() {
        return google;
    }

    /**
     * google+
     * 
     * @param google
     *     The google
     */
    public void setGoogle(String google) {
        this.google = google;
    }

    /**
     * instagram
     * 
     * @return
     *     The instagram
     */
    public String getInstagram() {
        return instagram;
    }

    /**
     * instagram
     * 
     * @param instagram
     *     The instagram
     */
    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    /**
     * linkedin
     * 
     * @return
     *     The linkedin
     */
    public String getLinkedin() {
        return linkedin;
    }

    /**
     * linkedin
     * 
     * @param linkedin
     *     The linkedin
     */
    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    /**
     * qzone
     * 
     * @return
     *     The qzone
     */
    public String getQzone() {
        return qzone;
    }

    /**
     * qzone
     * 
     * @param qzone
     *     The qzone
     */
    public void setQzone(String qzone) {
        this.qzone = qzone;
    }

    /**
     * twitter
     * 
     * @return
     *     The twitter
     */
    public String getTwitter() {
        return twitter;
    }

    /**
     * twitter
     * 
     * @param twitter
     *     The twitter
     */
    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(facebook).append(google).append(instagram).append(linkedin).append(qzone).append(twitter).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SocialProfile) == false) {
            return false;
        }
        SocialProfile rhs = ((SocialProfile) other);
        return new EqualsBuilder().append(facebook, rhs.facebook).append(google, rhs.google).append(instagram, rhs.instagram).append(linkedin, rhs.linkedin).append(qzone, rhs.qzone).append(twitter, rhs.twitter).isEquals();
    }

}
