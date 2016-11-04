
package it.contactlab.hub.sdk.java.models.base;

import java.util.HashMap;
import java.util.Map;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class MobileDevice {

    @SerializedName("identifier")
    @Expose
    private String identifier;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private MobileDevice.Type type;

    /**
     * 
     * @return
     *     The identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * 
     * @param identifier
     *     The identifier
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

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
    public MobileDevice.Type getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(MobileDevice.Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(identifier).append(name).append(type).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof MobileDevice) == false) {
            return false;
        }
        MobileDevice rhs = ((MobileDevice) other);
        return new EqualsBuilder().append(identifier, rhs.identifier).append(name, rhs.name).append(type, rhs.type).isEquals();
    }

    public enum Type {

        @SerializedName("IOS")
        IOS("IOS"),
        @SerializedName("GCM")
        GCM("GCM"),
        @SerializedName("WP")
        WP("WP");
        private final String value;
        private final static Map<String, MobileDevice.Type> CONSTANTS = new HashMap<String, MobileDevice.Type>();

        static {
            for (MobileDevice.Type c: values()) {
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

        public static MobileDevice.Type fromValue(String value) {
            MobileDevice.Type constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
