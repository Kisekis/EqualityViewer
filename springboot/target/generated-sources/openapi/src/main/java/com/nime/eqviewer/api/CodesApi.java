/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (5.4.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.nime.eqviewer.api;

import com.nime.eqviewer.dto.ErrorDto;
import com.nime.eqviewer.dto.SourceCodePairDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-11-27T23:03:00.958067500+08:00[Asia/Hong_Kong]")
@Validated
@Tag(name = "codes", description = "the codes API")
public interface CodesApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /codes : List all source_code_pairs
     *
     * @return A paged array of products (status code 200)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "listSourceCodePairs",
        summary = "List all source_code_pairs",
        tags = { "SourceCodePairs" },
        responses = {
            @ApiResponse(responseCode = "200", description = "A paged array of products", content = @Content(mediaType = "application/json", schema = @Schema(implementation =  SourceCodePairDto.class))),
            @ApiResponse(responseCode = "200", description = "unexpected error", content = @Content(mediaType = "application/json", schema = @Schema(implementation =  ErrorDto.class)))
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/codes",
        produces = { "application/json" }
    )
    default ResponseEntity<List<SourceCodePairDto>> listSourceCodePairs(
        
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code2\" : { \"path\" : \"path\", \"id\" : 0 }, \"code1\" : { \"path\" : \"path\", \"id\" : 0 } }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}