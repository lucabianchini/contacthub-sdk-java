package it.contactlab.hub.sdk.java.models.base;

import org.immutables.gson.Gson;
import org.immutables.value.Value;

@Gson.TypeAdapters
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractEducation {

  public abstract String id();

  public abstract SchoolType schoolType();

  public abstract String schoolName();

  public abstract String schoolConcentration();

  public abstract Integer startYear();

  public abstract Integer endYear();

  public abstract Boolean isCurrent();

  public enum SchoolType {
    PRIMARY_SCHOOL,
    SECONDARY_SCHOOL,
    HIGH_SCHOOL,
    COLLEGE,
    OTHER
  }

}
