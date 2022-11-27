package com.nime.eqviewer.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets ResultType
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-11-27T23:03:00.958067500+08:00[Asia/Hong_Kong]")
public enum ResultTypeDto {
  
  SAME("SAME"),
  
  EQUAL("EQUAL"),
  
  INEQUAL("INEQUAL");

  private String value;

  ResultTypeDto(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static ResultTypeDto fromValue(String value) {
    for (ResultTypeDto b : ResultTypeDto.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

