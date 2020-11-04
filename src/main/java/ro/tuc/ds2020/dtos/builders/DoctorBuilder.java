package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.CaregiverDTO;
import ro.tuc.ds2020.dtos.DoctorDTO;
import ro.tuc.ds2020.entities.Doctor;
import ro.tuc.ds2020.entities.Patient;

import java.util.ArrayList;
import java.util.List;

public class DoctorBuilder {

    public static DoctorDTO toDoctorDTO(Doctor doctor) {

        return new DoctorDTO(doctor.getID(), doctor.getName(), doctor.getBirth_date(), doctor.getGender(), doctor.getAddress());

    }

    public static Doctor toEntity(DoctorDTO doctor) {
        return new Doctor(doctor.getName(), doctor.getBirthdate(), doctor.getGender(), doctor.getAddress());
    }

}
