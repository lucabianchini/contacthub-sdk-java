package it.contactlab.hub.sdk.java.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * PutCustomer.
 */
@javax.annotation.Generated(
    value = "class io.swagger.codegen.languages.JavaClientCodegen",
    date = "2016-11-16T14:42:57.242Z")
public class PutCustomer extends PostCustomer {
  @SerializedName("id")
  private String id = null;

  public PutCustomer id(String id) {
    this.id = id;
    return this;
  }

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


  @Override
  public boolean equals(java.lang.Object that) {
    if (this == that) {
      return true;
    }
    if (that == null || getClass() != that.getClass()) {
      return false;
    }
    PutCustomer putCustomer = (PutCustomer) that;
    return Objects.equals(this.id, putCustomer.id)
        && super.equals(that);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, super.hashCode());
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PutCustomer {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

