package rs.ac.uns.ftn.sbz.backend.model.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.sbz.backend.model.Symptom;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SymptomList
{
    private List<Symptom> symptoms;


    public SymptomList()
    {
        this.symptoms = new ArrayList<>();
    }


    public void add(Symptom symptom)
    {
        this.symptoms.add(symptom);
    }


    public void remove(Symptom symptom)
    {
        this.symptoms.remove(symptom);
    }


    public Boolean contains(Symptom symptom)
    {
        return  this.symptoms.contains(symptom);
    }


}
