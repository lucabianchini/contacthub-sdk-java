package it.contactlab.hub.sdk.java.exceptions;

public class ContactHubException extends BaseException {

  private int statusCode;
  private String responseBody;

  /**
   * ContactHubException is thrown when the Contacthub API returns an error.
   */
  public ContactHubException(int statusCode, String responseBody) {
    super("ContactHub API returned " + statusCode + " " + responseBody);

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
