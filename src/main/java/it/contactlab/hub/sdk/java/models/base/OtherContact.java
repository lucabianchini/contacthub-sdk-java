
package it.contactlab.hub.sdk.java.models.base;

import java.util.HashMap;
import java.util.Map;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class OtherContact {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private OtherContact.Type type;
    @SerializedName("value")
    @Expose
    private String value;

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The type
     */
    public OtherContact.Type getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(OtherContact.Type type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The value
     */
    public String getValue() {
        return value;
    }

    /**
     * 
     * @param value
     *     The value
     */
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(name).append(type).append(value).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof OtherContact) == false) {
            return false;
        }
        OtherContact rhs = ((OtherContact) other);
        return new EqualsBuilder().append(name, rhs.name).append(type, rhs.type).append(value, rhs.value).isEquals();
    }

    public enum Type {

        @SerializedName("MOBILE")
        MOBILE("MOBILE"),
        @SerializedName("PHONE")
        PHONE("PHONE"),
        @SerializedName("EMAIL")
        EMAIL("EMAIL"),
        @SerializedName("FAX")
        FAX("FAX"),
        @SerializedName("OTHER")
        OTHER("OTHER");
        private final String value;
        private final static Map<String, OtherContact.Type> CONSTANTS = new HashMap<String, OtherContact.Type>();

        static {
            for (OtherContact.Type c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private Type(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public String value() {
            return this.value;
        }

        public static OtherContact.Type fromValue(String value) {
            OtherContact.Type constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
