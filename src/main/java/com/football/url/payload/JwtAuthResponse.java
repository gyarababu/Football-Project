package com.football.url.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        description = "JWT Auth Model information"
)
public class JwtAuthResponse {

    @Schema(
            description = "Blog JWT Token"
    )
    private String accessToken;

    @Schema(
            description = "Blog JWT Token Type"
    )
    private String tokenType = "Bearer";
}
