 /* Automatically generated from Swagger definitions */
 /* DO NOT EDIT MANUALLY */

package com.contactlab.hub.models;

import java.util.List;
import java.util.Map;

public class PageLink {

  public PageLink(Link previous, Link last, Link next, Link self, Link first) {
    this.previous = previous;
    this.last = last;
    this.next = next;
    this.self = self;
    this.first = first;
  }

  /**
   * previous page link
   */
  public final Link previous;

  /**
   * last page link
   */
  public final Link last;

  /**
   * next page link
   */
  public final Link next;

  /**
   * self page link
   */
  public final Link self;

  /**
   * first page link
   */
  public final Link first;

  public String toString() {
    return "PageLink(" + previous + last + next + self + first + ")";
  }

}
