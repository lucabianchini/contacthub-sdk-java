package it.contactlab.hub.sdk.java.models.base;

import org.immutables.gson.Gson;
import org.immutables.value.Value;

/**
 * Social profile.
 */
@Gson.TypeAdapters
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractSocialProfile {

  public abstract String facebook();

  public abstract String google();

  public abstract String instagram();

  public abstract String linkedin();

  public abstract String qzone();

  public abstract String twitter();

}
