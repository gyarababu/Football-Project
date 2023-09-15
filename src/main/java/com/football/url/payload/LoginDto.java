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
        description = "LoginDTO Model information"
)
public class LoginDto {

    @Schema(
            description = "Blog Login UserName or Email"
    )
    private String userNameOrEmail;

    @Schema(
            description = "Blog Login Password"
    )
    private String password;
}
