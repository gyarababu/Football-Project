package com.football.url.payload;

import com.football.url.enums.UserRole;
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
        description = "RegisterDTO Model information"
)
public class RegisterDto {

    @Schema(
            description = "Blog Register Name"
    )
    private String name;

    @Schema(
            description = "Blog Register UserName"
    )
    private String userName;

    @Schema(
            description = "Blog Register Email"
    )
    private String email;

    @Schema(
            description = "Blog Register Password"
    )
    private String password;

    @Schema(
            description = "User role"
    )
    private UserRole userRole;
}
