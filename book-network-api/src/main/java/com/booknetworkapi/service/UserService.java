package com.booknetworkapi.service;

import com.booknetworkapi.dto.LoginDTO;
import com.booknetworkapi.dto.RegistrationDTO;
import com.booknetworkapi.entity.user.Role;
import com.booknetworkapi.entity.user.Token;
import com.booknetworkapi.entity.user.User;
import com.booknetworkapi.repository.RoleRepository;
import com.booknetworkapi.repository.TokenRepository;
import com.booknetworkapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {

    @Value("${application.jwt.expiration}")
    private static String expiration;

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final TokenService tokenService;
    private final TokenRepository tokenRepository;
    private final AuthenticationProvider authenticationProvider;
    private final RoleRepository roleRepository;


    public User register(RegistrationDTO registration) {

        User user = User.builder()
                .firstName(registration.getFirstName())
                .lastName(registration.getLastName())
                .email(registration.getEmail())
                .password(encoder.encode(registration.getPassword()))
                .accountLocked(false)
                .enabled(true)
                .build();

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByAuthority("USER").get();

        roles.add(userRole);
        user.setAuthorities(roles);

        User savedUser = userRepository.save(user);
        return savedUser;
    }

    public String loginUser(LoginDTO login) {

        String username = login.getEmail();
        String password = login.getPassword();

        var auth = authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        User user = userRepository.findByEmail(username).get();

        List<Token> userTokens = tokenRepository.findAllUserTokens(user.getId());
        if(!userTokens.isEmpty()){
            userTokens.forEach(tokenRepository::delete);
        }

        String jwt = tokenService.generateToken(auth);


        Token token = Token.builder()
                .token(jwt)
                .user(user)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15 ))
                .build();
        Token savedToken = tokenRepository.save(token);

        return jwt;
    }
}
