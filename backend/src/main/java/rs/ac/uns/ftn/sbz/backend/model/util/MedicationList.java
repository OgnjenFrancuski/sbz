package rs.ac.uns.ftn.sbz.backend.model.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import rs.ac.uns.ftn.sbz.backend.model.Medication;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
public class MedicationList
{
    private List<Medication> medications;


    public MedicationList()
    {
        this.medications = new ArrayList<>();
    }


    public void add(Medication medication)
    {
        this.medications.add(medication);
    }


    public void remove(Medication medication)
    {
        this.medications.remove(medication);
    }


    public Boolean contains(Medication medication)
    {
        return  this.medications.contains(medication);
    }
}
