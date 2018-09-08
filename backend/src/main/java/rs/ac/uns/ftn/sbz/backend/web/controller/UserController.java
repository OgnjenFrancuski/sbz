package rs.ac.uns.ftn.sbz.backend.web.controller;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.sbz.backend.service.UserService;
import rs.ac.uns.ftn.sbz.backend.web.dto.LoggedInUserDto;
import rs.ac.uns.ftn.sbz.backend.web.dto.LoginDto;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping(value = "/api")
public class UserController
{
    @Value("${ruleSessionName}")
    private String ruleSessionName;

    private final UserService userService;
    private final KieContainer kieContainer;


    @Autowired
    public UserController(UserService userService, KieContainer kieContainer)
    {
        this.userService = userService;
        this.kieContainer = kieContainer;
    }


    @RequestMapping(
            value = "/login",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity login(HttpSession httpSession, @RequestBody LoginDto loginDTO)
    {
        List<String> authorities = this.userService.login(loginDTO.getUsername(), loginDTO.getPassword());
        KieSession kieSession = this.kieContainer.newKieSession(this.ruleSessionName);
        httpSession.setAttribute("kieSession", kieSession);
        return new ResponseEntity<>(new LoggedInUserDto(loginDTO.getUsername(), authorities), HttpStatus.OK);
    }


    @RequestMapping(
            value = "/logout",
            method = RequestMethod.GET
    )
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity logout(HttpSession httpSession)
    {
        this.userService.logout();
        KieSession ks = (KieSession) httpSession.getAttribute("kieSession");
        httpSession.removeAttribute("kieSession");
        ks.destroy();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
