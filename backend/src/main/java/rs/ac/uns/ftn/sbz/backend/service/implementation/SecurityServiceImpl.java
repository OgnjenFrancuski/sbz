package rs.ac.uns.ftn.sbz.backend.service.implementation;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.sbz.backend.exception.NotFoundException;
import rs.ac.uns.ftn.sbz.backend.service.SecurityService;


@Service(value = "securityService")
public class SecurityServiceImpl implements SecurityService
{
    @Override
    public Boolean hasAccountSpecificAuthorities(String username)
    {
        try
        {
            String ctxUsername = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return ctxUsername.equalsIgnoreCase(username);
        }
        catch(NotFoundException e)
        {
            return false;
        }
    }
}
