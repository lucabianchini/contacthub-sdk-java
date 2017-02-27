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
   *
   * <p>Marked as @Nullable so that you can create a `patchCustomer` object with
   * this field set to null. It is never `null` when it is persisted.</p>
  */
  @Nullable public abstract Set<String> auto();

  /**
  * A {@link Set} of tags that were assigned manually.
   *
   * <p>Marked as @Nullable so that you can create a `patchCustomer` object with
   * this field set to null. It is never `null` when it is persisted.</p>
  */
  @Nullable public abstract Set<String> manual();
}
