package it.contactlab.hub.sdk.java.models;

import org.immutables.value.Value;

import java.util.Optional;

/**
 * Credentials.
 *
 */
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractCredential {

  public abstract String username();

  public abstract String password();

}
