package rs.ac.uns.ftn.sbz.backend.model.monitoring.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import rs.ac.uns.ftn.sbz.backend.model.Patient;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Role(Role.Type.EVENT)
@Expires("30m")
public class Urination
{
    private Patient patient;
    private int amount;
}
