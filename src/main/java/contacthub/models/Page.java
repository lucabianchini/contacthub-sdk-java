 /* Automatically generated from Swagger definitions */
 /* DO NOT EDIT MANUALLY */

package com.contactlab.hub.models;

import java.util.List;
import java.util.Map;

public class Page {

  public Page(Integer size, Integer number, Integer totalElements, Integer totalPages) {
    this.size = size;
    this.number = number;
    this.totalElements = totalElements;
    this.totalPages = totalPages;
  }

  /**
   * the requested size of the page
   */
  public final Integer size;

  /**
   * the number of the current page
   */
  public final Integer number;

  /**
   * total number of elements available
   */
  public final Integer totalElements;

  /**
   * how many pages are available in total
   */
  public final Integer totalPages;

  public String toString() {
    return "Page(" + size + number + totalElements + totalPages + ")";
  }

}
