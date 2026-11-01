
package com.bookstore.bookstore_springboot.controller;

import com.bookstore.bookstore_springboot.dto.RegisterRequest;
import com.bookstore.bookstore_springboot.entity.User;
import com.bookstore.bookstore_springboot.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // POST /api/auth/register
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@Valid @RequestBody RegisterRequest req) {
        String email = req.getEmail().trim().toLowerCase();
        Map<String, Object> body = new LinkedHashMap<>();

        if (userRepository.existsByEmail(email)) {
            body.put("status", 409);
            body.put("error", "Indha email already register aagiduchu");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
        }

        User user = new User();
        user.setName(req.getName().trim());
        user.setEmail(email);
        user.setPasswordHash(passwordEncoder.encode(req.getPassword()));
        user.setRole("USER");
        User saved = userRepository.save(user);

        // password / hash-ai response-la anuppa koodaadhu
        body.put("id", saved.getId());
        body.put("name", saved.getName());
        body.put("email", saved.getEmail());
        body.put("role", saved.getRole());
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }
}