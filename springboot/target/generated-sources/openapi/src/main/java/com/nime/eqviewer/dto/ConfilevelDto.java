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
 * Gets or Sets Confilevel
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-12-01T19:49:50.875269600+08:00[Asia/Hong_Kong]")
public enum ConfilevelDto {
  
  SUSPICIOUS("SUSPICIOUS"),
  
  RELIABLE("RELIABLE"),
  
  UNRELIABLE("UNRELIABLE");

  private String value;

  ConfilevelDto(String value) {
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
  public static ConfilevelDto fromValue(String value) {
    for (ConfilevelDto b : ConfilevelDto.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

