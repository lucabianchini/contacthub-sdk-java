
package it.contactlab.hub.sdk.java.models.base;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Job {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("companyIndustry")
    @Expose
    private String companyIndustry;
    @SerializedName("companyName")
    @Expose
    private String companyName;
    @SerializedName("jobTitle")
    @Expose
    private String jobTitle;
    @SerializedName("startDate")
    @Expose
    private String startDate;
    @SerializedName("endDate")
    @Expose
    private String endDate;
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
     *     The companyIndustry
     */
    public String getCompanyIndustry() {
        return companyIndustry;
    }

    /**
     * 
     * @param companyIndustry
     *     The companyIndustry
     */
    public void setCompanyIndustry(String companyIndustry) {
        this.companyIndustry = companyIndustry;
    }

    /**
     * 
     * @return
     *     The companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 
     * @param companyName
     *     The companyName
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * 
     * @return
     *     The jobTitle
     */
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * 
     * @param jobTitle
     *     The jobTitle
     */
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    /**
     * 
     * @return
     *     The startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * 
     * @param startDate
     *     The startDate
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * 
     * @return
     *     The endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * 
     * @param endDate
     *     The endDate
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
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
        return new HashCodeBuilder().append(id).append(companyIndustry).append(companyName).append(jobTitle).append(startDate).append(endDate).append(isCurrent).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Job) == false) {
            return false;
        }
        Job rhs = ((Job) other);
        return new EqualsBuilder().append(id, rhs.id).append(companyIndustry, rhs.companyIndustry).append(companyName, rhs.companyName).append(jobTitle, rhs.jobTitle).append(startDate, rhs.startDate).append(endDate, rhs.endDate).append(isCurrent, rhs.isCurrent).isEquals();
    }

}
