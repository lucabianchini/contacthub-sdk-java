package it.contactlab.hub.sdk.java.models;

import org.immutables.value.Value;

import java.util.Optional;

/**
 * Social profile.
 */
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractSocialProfile {

  public abstract Optional<String> facebook();

  public abstract Optional<String> google();

  public abstract Optional<String> instagram();

  public abstract Optional<String> linkedin();

  public abstract Optional<String> qzone();

  public abstract Optional<String> twitter();

}
