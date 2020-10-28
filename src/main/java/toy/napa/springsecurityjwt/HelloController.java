package toy.napa.springsecurityjwt;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import toy.napa.springsecurityjwt.jwt.JwtProvider;
import toy.napa.springsecurityjwt.model.AuthenticationRequest;
import toy.napa.springsecurityjwt.model.AuthenticationResponse;
import toy.napa.springsecurityjwt.service.UserDetailsServiceImpl;

@RestController
@RequiredArgsConstructor
public class HelloController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final JwtProvider jwtProvider;

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.status(HttpStatus.OK).body("Hello Spring security With JWT");
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );

        final UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtProvider.generateToken(userDetails);

        return ResponseEntity.status(HttpStatus.OK).body(new AuthenticationResponse(jwt));
    }

}
