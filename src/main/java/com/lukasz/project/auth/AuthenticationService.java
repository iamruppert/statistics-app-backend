package com.lukasz.project.auth;

import com.lukasz.project.config.JwtService;
import com.lukasz.project.token.Token;
import com.lukasz.project.token.TokenRepository;
import com.lukasz.project.token.TokenType;
import com.lukasz.project.user.Role;
import com.lukasz.project.user.User;
import com.lukasz.project.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor

public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final ObjectValidator objectValidator;

    private final TokenRepository tokenRepository;

    public AuthenticationResponse register(
            RegisterRequest request) {

        var violations = objectValidator.validate(request);
        if(!violations.isEmpty()){
            return AuthenticationResponse
                    .builder()
                    .build();
        }
        // check if user already exists in DB

        String email = request.getEmail();
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Użytkownik o podanym adresie e-mail już istnieje.");
        }

        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.valueOf(Role.USER.name()))
                .build();
        var savedUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        revokeAllUsersToken(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private void revokeAllUsersToken(User user){
        var validUserToken = tokenRepository.findAllValidTokensByUser(user.getId());
        if(validUserToken.isEmpty()){
            return;
        }
        validUserToken.forEach(t->{
            t.setExpired(true);
            t.setRevoked(true);
        });
        tokenRepository.saveAll(validUserToken);
    }
    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();
        tokenRepository.save(token);
    }
}
