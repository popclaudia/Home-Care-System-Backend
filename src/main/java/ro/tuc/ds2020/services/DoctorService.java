package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.DoctorDTO;
import ro.tuc.ds2020.dtos.UserDTO;
import ro.tuc.ds2020.dtos.builders.DoctorBuilder;
import ro.tuc.ds2020.dtos.builders.UserBuilder;
import ro.tuc.ds2020.entities.Doctor;
import ro.tuc.ds2020.entities.UserAccount;
import ro.tuc.ds2020.repositories.AccountRepository;
import ro.tuc.ds2020.repositories.DoctorRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class DoctorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorService.class);
    private final DoctorRepository doctorRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository, AccountRepository accountRepository) {
        this.doctorRepository = doctorRepository;
        this.accountRepository = accountRepository;
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
        UserDTO account=new UserDTO();
        account.setUsername(doctorDTO.getName());
        account.setPassword("doctor");
        account.setUser(doctor.getID());
        UserAccount cont = UserBuilder.toEntity(account, doctor);
        doctor.setUser_account(cont);
        cont = accountRepository.save(cont);
        doctor = doctorRepository.save(doctor);
        LOGGER.debug("Doctor with id {} was inserted in db", doctor.getID());
        return doctor.getID();



    }
}
