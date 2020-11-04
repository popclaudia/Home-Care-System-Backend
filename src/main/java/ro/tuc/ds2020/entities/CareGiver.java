package ro.tuc.ds2020.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@DiscriminatorValue("CareGiver")
public class CareGiver extends User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Integer ID;

    @OneToMany(mappedBy = "caregiver", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Patient> patients=new HashSet<Patient>();

    @Override
    public Integer getID() {
        return ID;
    }

    @Override
    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }

    public CareGiver() {
    }

    public CareGiver(String name, Date birth_date, String gender, String address) {
        super(name, birth_date, gender, address);

    }
}
