package pl.hairbybieszczii.hair_bieszczii;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.hairbybieszczii.hair_bieszczii.dto.LoginDto;
import pl.hairbybieszczii.hair_bieszczii.dto.RegisterDto;
import pl.hairbybieszczii.hair_bieszczii.security.models.EntityUser;
import pl.hairbybieszczii.hair_bieszczii.security.models.Role;
import pl.hairbybieszczii.hair_bieszczii.security.repo.RoleRepository;
import pl.hairbybieszczii.hair_bieszczii.security.repo.UserRepository;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;


    @PostMapping("register/user")
    public ResponseEntity<String> registerUser(@RequestBody RegisterDto registerDto) {
        if (userRepository.existsByUserName(registerDto.getUserName())) {
            return new ResponseEntity<>("Name has taken!", HttpStatus.BAD_REQUEST);
        }

        EntityUser user = new EntityUser();
        user.setUserName(registerDto.getUserName());
        user.setPassword(passwordEncoder.encode((registerDto.getPassword())));

        Role roles = roleRepository.findByName("USER").orElseThrow(() -> new RuntimeException("USER not found!"));
        user.setRoles(Collections.singletonList(roles));
        userRepository.save(user);

        return new ResponseEntity<>("User added success!", HttpStatus.CREATED);
    }

    @PostMapping("register/admin")
    public ResponseEntity<String> registerUserAdmin(@RequestBody RegisterDto registerDto) {
        if (userRepository.existsByUserName(registerDto.getUserName())) {
            return new ResponseEntity<>("Name has taken!", HttpStatus.BAD_REQUEST);
        }

        EntityUser user = new EntityUser();
        user.setUserName(registerDto.getUserName());
        user.setPassword(passwordEncoder.encode((registerDto.getPassword())));

        Role roles = roleRepository.findByName("ADMIN").orElseThrow(() -> new RuntimeException("ADMIN not found"));
        user.setRoles(Collections.singletonList(roles));
        userRepository.save(user);

        return new ResponseEntity<>("User added success!", HttpStatus.CREATED);
    }

    @GetMapping("login")
    public ResponseEntity<Void> getLogin() {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity<Void> logUser(@RequestBody LoginDto loginDto) {
        Authentication userAuth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(userAuth);

        return new ResponseEntity<>(HttpStatus.OK);

    }


}
