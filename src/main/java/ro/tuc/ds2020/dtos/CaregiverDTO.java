package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;
import ro.tuc.ds2020.entities.Patient;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class CaregiverDTO extends RepresentationModel<CaregiverDTO> {

    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private Date birthdate;
    @NotNull
    private String gender;
    @NotNull
    private String address;

    private List<Integer> patients;

    public CaregiverDTO(){

    }

    public CaregiverDTO(Integer id, String name, Date birthdate, String gender, String address, List<Integer> patients) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.patients=patients;
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

    public List<Integer> getPatients() {
        return patients;
    }

    public void setPatients(List<Integer> patients) {
        this.patients = patients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaregiverDTO that = (CaregiverDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(birthdate, that.birthdate) &&
                Objects.equals(gender, that.gender) &&
                Objects.equals(address, that.address) &&
                Objects.equals(patients, that.patients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, birthdate, gender, address, patients);
    }

}
