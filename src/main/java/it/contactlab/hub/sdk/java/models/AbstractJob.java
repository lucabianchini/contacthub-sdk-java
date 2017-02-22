package it.contactlab.hub.sdk.java.models;

import org.immutables.value.Value;

import java.time.LocalDate;
import java.util.Optional;

@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractJob {

  public abstract String id();

  public abstract Optional<String> companyIndustry();

  public abstract Optional<String> companyName();

  public abstract Optional<String> jobTitle();

  public abstract Optional<LocalDate> startDate();

  public abstract Optional<LocalDate> endDate();

  public abstract Optional<Boolean> isCurrent();

}
