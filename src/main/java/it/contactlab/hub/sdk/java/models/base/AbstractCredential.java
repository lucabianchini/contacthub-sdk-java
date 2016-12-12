package it.contactlab.hub.sdk.java.models.base;

import org.immutables.gson.Gson;
import org.immutables.value.Value;

import java.util.Optional;

/**
 * Credentials.
 *
 */
@Gson.TypeAdapters
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractCredential {

  /**
   * The password.
   */
  public abstract String password();

  /**
   * The user name.
   */
  public abstract String username();

}
