package com.contactlab.hub;

/**
 * Example use of the SDK.
 */
public class Main {

  /**
   * The main method.
   */
  public static void main(String[] args) {
    Node node = new Node(
        "854f0791-c120-4e4a-9264-6dd197cb922c",
        "40b6195f-e4f7-4f95-b10e-75268d850988"
    );
    await(node.getCustomers().whenComplete((cc, ex) -> System.out.println(cc)));
  }

  private static <T> T await(java.util.concurrent.CompletionStage<T> stage) {
    try {
      return stage.toCompletableFuture().get();
    } catch (Exception exception) {
      return null;
    }
  }

}
