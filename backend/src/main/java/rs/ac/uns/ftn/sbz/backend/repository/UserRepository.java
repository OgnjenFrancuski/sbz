package rs.ac.uns.ftn.sbz.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.sbz.backend.model.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long>
{
    Optional<User> findById(Long id);

    User findByUsername(String username);
}
