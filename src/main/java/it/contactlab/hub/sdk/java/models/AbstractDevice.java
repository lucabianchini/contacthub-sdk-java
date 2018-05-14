package it.contactlab.hub.sdk.java.models;

import org.immutables.value.Value;

/**
 *  Device of mobile context info.
 */
@Value.Immutable
@Value.Style(typeImmutable = "*")
public abstract class AbstractDevice {

  /**
   * The bundle identifier of the mobile device.
   */
  public abstract String bundleIdentifier();

  /**
   * The version number of the mobile device.
   */
  public abstract String versionNumber();

  /**
   * The build number of the mobile device.
   */
  public abstract String buildNumber();

  /**
   * The vendor indentifier of the mobile device.
   */
  public abstract String identifierForVendor();

  /**
   * The system version of the mobile device.
   */
  public abstract String systemVersion();

  /**
   * The model of the mobile device.
   */
  public abstract String model();

  /**
   * The device vendor of the mobile device.
   */
  public abstract String deviceVendor();

  /**
   * The locale of the mobile device.
   */
  public abstract String locale();

  /**
   * The language of the mobile device.
   */
  public abstract String language();
}
