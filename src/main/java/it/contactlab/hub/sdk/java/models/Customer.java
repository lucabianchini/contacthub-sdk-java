package it.contactlab.hub.sdk.java.models;

import it.contactlab.hub.sdk.java.models.PostCustomer;
import it.contactlab.hub.sdk.java.models.PostCustomerTags;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * Customer.
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen",
                            date = "2016-10-31T13:59:13.845Z")
public class Customer extends PostCustomer {
  @SerializedName("id")
  private String id = null;

  @SerializedName("registeredAt")
  private OffsetDateTime registeredAt = null;

  @SerializedName("updatedAt")
  private OffsetDateTime updatedAt = null;

  @SerializedName("_links")
  private Object links = null;

  /**
   * the customer id.
   * @return id
  **/
  @ApiModelProperty(example = "null", value = "the customer id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  /**
   * the registration timestamp.
   * @return registeredAt
  **/
  @ApiModelProperty(example = "null", value = "the registration timestamp")
  public OffsetDateTime getRegisteredAt() {
    return registeredAt;
  }

  public void setRegisteredAt(OffsetDateTime registeredAt) {
    this.registeredAt = registeredAt;
  }

  /**
   * the customer update timestamp.
   * @return updatedAt
  **/
  @ApiModelProperty(example = "null", value = "the customer update timestamp")
  public OffsetDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  /**
   * the related links of customer.
   * @return links
  **/
  @ApiModelProperty(example = "null", value = "the related links of customer")
  public Object getLinks() {
    return links;
  }

  public void setLinks(Object links) {
    this.links = links;
  }


  @Override
  public boolean equals(java.lang.Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Customer customer = (Customer) obj;
    return Objects.equals(this.id, customer.id)
        && Objects.equals(this.registeredAt, customer.registeredAt)
        && Objects.equals(this.updatedAt, customer.updatedAt)
        && Objects.equals(this.links, customer.links)
        && super.equals(obj);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, registeredAt, updatedAt, links, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Customer {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    registeredAt: ").append(toIndentedString(registeredAt)).append("\n");
    sb.append("    updatedAt: ").append(toIndentedString(updatedAt)).append("\n");
    sb.append("    links: ").append(toIndentedString(links)).append("\n");
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
