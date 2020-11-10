package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.CaregiverDTO;
import ro.tuc.ds2020.dtos.UserDTO;
import ro.tuc.ds2020.dtos.builders.CaregiverBuilder;
import ro.tuc.ds2020.dtos.builders.UserBuilder;
import ro.tuc.ds2020.entities.CareGiver;
import ro.tuc.ds2020.entities.Patient;
import ro.tuc.ds2020.entities.UserAccount;
import ro.tuc.ds2020.repositories.AccountRepository;
import ro.tuc.ds2020.repositories.CaregiverRepository;
import ro.tuc.ds2020.repositories.PatientRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CaregiverService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CaregiverService.class);
    private final CaregiverRepository caregiverRepository;
    private final PatientRepository patientRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public CaregiverService(CaregiverRepository caregiverRepository,PatientRepository patientRepository, AccountRepository accountRepository ) {
        this.caregiverRepository = caregiverRepository;
        this.patientRepository = patientRepository;
        this.accountRepository = accountRepository;
    }

    public List<CaregiverDTO> findCaregivers() {
        List<CareGiver> CaregiverList = caregiverRepository.findCaregivers();
        return CaregiverList.stream()
                .map(CaregiverBuilder::toCaregiverDTO)
                .collect(Collectors.toList());
    }

    public CaregiverDTO findCaregiverById(Integer id) {
        Optional<CareGiver> prosumerOptional = caregiverRepository.findCaregiverById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Caregiver with id {} was not found in db", id);
            throw new ResourceNotFoundException(CareGiver.class.getSimpleName() + " with id: " + id);
        }
        return CaregiverBuilder.toCaregiverDTO(prosumerOptional.get());
    }

    public void delete(Integer id) {
        Optional<CareGiver> caregiverOptional = caregiverRepository.findCaregiverById(id);
        if (!caregiverOptional.isPresent()) {
            LOGGER.error("Caregiver with id {} doesn't exist in DB", id);
            throw new ResourceNotFoundException(CareGiver.class.getSimpleName() + " with id: " + id);
        }
        if(caregiverOptional.get().getPatients().size()!=0){
            for(Patient p: caregiverOptional.get().getPatients()){
                p.setCaregiver(null);
                patientRepository.save(p);
            }

        }
        caregiverRepository.deleteById(id);
        LOGGER.debug("Caregiver with id {} was deleted from the db", id);
    }

    public Integer insert(CaregiverDTO caregiverDTO) {
        CareGiver caregiver = CaregiverBuilder.toEntity(caregiverDTO);
//        List<Patient> patients=new ArrayList<Patient>();
//        for(Integer i: caregiverDTO.getPatients()) {
//            Optional<Patient> patientOptional = patientRepository.findById(i);
//            patients.add(patientOptional.get());
//        }
//        caregiver.setPatients(patients);
//        for(Patient p: caregiver.getPatients()){
//            System.out.println(p.getName());
//        }
        caregiver = caregiverRepository.save(caregiver);

        UserDTO account=new UserDTO();
        account.setUsername(caregiverDTO.getName());
        account.setPassword("caregiver");
        account.setUser(caregiver.getID());
        UserAccount cont = UserBuilder.toEntity(account, caregiver);
        caregiver.setUser_account(cont);
        cont = accountRepository.save(cont);
        caregiver = caregiverRepository.save(caregiver);
        LOGGER.debug("Caregiver with id {} was inserted in db", caregiver.getID());
        return caregiver.getID();
    }

    public CaregiverDTO update(CaregiverDTO caregiverDTO, Integer caregiverId){

        Optional<CareGiver> CaregiverOptional = caregiverRepository.findById(caregiverId);
        if (!CaregiverOptional.isPresent()) {
            LOGGER.error("Caregiver with id {} doesn't exist in DB", caregiverId);
            throw new ResourceNotFoundException(CareGiver.class.getSimpleName() + " with id: " + caregiverId);
        }
        CareGiver caregiver = CaregiverBuilder.toEntity(caregiverDTO);
        caregiver.setID(caregiverId);
        caregiverDTO.setId(caregiverId);
        caregiverRepository.save(caregiver);
        return caregiverDTO;
    }
}
