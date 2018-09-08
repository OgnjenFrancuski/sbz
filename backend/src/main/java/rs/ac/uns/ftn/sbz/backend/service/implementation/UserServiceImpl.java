package rs.ac.uns.ftn.sbz.backend.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.sbz.backend.exception.BadRequestException;
import rs.ac.uns.ftn.sbz.backend.exception.NotFoundException;
import rs.ac.uns.ftn.sbz.backend.model.Authority;
import rs.ac.uns.ftn.sbz.backend.model.User;
import rs.ac.uns.ftn.sbz.backend.repository.UserRepository;
import rs.ac.uns.ftn.sbz.backend.service.UserService;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService
{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public List<String> login(String username, String password)
    {
        User user = this.userRepository.findByUsername(username);

        if (user == null)
            throw new BadRequestException("Bad credentials!");

        String usernameDB = user.getUsername();
        String passwordDB = user.getPassword();
        List<String> authoritiesDB = user.getAuthorities().stream().map(Authority::getName).collect(Collectors.toList());

        if(!username.equals(usernameDB) || !this.passwordEncoder.matches(password, passwordDB))
            throw new BadRequestException("Bad credentials!");

        this.putUserInSession(username, authoritiesDB);
        return authoritiesDB;
    }


    @Override
    public void logout() {
        SecurityContextHolder.clearContext();
    }


    @Override
    public boolean isAuthenticated()
    {
        return !SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser");
    }


    @Override
    public String currentUserUsername()
    {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


    @Override
    public String currentUserRole()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();
        return authorities.isEmpty() ? null : authorities.get(0).getAuthority();
    }


    @Override
    public User currentUser()
    {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return this.userRepository.findByUsername(username);
    }


    @Override
    public User findByUsername(String username)
    {
        User user = this.userRepository.findByUsername(username);
        if (user == null)
            throw new NotFoundException("User with given username not found!");
        return user;
    }


    private void putUserInSession(String username, List<String> authorities)
    {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        for (String authName : authorities)
            grantedAuthorities.add(new SimpleGrantedAuthority(authName));

        // spring authentication instance
        Authentication authentication = new PreAuthenticatedAuthenticationToken(username, null, grantedAuthorities);

        // store authenticated user in application contex
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}

