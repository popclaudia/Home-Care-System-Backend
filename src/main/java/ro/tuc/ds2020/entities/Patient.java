package ro.tuc.ds2020.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue("Patient")
public class Patient extends User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Integer ID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CAREGIVER_ID", referencedColumnName = "ID", nullable = true)
    private CareGiver caregiver;

    @Column(name = "medical_record")
    private String medical_record;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "patient")
    private List<MedicationPlan> medication_plan;

    public Patient() {
    }

    public Patient(String name, Date birth_date, String gender, String address, String medical_record, CareGiver caregiver) {
        super(name, birth_date, gender, address);
        this.medical_record = medical_record;
        this.caregiver=caregiver;
    }

    @Override
    public Integer getID() {
        return ID;
    }

    @Override
    public void setID(Integer ID) {
        this.ID = ID;
    }

    public CareGiver getCaregiver() {
        return caregiver;
    }

    public void setCaregiver(CareGiver caregiver) {
        this.caregiver = caregiver;
    }

    public String getMedical_record() {
        return medical_record;
    }

    public void setMedical_record(String medical_record) {
        this.medical_record = medical_record;
    }

    public List<MedicationPlan> getMedication_plan() {
        return medication_plan;
    }

    public void setMedication_plan(List<MedicationPlan> medication_plan) {
        this.medication_plan = medication_plan;
    }
}
