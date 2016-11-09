package it.contactlab.hub.sdk.java;

/**
 * ContactHub Authentication params.
 *
 */
public class Auth {

  public final String token;
  public final String workspaceId;
  public final String nodeId;

  /**
   * Creates a new Auth instance.
   *
   * @param token       The authorization token.
   * @param workspaceId The workspace id.
   * @param nodeId      The node id.
   */
  public Auth(String token, String workspaceId, String nodeId) {
    this.token = token;
    this.workspaceId = workspaceId;
    this.nodeId = nodeId;
  }

}
