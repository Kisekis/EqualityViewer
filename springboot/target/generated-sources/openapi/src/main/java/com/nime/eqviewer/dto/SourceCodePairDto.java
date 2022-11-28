package com.nime.eqviewer.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.nime.eqviewer.dto.ConfilevelDto;
import com.nime.eqviewer.dto.ResultTypeDto;
import com.nime.eqviewer.dto.SourceCodeDto;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * SourceCodePairDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-11-28T15:17:24.572593+08:00[Asia/Hong_Kong]")
public class SourceCodePairDto   {

  @JsonProperty("code1")
  private SourceCodeDto code1;

  @JsonProperty("code2")
  private SourceCodeDto code2;

  @JsonProperty("result")
  private ResultTypeDto result;

  @JsonProperty("level")
  private ConfilevelDto level;

  @JsonProperty("id")
  private Integer id;

  public SourceCodePairDto code1(SourceCodeDto code1) {
    this.code1 = code1;
    return this;
  }

  /**
   * Get code1
   * @return code1
  */
  @NotNull @Valid 
  @Schema(name = "code1", required = true)
  public SourceCodeDto getCode1() {
    return code1;
  }

  public void setCode1(SourceCodeDto code1) {
    this.code1 = code1;
  }

  public SourceCodePairDto code2(SourceCodeDto code2) {
    this.code2 = code2;
    return this;
  }

  /**
   * Get code2
   * @return code2
  */
  @NotNull @Valid 
  @Schema(name = "code2", required = true)
  public SourceCodeDto getCode2() {
    return code2;
  }

  public void setCode2(SourceCodeDto code2) {
    this.code2 = code2;
  }

  public SourceCodePairDto result(ResultTypeDto result) {
    this.result = result;
    return this;
  }

  /**
   * Get result
   * @return result
  */
  @NotNull @Valid 
  @Schema(name = "result", required = true)
  public ResultTypeDto getResult() {
    return result;
  }

  public void setResult(ResultTypeDto result) {
    this.result = result;
  }

  public SourceCodePairDto level(ConfilevelDto level) {
    this.level = level;
    return this;
  }

  /**
   * Get level
   * @return level
  */
  @NotNull @Valid 
  @Schema(name = "level", required = true)
  public ConfilevelDto getLevel() {
    return level;
  }

  public void setLevel(ConfilevelDto level) {
    this.level = level;
  }

  public SourceCodePairDto id(Integer id) {
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
    SourceCodePairDto sourceCodePair = (SourceCodePairDto) o;
    return Objects.equals(this.code1, sourceCodePair.code1) &&
        Objects.equals(this.code2, sourceCodePair.code2) &&
        Objects.equals(this.result, sourceCodePair.result) &&
        Objects.equals(this.level, sourceCodePair.level) &&
        Objects.equals(this.id, sourceCodePair.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code1, code2, result, level, id);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SourceCodePairDto {\n");
    sb.append("    code1: ").append(toIndentedString(code1)).append("\n");
    sb.append("    code2: ").append(toIndentedString(code2)).append("\n");
    sb.append("    result: ").append(toIndentedString(result)).append("\n");
    sb.append("    level: ").append(toIndentedString(level)).append("\n");
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

