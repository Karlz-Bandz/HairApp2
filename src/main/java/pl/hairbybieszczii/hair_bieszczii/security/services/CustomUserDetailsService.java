package pl.hairbybieszczii.hair_bieszczii.security.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.hairbybieszczii.hair_bieszczii.security.models.EntityUser;
import pl.hairbybieszczii.hair_bieszczii.security.models.Role;
import pl.hairbybieszczii.hair_bieszczii.security.repo.UserRepository;

import java.util.Collection;
import java.util.List;

import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EntityUser user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User name not found!"));

        return new User(user.getUserName(), user.getPassword(), mapRolesToAutority(user.getRoles()));
    }

    private Collection<GrantedAuthority> mapRolesToAutority(List<Role> roles) {
        return roles.stream()
                .map(x -> new SimpleGrantedAuthority(x.getName()))
                .collect(Collectors.toList());
    }
}
