package rs.ac.uns.ftn.sbz.backend.model.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;


public class SymptomCounter
{
    Integer found;
    Integer all;
    Integer value;


    public SymptomCounter()
    {
        this.value = 0;
    }


    public SymptomCounter(Integer all, Integer found)
    {
        this();
        this.all = all;
        this.found = found;
    }


    public void incFound()
    {
        this.found += 1;
    }


    public void decFound()
    {
        this.found -= 1;
    }


    public void incSalience()
    {
        this.value += 1;
    }


    public void decSalience()
    {
        this.value -= 1;
    }


    public Integer difference()
    {
        return this.all - this.found;
    }


    public Boolean foundAll()
    {
        return this.found.doubleValue() == this.all;
    }

    public Integer getFound()
    {
        return found;
    }

    public void setFound(Integer found)
    {
        this.found = found;
    }

    public Integer getAll()
    {
        return all;
    }

    public void setAll(Integer all)
    {
        this.all = all;
    }

    public Integer getValue()
    {
        return value;
    }

    public void setValue(Integer value)
    {
        this.value = value;
    }
}
