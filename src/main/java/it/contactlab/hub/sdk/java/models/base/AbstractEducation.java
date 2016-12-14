package it.contactlab.hub.sdk.java.models.base;

import org.immutables.gson.Gson;
import org.immutables.value.Value;

import java.util.Optional;

@Gson.TypeAdapters
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractEducation {

  public abstract Optional<String> id();

  public abstract Optional<SchoolType> schoolType();

  public abstract Optional<String> schoolName();

  public abstract Optional<String> schoolConcentration();

  public abstract Optional<Integer> startYear();

  public abstract Optional<Integer> endYear();

  public abstract Optional<Boolean> isCurrent();

}
