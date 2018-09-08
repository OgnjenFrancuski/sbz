package rs.ac.uns.ftn.sbz.backend.service;

import rs.ac.uns.ftn.sbz.backend.model.User;

import java.util.List;


public interface UserService
{
    List<String> login(String username, String password);

    void logout();

    boolean isAuthenticated();

    String currentUserUsername();

    String currentUserRole();

    User currentUser();

    User findByUsername(String username);
}
