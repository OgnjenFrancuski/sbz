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
@AllArgsConstructor
@NoArgsConstructor
@Role(Role.Type.EVENT)
public class Notification
{
    private Patient patient;
    private String notification;
}
