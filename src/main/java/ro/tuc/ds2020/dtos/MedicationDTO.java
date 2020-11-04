package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotNull;

public class MedicationDTO extends RepresentationModel<MedicationDTO> {


    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private String side_effects;
    @NotNull
    private String dosage;

    public MedicationDTO(@NotNull Integer id, @NotNull String name, @NotNull String side_effects, @NotNull String dosage) {
        this.id = id;
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
