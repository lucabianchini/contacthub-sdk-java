
package it.contactlab.hub.sdk.java.models.base;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * the contact information
 * 
 */
public class Contacts {

    /**
     * the e-mail
     * 
     */
    @SerializedName("email")
    @Expose
    private String email;
    /**
     * the fax number
     * 
     */
    @SerializedName("fax")
    @Expose
    private String fax;
    /**
     * the mobile phone number
     * 
     */
    @SerializedName("mobilePhone")
    @Expose
    private String mobilePhone;
    /**
     * the phone
     * 
     */
    @SerializedName("phone")
    @Expose
    private String phone;
    /**
     * other contacts
     * 
     */
    @SerializedName("otherContacts")
    @Expose
    private List<OtherContact> otherContacts = new ArrayList<OtherContact>();
    /**
     * mobile device
     * 
     */
    @SerializedName("mobileDevices")
    @Expose
    private List<MobileDevice> mobileDevices = new ArrayList<MobileDevice>();

    /**
     * the e-mail
     * 
     * @return
     *     The email
     */
    public String getEmail() {
        return email;
    }

    /**
     * the e-mail
     * 
     * @param email
     *     The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * the fax number
     * 
     * @return
     *     The fax
     */
    public String getFax() {
        return fax;
    }

    /**
     * the fax number
     * 
     * @param fax
     *     The fax
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * the mobile phone number
     * 
     * @return
     *     The mobilePhone
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * the mobile phone number
     * 
     * @param mobilePhone
     *     The mobilePhone
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     * the phone
     * 
     * @return
     *     The phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * the phone
     * 
     * @param phone
     *     The phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * other contacts
     * 
     * @return
     *     The otherContacts
     */
    public List<OtherContact> getOtherContacts() {
        return otherContacts;
    }

    /**
     * other contacts
     * 
     * @param otherContacts
     *     The otherContacts
     */
    public void setOtherContacts(List<OtherContact> otherContacts) {
        this.otherContacts = otherContacts;
    }

    /**
     * mobile device
     * 
     * @return
     *     The mobileDevices
     */
    public List<MobileDevice> getMobileDevices() {
        return mobileDevices;
    }

    /**
     * mobile device
     * 
     * @param mobileDevices
     *     The mobileDevices
     */
    public void setMobileDevices(List<MobileDevice> mobileDevices) {
        this.mobileDevices = mobileDevices;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(email).append(fax).append(mobilePhone).append(phone).append(otherContacts).append(mobileDevices).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Contacts) == false) {
            return false;
        }
        Contacts rhs = ((Contacts) other);
        return new EqualsBuilder().append(email, rhs.email).append(fax, rhs.fax).append(mobilePhone, rhs.mobilePhone).append(phone, rhs.phone).append(otherContacts, rhs.otherContacts).append(mobileDevices, rhs.mobileDevices).isEquals();
    }

}
