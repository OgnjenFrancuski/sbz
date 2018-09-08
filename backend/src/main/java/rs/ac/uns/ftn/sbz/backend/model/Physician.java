package rs.ac.uns.ftn.sbz.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "physician")
@Getter
@Setter
@NoArgsConstructor
public class Physician extends Person
{
    public Physician(Long id, String firstName, String lastName, String personalId)
    {
        super(id, firstName, lastName, personalId);
    }
}
