
package it.contactlab.hub.sdk.java.models.base;

import java.util.HashMap;
import java.util.Map;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Education {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("schoolType")
    @Expose
    private Education.SchoolType schoolType;
    @SerializedName("schoolName")
    @Expose
    private String schoolName;
    @SerializedName("schoolConcentration")
    @Expose
    private String schoolConcentration;
    @SerializedName("startYear")
    @Expose
    private Integer startYear;
    @SerializedName("endYear")
    @Expose
    private Integer endYear;
    @SerializedName("isCurrent")
    @Expose
    private Boolean isCurrent;

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
     *     The schoolType
     */
    public Education.SchoolType getSchoolType() {
        return schoolType;
    }

    /**
     * 
     * @param schoolType
     *     The schoolType
     */
    public void setSchoolType(Education.SchoolType schoolType) {
        this.schoolType = schoolType;
    }

    /**
     * 
     * @return
     *     The schoolName
     */
    public String getSchoolName() {
        return schoolName;
    }

    /**
     * 
     * @param schoolName
     *     The schoolName
     */
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    /**
     * 
     * @return
     *     The schoolConcentration
     */
    public String getSchoolConcentration() {
        return schoolConcentration;
    }

    /**
     * 
     * @param schoolConcentration
     *     The schoolConcentration
     */
    public void setSchoolConcentration(String schoolConcentration) {
        this.schoolConcentration = schoolConcentration;
    }

    /**
     * 
     * @return
     *     The startYear
     */
    public Integer getStartYear() {
        return startYear;
    }

    /**
     * 
     * @param startYear
     *     The startYear
     */
    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    /**
     * 
     * @return
     *     The endYear
     */
    public Integer getEndYear() {
        return endYear;
    }

    /**
     * 
     * @param endYear
     *     The endYear
     */
    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    /**
     * 
     * @return
     *     The isCurrent
     */
    public Boolean getIsCurrent() {
        return isCurrent;
    }

    /**
     * 
     * @param isCurrent
     *     The isCurrent
     */
    public void setIsCurrent(Boolean isCurrent) {
        this.isCurrent = isCurrent;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(schoolType).append(schoolName).append(schoolConcentration).append(startYear).append(endYear).append(isCurrent).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Education) == false) {
            return false;
        }
        Education rhs = ((Education) other);
        return new EqualsBuilder().append(id, rhs.id).append(schoolType, rhs.schoolType).append(schoolName, rhs.schoolName).append(schoolConcentration, rhs.schoolConcentration).append(startYear, rhs.startYear).append(endYear, rhs.endYear).append(isCurrent, rhs.isCurrent).isEquals();
    }

    public enum SchoolType {

        @SerializedName("PRIMARY_SCHOOL")
        PRIMARY_SCHOOL("PRIMARY_SCHOOL"),
        @SerializedName("SECONDARY_SCHOOL")
        SECONDARY_SCHOOL("SECONDARY_SCHOOL"),
        @SerializedName("HIGH_SCHOOL")
        HIGH_SCHOOL("HIGH_SCHOOL"),
        @SerializedName("COLLEGE")
        COLLEGE("COLLEGE"),
        @SerializedName("OTHER")
        OTHER("OTHER");
        private final String value;
        private final static Map<String, Education.SchoolType> CONSTANTS = new HashMap<String, Education.SchoolType>();

        static {
            for (Education.SchoolType c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private SchoolType(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public String value() {
            return this.value;
        }

        public static Education.SchoolType fromValue(String value) {
            Education.SchoolType constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
