package it.contactlab.hub.sdk.java.api;

import it.contactlab.hub.sdk.java.Auth;
import it.contactlab.hub.sdk.java.exceptions.HttpException;
import it.contactlab.hub.sdk.java.models.Customer;
import it.contactlab.hub.sdk.java.models.CustomerTags;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class TagApi {

  /**
   * Add a new tag to a Customer.
   */
  public static Customer add(Auth auth, String customerId, String tag)
      throws HttpException {

    Customer customer = CustomerApi.get(auth, customerId);
    Set<String> manualTags = customer.tags().flatMap(t -> t.manual()).orElse(new HashSet());

    boolean changed = manualTags.add(tag);

    if (changed) {
      return CustomerApi.patch(auth, customerId, Customer.builder()
          .tags(CustomerTags.builder().manual(manualTags).build())
          .build());
    } else {
      return customer;
    }
  }

  /**
   * Remove a tag from a Customer.
   */
  public static Customer remove(Auth auth, String customerId, String tag)
      throws HttpException {

    Customer customer = CustomerApi.get(auth, customerId);
    Set<String> manualTags = customer.tags().flatMap(t -> t.manual()).orElse(new HashSet());

    boolean changed = manualTags.remove(tag);

    if (changed) {
      return CustomerApi.patch(auth, customerId, Customer.builder()
          .tags(CustomerTags.builder().manual(manualTags).build())
          .build());
    } else {
      return customer;
    }
  }

}
