package rs.ac.uns.ftn.sbz.backend.model.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import rs.ac.uns.ftn.sbz.backend.model.Patient;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
public class PatientList
{
    private List<Patient> patients;


    public PatientList()
    {
        this.patients = new ArrayList<>();
    }


    public void add(Patient patient)
    {
        this.patients.add(patient);
    }


    public void remove(Patient patient)
    {
        this.patients.remove(patient);
    }


    public Boolean contains(Patient patient)
    {
        for (Patient p : this.patients)
            if (p.getId().longValue() == patient.getId())
                return true;
        return false;
    }
}
