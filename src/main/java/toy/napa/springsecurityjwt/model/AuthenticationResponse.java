package toy.napa.springsecurityjwt.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AuthenticationResponse {

    private String jwt;

    @Builder
    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }
}
