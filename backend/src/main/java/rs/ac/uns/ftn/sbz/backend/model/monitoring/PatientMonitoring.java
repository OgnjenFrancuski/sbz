package rs.ac.uns.ftn.sbz.backend.model.monitoring;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import rs.ac.uns.ftn.sbz.backend.model.Diagnosis;
import rs.ac.uns.ftn.sbz.backend.model.Patient;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientMonitoring
{
    private Patient patient;
    private Diagnosis diagnosis;
    private Double bloodOxygenLevels;
}
