 /* Automatically generated from Swagger definitions */
 /* DO NOT EDIT MANUALLY */

package com.contactlab.hub.models;

import java.util.List;
import java.util.Map;

public class Education {

  public Education(Integer startYear, String schoolName, String schoolConcentration, String id, Integer endYear, String schoolType, Boolean isCurrent) {
    this.startYear = startYear;
    this.schoolName = schoolName;
    this.schoolConcentration = schoolConcentration;
    this.id = id;
    this.endYear = endYear;
    this.schoolType = schoolType;
    this.isCurrent = isCurrent;
  }

  /**
   * TODO
   */
  public final Integer startYear;

  /**
   * the name of the school
   */
  public final String schoolName;

  /**
   * TODO
   */
  public final String schoolConcentration;

  /**
   * unique identifier of education
   */
  public final String id;

  /**
   * TODO
   */
  public final Integer endYear;

  /**
   * The name of school type
   */
  public final String schoolType;

  /**
   * TODO
   */
  public final Boolean isCurrent;

  public String toString() {
    return "Education(" + startYear + schoolName + schoolConcentration + id + endYear + schoolType + isCurrent + ")";
  }

}
