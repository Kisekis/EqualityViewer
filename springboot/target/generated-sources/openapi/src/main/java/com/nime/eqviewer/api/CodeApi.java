/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (5.4.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.nime.eqviewer.api;

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

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-12-01T19:49:50.875269600+08:00[Asia/Hong_Kong]")
@Validated
@Tag(name = "code", description = "the code API")
public interface CodeApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /code/{codeID} : get source code content
     *
     * @param codeID  (required)
     * @return A paged array of products (status code 200)
     */
    @Operation(
        operationId = "getSourceCode",
        summary = "get source code content",
        responses = {
            @ApiResponse(responseCode = "200", description = "A paged array of products", content = @Content(mediaType = "application/json", schema = @Schema(implementation =  String.class)))
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/code/{codeID}",
        produces = { "text/plain" }
    )
    default ResponseEntity<String> getSourceCode(
        @Parameter(name = "codeID", description = "", required = true, schema = @Schema(description = "")) @PathVariable("codeID") Integer codeID
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
