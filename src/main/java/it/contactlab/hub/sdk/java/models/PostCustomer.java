package it.contactlab.hub.sdk.java.models;

import it.contactlab.hub.sdk.java.models.PostCustomerTags;
import it.contactlab.hub.sdk.java.models.base.BaseProperties;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * PostCustomer.
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen",
                            date = "2016-10-31T13:59:13.845Z")
public class PostCustomer   {
  @SerializedName("externalId")
  private String externalId = null;

  @SerializedName("nodeId")
  private String nodeId = null;

  @SerializedName("base")
  private BaseProperties base = null;

  @SerializedName("extended")
  private Object extended = null;

  @SerializedName("extra")
  private String extra = null;

  @SerializedName("tags")
  private PostCustomerTags tags = null;

  @SerializedName("enabled")
  private Boolean enabled = null;

  /**
   * the external id of customer.
   * @return externalId
  **/
  @ApiModelProperty(example = "null", value = "the external id of customer")
  public String getExternalId() {
    return externalId;
  }

  public void setExternalId(String externalId) {
    this.externalId = externalId;
  }

  /**
   * entry node `id`.
   * @return nodeId
  **/
  @ApiModelProperty(example = "null", value = "entry node `id`")
  public String getNodeId() {
    return nodeId;
  }

  public void setNodeId(String nodeId) {
    this.nodeId = nodeId;
  }

  /**
   * properties predefined in contacthub in base to the model retrived in /models/properties/base.
   * @return base
  **/
  @ApiModelProperty(example = "null",
                    value = "properties predefined in contacthub in base to the model retrived "
                          + "in /models/properties/base")
  public BaseProperties getBase() {
    return base;
  }

  public void setBase(BaseProperties base) {
    this.base = base;
  }

  /**
   * custom data defined by workspace based on a declarated schema.
   * @return extended
  **/
  @ApiModelProperty(example = "null",
                    value = "custom data defined by workspace based on a declarated schema")
  public Object getExtended() {
    return extended;
  }

  public void setExtended(Object extended) {
    this.extended = extended;
  }

  /**
   * custom data defined by workspace not based on a declarated schema.
   * @return extra
  **/
  @ApiModelProperty(example = "null",
                    value = "custom data defined by workspace not based on a declarated schema")
  public String getExtra() {
    return extra;
  }

  public void setExtra(String extra) {
    this.extra = extra;
  }

  /**
   * Get tags.
   * @return tags
  **/
  @ApiModelProperty(example = "null", value = "")
  public PostCustomerTags getTags() {
    return tags;
  }

  public void setTags(PostCustomerTags tags) {
    this.tags = tags;
  }

  /**
   * flag for soft delete.
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
  public boolean equals(java.lang.Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    PostCustomer postCustomer = (PostCustomer) obj;
    return Objects.equals(this.externalId, postCustomer.externalId)
        && Objects.equals(this.nodeId, postCustomer.nodeId)
        && Objects.equals(this.base, postCustomer.base)
        && Objects.equals(this.extended, postCustomer.extended)
        && Objects.equals(this.extra, postCustomer.extra)
        && Objects.equals(this.tags, postCustomer.tags)
        && Objects.equals(this.enabled, postCustomer.enabled);
  }

  @Override
  public int hashCode() {
    return Objects.hash(externalId, nodeId, base, extended, extra, tags, enabled);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PostCustomer {\n");

    sb.append("    externalId: ").append(toIndentedString(externalId)).append("\n");
    sb.append("    nodeId: ").append(toIndentedString(nodeId)).append("\n");
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
  private String toIndentedString(java.lang.Object obj) {
    if (obj == null) {
      return "null";
    }
    return obj.toString().replace("\n", "\n    ");
  }
}

