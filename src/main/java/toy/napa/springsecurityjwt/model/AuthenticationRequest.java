package toy.napa.springsecurityjwt.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AuthenticationRequest {

    private String username;
    private String password;

    @Builder
    public AuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
