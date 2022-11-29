package com.nime.eqviewer.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * UnionFindDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-11-29T15:04:32.792220700+08:00[Asia/Hong_Kong]")
public class UnionFindDto   {

  @JsonProperty("parent")
  @Valid
  private List<Integer> parent = new ArrayList<>();

  @JsonProperty("count")
  private Integer count;

  public UnionFindDto parent(List<Integer> parent) {
    this.parent = parent;
    return this;
  }

  public UnionFindDto addParentItem(Integer parentItem) {
    this.parent.add(parentItem);
    return this;
  }

  /**
   * Get parent
   * @return parent
  */
  @NotNull 
  @Schema(name = "parent", required = true)
  public List<Integer> getParent() {
    return parent;
  }

  public void setParent(List<Integer> parent) {
    this.parent = parent;
  }

  public UnionFindDto count(Integer count) {
    this.count = count;
    return this;
  }

  /**
   * Get count
   * @return count
  */
  @NotNull 
  @Schema(name = "count", required = true)
  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UnionFindDto unionFind = (UnionFindDto) o;
    return Objects.equals(this.parent, unionFind.parent) &&
        Objects.equals(this.count, unionFind.count);
  }

  @Override
  public int hashCode() {
    return Objects.hash(parent, count);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UnionFindDto {\n");
    sb.append("    parent: ").append(toIndentedString(parent)).append("\n");
    sb.append("    count: ").append(toIndentedString(count)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

