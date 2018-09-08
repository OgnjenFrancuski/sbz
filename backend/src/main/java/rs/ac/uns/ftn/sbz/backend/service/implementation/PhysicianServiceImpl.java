package rs.ac.uns.ftn.sbz.backend.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.sbz.backend.exception.NotFoundException;
import rs.ac.uns.ftn.sbz.backend.model.Physician;
import rs.ac.uns.ftn.sbz.backend.model.User;
import rs.ac.uns.ftn.sbz.backend.repository.PhysicianRepository;
import rs.ac.uns.ftn.sbz.backend.repository.UserRepository;
import rs.ac.uns.ftn.sbz.backend.service.PhysicianService;

import java.util.HashSet;
import java.util.Set;


@Service
public class PhysicianServiceImpl implements PhysicianService
{
    private final PhysicianRepository physicianRepository;
    private final UserRepository userRepository;


    @Autowired
    public PhysicianServiceImpl(PhysicianRepository physicianRepository,
                                UserRepository userRepository)
    {
        this.physicianRepository = physicianRepository;
        this.userRepository = userRepository;
    }


    @Override
    public Physician create(Physician physician)
    {
        physician.setId(null);
        return this.physicianRepository.save(physician);
    }


    @Override
    public Physician update(Physician physician)
    {
        Physician physicianDB = this.physicianRepository.findById(physician.getId()).orElseThrow(NotFoundException::new);
        physicianDB.setFirstName(physician.getFirstName());
        physicianDB.setLastName(physician.getLastName());
        physicianDB.setPersonalId(physician.getPersonalId());
        return this.physicianRepository.save(physicianDB);
    }


    @Override
    public void delete(Long id)
    {
        this.physicianRepository.deleteById(id);
    }


    @Override
    public Physician getOne(Long id)
    {
        return this.physicianRepository.findById(id).orElseThrow(NotFoundException::new);
    }


    @Override
    public Physician getByUsername()
    {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = this.userRepository.findByUsername(username);
        return user.getPhysician();
    }


    @Override
    public Set<Physician> getAll()
    {
        return new HashSet<>(this.physicianRepository.findAll());
    }
}
