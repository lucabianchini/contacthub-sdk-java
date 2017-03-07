package it.contactlab.hub.sdk.java.exceptions;

public class ServerException extends ContactHubException {

  private final int statusCode;
  private final String responseBody;

  /**
   * ContactHubException is thrown when the Contacthub API returns an unexpected
   * response (50x or 40x with a non-JSON body).
   */
  public ServerException(int statusCode, String responseBody) {
    super("ContactHub API returned an unexpected response:" + statusCode + " " + responseBody);

    this.statusCode = statusCode;
    this.responseBody = responseBody;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public String getResponseBody() {
    return responseBody;
  }
}
