package it.contactlab.hub.sdk.java.models;

import org.immutables.value.Value;

import java.util.Optional;

@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractApiError {

  public abstract String message();

  public abstract String path();

  public abstract Optional<Object> data();

}
