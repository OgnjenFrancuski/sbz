package rs.ac.uns.ftn.sbz.backend.model.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class StringIntegerMap
{
    private Map<String, Integer> map;


    public StringIntegerMap()
    {
        this.map = new HashMap<>();
    }


    public void put(String s, Integer i)
    {
        this.map.put(s, i);
    }


    public int get(String s)
    {
        return this.map.get(s);
    }


    public int size()
    {
        return this.map.size();
    }


    public boolean contains(String name)
    {
        return this.map.containsKey(name);
    }


    public void incCount(String name)
    {
        if (this.map.containsKey(name))
            this.map.put(name, this.map.get(name) + 1);
        else
            this.map.put(name, 1);
    }


    public Set<String> keySet()
    {
        return this.map.keySet();
    }


    public int valueSetSum()
    {
        int sum = 0;
        for (int i : this.map.values())
            sum += i;
        return sum;
    }



}
