package rs.ac.uns.ftn.sbz.backend.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.sbz.backend.model.Physician;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PhysicianDto
{
    private Long id;
    private String firstName;
    private String lastName;
    private String personalId;


    public PhysicianDto(Physician physician)
    {
        this.id = physician.getId();
        this.firstName = physician.getFirstName();
        this.lastName = physician.getLastName();
        this.personalId = physician.getPersonalId();
    }
}
