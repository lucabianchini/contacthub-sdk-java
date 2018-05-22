package it.contactlab.hub.sdk.java.models;

import com.google.gson.annotations.SerializedName;

public enum StoreType {
  AIRPORT,
  ECOMMERCE,
  FLAGSHIP,
  @SerializedName("FREE-STANDING") FREE_STANDING,
  MALL,
  OUTLET,
  RESORT,
  SIS,
  WAREHOUSE,
  @SerializedName("NOT-DEFINED") NOT_DEFINED
}
