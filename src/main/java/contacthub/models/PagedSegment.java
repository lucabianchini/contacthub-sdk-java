 /* Automatically generated from Swagger definitions */
 /* DO NOT EDIT MANUALLY */

package com.contactlab.contacthub.models;

import java.util.List;
import java.util.Map;

public class PagedSegment {

  public PagedSegment(Map<String, Object> _embedded, Page page, PageLink _links) {
    this._embedded = _embedded;
    this.page = page;
    this._links = _links;
  }

  /**
   * TODO
   */
  public final Map<String, Object> _embedded;

  /**
   * TODO
   */
  public final Page page;

  /**
   * TODO
   */
  public final PageLink _links;

  public String toString() {
    return "PagedSegment(" + _embedded + page + _links + ")";
  }

}
