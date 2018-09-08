package rs.ac.uns.ftn.sbz.backend.model.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.sbz.backend.model.Disease;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DiseaseMap
{
    Map<Disease, Integer> map;


    public void put(Disease key, Integer value)
    {
        this.map.put(key, value);
    }
}
