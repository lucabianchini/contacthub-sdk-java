 /* Automatically generated from Swagger definitions */
 /* DO NOT EDIT MANUALLY */

package com.contactlab.contacthub.models;

import java.util.List;
import java.util.Map;

public class TrendValue {

  public TrendValue(String time, Integer isNew, Integer total, Integer lost) {
    this.time = time;
    this.isNew = isNew;
    this.total = total;
    this.lost = lost;
  }

  /**
   * Date referral to mode defined in request
   */
  public final String time;

  /**
   * Number of new customers
   */
  public final Integer isNew;

  /**
   * Number of total customer in the node
   */
  public final Integer total;

  /**
   * Number of lost customers
   */
  public final Integer lost;

  public String toString() {
    return "TrendValue(" + time + isNew + total + lost + ")";
  }

}
