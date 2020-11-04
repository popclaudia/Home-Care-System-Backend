package ro.tuc.ds2020.dtos;


import org.springframework.hateoas.RepresentationModel;
import ro.tuc.ds2020.entities.Patient;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

public class PatientDTO extends RepresentationModel<PatientDTO> {

    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private Date birthdate;
    @NotNull
    private String gender;
    @NotNull
    private String address;
    @NotNull
    private String medical_record;

    private Integer caregiver;

    public PatientDTO(Integer id, String name, Date birthdate, String gender, String address, String medical_record, Integer caregiver) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.medical_record = medical_record;
        this.caregiver=caregiver;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMedical_record() {
        return medical_record;
    }

    public void setMedical_record(String medical_record) {
        this.medical_record = medical_record;
    }

    public Integer getCaregiver() {
        return caregiver;
    }

    public void setCaregiver(Integer caregiver) {
        this.caregiver = caregiver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientDTO that = (PatientDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(birthdate, that.birthdate) &&
                Objects.equals(gender, that.gender) &&
                Objects.equals(address, that.address) &&
                Objects.equals(medical_record, that.medical_record);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, birthdate, gender, address, medical_record);
    }
}



