package it.contactlab.hub.sdk.java.models;

import org.immutables.gson.Gson;
import org.immutables.value.Value;

import java.util.List;
import java.util.Optional;

/**
 * Customer Tags.
 */
@Gson.TypeAdapters
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractCustomerTags {

  /**
  * A {@link List} of tags that were assigned automatically.
  */
  public abstract Optional<List<String>> auto();

  /**
  * A {@link List} of tags that were assigned manually.
  */
  public abstract Optional<List<String>> manual();
}
