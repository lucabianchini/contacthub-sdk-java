package it.contactlab.hub.sdk.java;

/**
 * ContactHub Authentication params.
 *
 */
public class Auth {

  private static final String DEFAULT_API_URL = "https://api.contactlab.it/hub/v1";

  public final String token;
  public final String workspaceId;
  public final String nodeId;
  public final String apiUrl;

  /**
   * Creates a new Auth instance.
   *
   * @param token       The authorization token.
   * @param workspaceId The workspace id.
   * @param nodeId      The node id.
   */
  public Auth(
      String token,
      String workspaceId,
      String nodeId
  ) {
    this(token, workspaceId, nodeId, DEFAULT_API_URL);
  }

  /**
   * Creates a new Auth instance with a custom API url.
   *
   * @param token       The authorization token.
   * @param workspaceId The workspace id.
   * @param nodeId      The node id.
   * @param apiUrl      The API url.
   */
  public Auth(
      String token,
      String workspaceId,
      String nodeId,
      String apiUrl
  ) {
    this.token = token;
    this.workspaceId = workspaceId;
    this.nodeId = nodeId;
    this.apiUrl = apiUrl;
  }

}
