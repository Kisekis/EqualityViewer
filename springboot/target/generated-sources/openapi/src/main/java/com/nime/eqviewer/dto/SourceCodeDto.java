package com.nime.eqviewer.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * SourceCodeDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-11-28T15:17:24.572593+08:00[Asia/Hong_Kong]")
public class SourceCodeDto   {

  @JsonProperty("path")
  private String path;

  @JsonProperty("id")
  private Integer id;

  public SourceCodeDto path(String path) {
    this.path = path;
    return this;
  }

  /**
   * Get path
   * @return path
  */
  @NotNull 
  @Schema(name = "path", required = true)
  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public SourceCodeDto id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @NotNull 
  @Schema(name = "id", required = true)
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SourceCodeDto sourceCode = (SourceCodeDto) o;
    return Objects.equals(this.path, sourceCode.path) &&
        Objects.equals(this.id, sourceCode.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(path, id);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SourceCodeDto {\n");
    sb.append("    path: ").append(toIndentedString(path)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

