package com.contactlab.hub.sdk.async;

import com.contactlab.hub.Auth;
import com.contactlab.hub.sdk.async.ContactHub;

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

    await(ch.getCustomers().whenComplete((cc, ex) -> System.out.println(cc)));
  }

  private static <T> T await(java.util.concurrent.CompletionStage<T> stage) {
    try {
      return stage.toCompletableFuture().get();
    } catch (Exception exception) {
      return null;
    }
  }

}
