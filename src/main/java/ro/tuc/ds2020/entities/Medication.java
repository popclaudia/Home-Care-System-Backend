package ro.tuc.ds2020.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
public class Medication implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "side_effects", nullable = false)
    private String side_effects;

    @Column(name = "dosage", nullable = false)
    private String dosage;

    @ManyToMany(mappedBy = "medication", cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private Set<MedicationPlan> medication_plans;

    public Medication() {
    }

    public Medication(String name, String side_effects, String dosage) {
        this.name = name;
        this.side_effects = side_effects;
        this.dosage = dosage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<MedicationPlan> getMedication_plans() {
        return medication_plans;
    }

    public void setMedication_plans(Set<MedicationPlan> medication_plans) {
        this.medication_plans = medication_plans;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSide_effects() {
        return side_effects;
    }

    public void setSide_effects(String side_effects) {
        this.side_effects = side_effects;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }
}
