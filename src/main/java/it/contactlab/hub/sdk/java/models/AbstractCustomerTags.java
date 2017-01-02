package it.contactlab.hub.sdk.java.models;

import org.immutables.value.Value;

import java.util.Optional;
import java.util.Set;

/**
 * Customer Tags.
 */
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractCustomerTags {

  /**
  * A {@link Set} of tags that were assigned automatically.
  */
  public abstract Optional<Set<String>> auto();

  /**
  * A {@link Set} of tags that were assigned manually.
  */
  public abstract Optional<Set<String>> manual();
}
