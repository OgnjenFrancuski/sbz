package rs.ac.uns.ftn.sbz.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "account")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    private Long id;

    @Column(name = "account_username", unique = true)
    private String username;

    @Column(name = "account_password")
    private String password;

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "account_authorities",
               joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "account_id"),
               inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "authority_id"))
    private List<Authority> authorities;

    @OneToOne
    @JoinColumn(referencedColumnName = "person_id", name = "account_physician_id")
    private Physician physician;
}
