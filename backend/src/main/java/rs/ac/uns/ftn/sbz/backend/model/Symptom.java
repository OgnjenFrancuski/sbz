package rs.ac.uns.ftn.sbz.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "symptom")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Symptom
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "symptom_id")
    private Long id;

    @Column(name = "symptom_name", unique = true)
    private String name;


    public Symptom(String name)
    {
        this.name = name;
    }


    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Symptom)) return false;

        Symptom other = (Symptom) o;

        if (id != null && !id.equals(other.id)) return false;
        if (name != null && !name.equalsIgnoreCase(other.name)) return false;

        return true;
    }
}
