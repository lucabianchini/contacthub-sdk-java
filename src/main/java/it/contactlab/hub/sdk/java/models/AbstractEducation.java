package it.contactlab.hub.sdk.java.models;

import org.immutables.value.Value;

import java.util.Optional;

@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractEducation {

  public abstract String id();

  public abstract Optional<SchoolType> schoolType();

  public abstract Optional<String> schoolName();

  public abstract Optional<String> schoolConcentration();

  public abstract Optional<Integer> startYear();

  public abstract Optional<Integer> endYear();

  public abstract Optional<Boolean> isCurrent();

}
