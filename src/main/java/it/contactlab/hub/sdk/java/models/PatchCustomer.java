package it.contactlab.hub.sdk.java.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * PatchCustomer
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-11-16T14:42:57.242Z")
public class PatchCustomer {
  @SerializedName("externalId")
  private String externalId = null;

  @SerializedName("base")
  private Object base = null;

  @SerializedName("extended")
  private Object extended = null;

  @SerializedName("extra")
  private String extra = null;

  @SerializedName("tags")
  private PostCustomerTags tags = null;

  @SerializedName("enabled")
  private Boolean enabled = null;

  public PatchCustomer externalId(String externalId) {
    this.externalId = externalId;
    return this;
  }

   /**
   * the external id of customer
   * @return externalId
  **/
  @ApiModelProperty(example = "null", value = "the external id of customer")
  public String getExternalId() {
    return externalId;
  }

  public void setExternalId(String externalId) {
    this.externalId = externalId;
  }

  public PatchCustomer base(Object base) {
    this.base = base;
    return this;
  }

   /**
   * properties predefined in contacthub in base to the model retrived in /models/properties/base
   * @return base
  **/
  @ApiModelProperty(example = "null", value = "properties predefined in contacthub in base to the model retrived in /models/properties/base")
  public Object getBase() {
    return base;
  }

  public void setBase(Object base) {
    this.base = base;
  }

  public PatchCustomer extended(Object extended) {
    this.extended = extended;
    return this;
  }

   /**
   * custom data defined by workspace based on a declarated schema
   * @return extended
  **/
  @ApiModelProperty(example = "null", value = "custom data defined by workspace based on a declarated schema")
  public Object getExtended() {
    return extended;
  }

  public void setExtended(Object extended) {
    this.extended = extended;
  }

  public PatchCustomer extra(String extra) {
    this.extra = extra;
    return this;
  }

   /**
   * custom data defined by workspace not based on a declarated schema
   * @return extra
  **/
  @ApiModelProperty(example = "null", value = "custom data defined by workspace not based on a declarated schema")
  public String getExtra() {
    return extra;
  }

  public void setExtra(String extra) {
    this.extra = extra;
  }

  public PatchCustomer tags(PostCustomerTags tags) {
    this.tags = tags;
    return this;
  }

   /**
   * Get tags
   * @return tags
  **/
  @ApiModelProperty(example = "null", value = "")
  public PostCustomerTags getTags() {
    return tags;
  }

  public void setTags(PostCustomerTags tags) {
    this.tags = tags;
  }

  public PatchCustomer enabled(Boolean enabled) {
    this.enabled = enabled;
    return this;
  }

   /**
   * flag for soft delete
   * @return enabled
  **/
  @ApiModelProperty(example = "null", value = "flag for soft delete")
  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PatchCustomer patchCustomer = (PatchCustomer) o;
    return Objects.equals(this.externalId, patchCustomer.externalId) &&
        Objects.equals(this.base, patchCustomer.base) &&
        Objects.equals(this.extended, patchCustomer.extended) &&
        Objects.equals(this.extra, patchCustomer.extra) &&
        Objects.equals(this.tags, patchCustomer.tags) &&
        Objects.equals(this.enabled, patchCustomer.enabled);
  }

  @Override
  public int hashCode() {
    return Objects.hash(externalId, base, extended, extra, tags, enabled);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PatchCustomer {\n");
    
    sb.append("    externalId: ").append(toIndentedString(externalId)).append("\n");
    sb.append("    base: ").append(toIndentedString(base)).append("\n");
    sb.append("    extended: ").append(toIndentedString(extended)).append("\n");
    sb.append("    extra: ").append(toIndentedString(extra)).append("\n");
    sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
    sb.append("    enabled: ").append(toIndentedString(enabled)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
  
}

