package it.contactlab.hub.sdk.java.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * the default tags property of customers.
 */
@ApiModel(description = "the default tags property of customers")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen",
                            date = "2016-10-31T13:59:13.845Z")
public class PostCustomerTags   {
  @SerializedName("auto")
  private List<String> auto = new ArrayList<String>();

  @SerializedName("manual")
  private List<String> manual = new ArrayList<String>();

  /**
   * Get auto.
   * @return auto
  **/
  @ApiModelProperty(example = "null", value = "")
  public List<String> getAuto() {
    return auto;
  }

  public void setAuto(List<String> auto) {
    this.auto = auto;
  }

  /**
   * Get manual.
   * @return manual
  **/
  @ApiModelProperty(example = "null", value = "")
  public List<String> getManual() {
    return manual;
  }

  public void setManual(List<String> manual) {
    this.manual = manual;
  }

  @Override
  public boolean equals(java.lang.Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    PostCustomerTags postCustomerTags = (PostCustomerTags) obj;
    return Objects.equals(this.auto, postCustomerTags.auto)
        && Objects.equals(this.manual, postCustomerTags.manual);
  }

  @Override
  public int hashCode() {
    return Objects.hash(auto, manual);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PostCustomerTags {\n");

    sb.append("    auto: ").append(toIndentedString(auto)).append("\n");
    sb.append("    manual: ").append(toIndentedString(manual)).append("\n");
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

