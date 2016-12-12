package it.contactlab.hub.sdk.java.models.base;

import org.immutables.gson.Gson;
import org.immutables.value.Value;

@Gson.TypeAdapters
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractJob {

  public abstract String id();

  public abstract String companyIndustry();

  public abstract String companyName();

  public abstract String jobTitle();

  public abstract String startDate();

  public abstract String endDate();

  public abstract Boolean isCurrent();

}
