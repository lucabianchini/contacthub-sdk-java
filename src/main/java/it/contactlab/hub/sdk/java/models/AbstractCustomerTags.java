package it.contactlab.hub.sdk.java.models;

import org.immutables.value.Value;

import java.util.Set;
import javax.annotation.Nullable;

/**
 * Customer Tags.
 */
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractCustomerTags {

  /**
  * A {@link Set} of tags that were assigned automatically.
  */
  @Nullable public abstract Set<String> auto();

  /**
  * A {@link Set} of tags that were assigned manually.
  */
  @Nullable public abstract Set<String> manual();
}
