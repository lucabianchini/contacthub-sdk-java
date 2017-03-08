package it.contactlab.hub.sdk.java.models;

import org.immutables.value.Value;

import java.util.List;
import java.util.Optional;

@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractApiErrorResponse {

  public abstract String message();

  public abstract String logref();

  public abstract List<ApiError> errors();

  public abstract Optional<String> data();

}
