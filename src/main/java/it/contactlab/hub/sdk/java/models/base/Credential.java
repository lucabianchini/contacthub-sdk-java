
package it.contactlab.hub.sdk.java.models.base;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * credential
 * 
 */
public class Credential {

    /**
     * the password
     * 
     */
    @SerializedName("password")
    @Expose
    private String password;
    /**
     * the user name
     * 
     */
    @SerializedName("username")
    @Expose
    private String username;

    /**
     * the password
     * 
     * @return
     *     The password
     */
    public String getPassword() {
        return password;
    }

    /**
     * the password
     * 
     * @param password
     *     The password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * the user name
     * 
     * @return
     *     The username
     */
    public String getUsername() {
        return username;
    }

    /**
     * the user name
     * 
     * @param username
     *     The username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(password).append(username).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Credential) == false) {
            return false;
        }
        Credential rhs = ((Credential) other);
        return new EqualsBuilder().append(password, rhs.password).append(username, rhs.username).isEquals();
    }

}
