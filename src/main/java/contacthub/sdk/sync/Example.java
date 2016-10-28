package com.contactlab.hub.sdk;

import com.contactlab.hub.Auth;
import com.contactlab.hub.models.Customer;
import com.contactlab.hub.sdk.sync.ContactHub;

import java.util.List;

/**
 * Example use of the Async SDK.
 */
public class Example {

  /**
   * The main method.
   */
  public static void main(String[] args) {
    Auth auth = new Auth(args[0], args[1], args[2]);

    ContactHub ch = new ContactHub(auth);

    List<Customer> cc = ch.getCustomers();
    System.out.println(cc);
  }

}
