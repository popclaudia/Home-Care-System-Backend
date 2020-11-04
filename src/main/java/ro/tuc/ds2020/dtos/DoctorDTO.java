package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

public class DoctorDTO extends RepresentationModel<DoctorDTO> {

    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private Date birthdate;
    @NotNull
    private String gender;
    @NotNull
    private String address;

    public DoctorDTO(Integer id, @NotNull String name, @NotNull Date birthdate, @NotNull String gender, @NotNull String address) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorDTO doctorDTO = (DoctorDTO) o;
        return Objects.equals(id, doctorDTO.id) &&
                Objects.equals(name, doctorDTO.name) &&
                Objects.equals(birthdate, doctorDTO.birthdate) &&
                Objects.equals(gender, doctorDTO.gender) &&
                Objects.equals(address, doctorDTO.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, birthdate, gender, address);
    }
}
