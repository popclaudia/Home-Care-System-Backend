package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.DoctorDTO;
import ro.tuc.ds2020.dtos.builders.DoctorBuilder;
import ro.tuc.ds2020.entities.Doctor;
import ro.tuc.ds2020.repositories.DoctorRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class DoctorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorService.class);
    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public DoctorDTO findDoctorById(Integer id) {
        Optional<Doctor> prosumerOptional = doctorRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Doctor with id {} was not found in db", id);
            throw new ResourceNotFoundException(Doctor.class.getSimpleName() + " with id: " + id);
        }
        return DoctorBuilder.toDoctorDTO(prosumerOptional.get());
    }

    public Integer insert(DoctorDTO doctorDTO) {
        Doctor doctor = DoctorBuilder.toEntity(doctorDTO);
        doctor = doctorRepository.save(doctor);
        LOGGER.debug("Doctor with id {} was inserted in db", doctor.getID());
        return doctor.getID();



    }
}
