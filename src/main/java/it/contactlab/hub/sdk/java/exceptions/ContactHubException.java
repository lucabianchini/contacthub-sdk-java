package it.contactlab.hub.sdk.java.exceptions;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class ContactHubException extends ServerException {

  private final String errorMessage;

  /**
   * ContactHubException is thrown when the Contacthub API returns an error with
   * a JSON-serialized error message.
   */
  public ContactHubException(int statusCode, String responseBody)
      throws ServerException {
    super(statusCode, responseBody);

    try {
      JsonParser parser = new JsonParser();
      JsonElement jsonResponse = parser.parse(responseBody);

      if (!jsonResponse.isJsonObject()) {
        throw new ServerException(statusCode, responseBody);
      }

      this.errorMessage = jsonResponse.getAsJsonObject()
                                      .get("message").getAsString();
    } catch (JsonSyntaxException jsonException) {
      throw new ServerException(statusCode, responseBody);
    }
  }

  public String getErrorMessage() {
    return errorMessage;
  }
}
