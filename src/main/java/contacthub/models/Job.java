 /* Automatically generated from Swagger definitions */
 /* DO NOT EDIT MANUALLY */

package com.contactlab.hub.models;

import java.util.List;
import java.util.Map;

public class Job {

  public Job(String jobTitle, String companyIndustry, String id, String companyName, String endDate, Boolean isCurrent, String startDate) {
    this.jobTitle = jobTitle;
    this.companyIndustry = companyIndustry;
    this.id = id;
    this.companyName = companyName;
    this.endDate = endDate;
    this.isCurrent = isCurrent;
    this.startDate = startDate;
  }

  /**
   * The job title
   */
  public final String jobTitle;

  /**
   * The name of company industry
   */
  public final String companyIndustry;

  /**
   * unique identifier of job
   */
  public final String id;

  /**
   * The name of company
   */
  public final String companyName;

  /**
   * job end date
   */
  public final String endDate;

  /**
   * TODO
   */
  public final Boolean isCurrent;

  /**
   * job start date
   */
  public final String startDate;

  public String toString() {
    return "Job(" + jobTitle + companyIndustry + id + companyName + endDate + isCurrent + startDate + ")";
  }

}
