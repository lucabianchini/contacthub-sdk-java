package com.contactlab.hub;

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
   */
  public Auth(String token, String workspaceId, String nodeId) {
    this.token = token;
    this.workspaceId = workspaceId;
    this.nodeId = nodeId;
  }

}
