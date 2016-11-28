package it.contactlab.hub.sdk.java.models;

import org.immutables.gson.Gson;
import org.immutables.value.Value;

import java.util.List;
import java.util.Optional;

/**
 * Customer.
 */
@Gson.TypeAdapters
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractCustomerTags {
  public abstract Optional<List<String>> auto();

  public abstract Optional<List<String>> manual();
}
