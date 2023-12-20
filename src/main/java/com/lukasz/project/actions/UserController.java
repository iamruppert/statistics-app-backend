package com.lukasz.project.actions;

import com.lukasz.project.config.JwtService;
import com.lukasz.project.token.Token;
import com.lukasz.project.token.TokenRepository;
import com.lukasz.project.user.User;
import com.lukasz.project.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth/user")

public class UserController {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;

    public UserController(UserRepository userRepository, JwtService jwtService, TokenRepository token) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        tokenRepository = token;
    }


    @DeleteMapping("/deleteAccount")
    public ResponseEntity<String> deleteAccount(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // Usunięcie przedrostka "Bearer "

            String email = jwtService.extractUsername(token);
            Optional<User> user = userRepository.findByEmail(email);
            User user2 = user.orElseThrow(() -> new RuntimeException("User not found"));
            if (user2 != null) {List<Token> tokens = tokenRepository.findByUserEmail(email);
                tokenRepository.deleteAll(tokens);
                userRepository.delete(user2);
                return ResponseEntity.ok("Account deleted successfully");
            } else {
                throw new UsernameNotFoundException("User not found");
            }
        } else {
            throw new AccessDeniedException("Access denied");
        }
    }


}
