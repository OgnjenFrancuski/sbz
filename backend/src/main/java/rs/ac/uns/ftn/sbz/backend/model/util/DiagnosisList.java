package rs.ac.uns.ftn.sbz.backend.model.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import rs.ac.uns.ftn.sbz.backend.model.Diagnosis;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class DiagnosisList
{
    private List<Diagnosis> diagnosisList;


    public DiagnosisList()
    {
        this.diagnosisList = new ArrayList<>();
    }


    public void add(Diagnosis diagnosis)
    {
        this.diagnosisList.add(diagnosis);
    }


    public void remove(Diagnosis diagnosis)
    {
        this.diagnosisList.remove(diagnosis);
    }


    public Boolean contains(Diagnosis diagnosis)
    {
        for (Diagnosis p : this.diagnosisList)
            if (p.getId().longValue() == diagnosis.getId())
                return true;
        return false;
    }
}
