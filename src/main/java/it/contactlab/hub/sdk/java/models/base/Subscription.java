
package it.contactlab.hub.sdk.java.models.base;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Subscription {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("kind")
    @Expose
    private Subscription.Kind kind;
    @SerializedName("subscribed")
    @Expose
    private Boolean subscribed;
    @SerializedName("dateStart")
    @Expose
    private OffsetDateTime dateStart;
    @SerializedName("dateEnd")
    @Expose
    private OffsetDateTime dateEnd;
    @SerializedName("subscriberId")
    @Expose
    private String subscriberId;
    @SerializedName("registeredAt")
    @Expose
    private OffsetDateTime registeredAt;
    @SerializedName("updatedAt")
    @Expose
    private OffsetDateTime updatedAt;
    @SerializedName("preferences")
    @Expose
    private List<Preference> preferences = new ArrayList<Preference>();

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
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
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The kind
     */
    public Subscription.Kind getKind() {
        return kind;
    }

    /**
     * 
     * @param kind
     *     The kind
     */
    public void setKind(Subscription.Kind kind) {
        this.kind = kind;
    }

    /**
     * 
     * @return
     *     The subscribed
     */
    public Boolean getSubscribed() {
        return subscribed;
    }

    /**
     * 
     * @param subscribed
     *     The subscribed
     */
    public void setSubscribed(Boolean subscribed) {
        this.subscribed = subscribed;
    }

    /**
     * 
     * @return
     *     The dateStart
     */
    public OffsetDateTime getDateStart() {
        return dateStart;
    }

    /**
     * 
     * @param dateStart
     *     The dateStart
     */
    public void setDateStart(OffsetDateTime dateStart) {
        this.dateStart = dateStart;
    }

    /**
     * 
     * @return
     *     The dateEnd
     */
    public OffsetDateTime getDateEnd() {
        return dateEnd;
    }

    /**
     * 
     * @param dateEnd
     *     The dateEnd
     */
    public void setDateEnd(OffsetDateTime dateEnd) {
        this.dateEnd = dateEnd;
    }

    /**
     * 
     * @return
     *     The subscriberId
     */
    public String getSubscriberId() {
        return subscriberId;
    }

    /**
     * 
     * @param subscriberId
     *     The subscriberId
     */
    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    /**
     * 
     * @return
     *     The registeredAt
     */
    public OffsetDateTime getRegisteredAt() {
        return registeredAt;
    }

    /**
     * 
     * @param registeredAt
     *     The registeredAt
     */
    public void setRegisteredAt(OffsetDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    /**
     * 
     * @return
     *     The updatedAt
     */
    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 
     * @param updatedAt
     *     The updatedAt
     */
    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * 
     * @return
     *     The preferences
     */
    public List<Preference> getPreferences() {
        return preferences;
    }

    /**
     * 
     * @param preferences
     *     The preferences
     */
    public void setPreferences(List<Preference> preferences) {
        this.preferences = preferences;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(name).append(type).append(kind).append(subscribed).append(dateStart).append(dateEnd).append(subscriberId).append(registeredAt).append(updatedAt).append(preferences).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Subscription) == false) {
            return false;
        }
        Subscription rhs = ((Subscription) other);
        return new EqualsBuilder().append(id, rhs.id).append(name, rhs.name).append(type, rhs.type).append(kind, rhs.kind).append(subscribed, rhs.subscribed).append(dateStart, rhs.dateStart).append(dateEnd, rhs.dateEnd).append(subscriberId, rhs.subscriberId).append(registeredAt, rhs.registeredAt).append(updatedAt, rhs.updatedAt).append(preferences, rhs.preferences).isEquals();
    }

    public enum Kind {

        @SerializedName("DIGITAL_MESSAGE")
        DIGITAL_MESSAGE("DIGITAL_MESSAGE"),
        @SerializedName("SERVICE")
        SERVICE("SERVICE"),
        @SerializedName("OTHER")
        OTHER("OTHER");
        private final String value;
        private final static Map<String, Subscription.Kind> CONSTANTS = new HashMap<String, Subscription.Kind>();

        static {
            for (Subscription.Kind c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private Kind(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public String value() {
            return this.value;
        }

        public static Subscription.Kind fromValue(String value) {
            Subscription.Kind constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
