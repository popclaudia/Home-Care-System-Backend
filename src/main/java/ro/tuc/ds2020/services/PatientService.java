package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.PatientDTO;
import ro.tuc.ds2020.dtos.builders.PatientBuilder;
import ro.tuc.ds2020.entities.CareGiver;
import ro.tuc.ds2020.entities.Patient;
import ro.tuc.ds2020.repositories.CaregiverRepository;
import ro.tuc.ds2020.repositories.PatientRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PatientService.class);
    private final PatientRepository patientRepository;
    private final CaregiverRepository caregiverRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository, CaregiverRepository caregiverRepository) {
        this.caregiverRepository = caregiverRepository;
        this.patientRepository = patientRepository;
    }

    public List<PatientDTO> findPatients() {
        List<Patient> patientList = patientRepository.findAll();
        return patientList.stream()
                .map(PatientBuilder::toPatientDTO)
                .collect(Collectors.toList());
    }
    public List<PatientDTO> findPatientsCg(Integer id) {
        List<Patient> patientList = patientRepository.findByCaregiver(id);
        return patientList.stream()
                .map(PatientBuilder::toPatientDTO)
                .collect(Collectors.toList());
    }

    public PatientDTO findPatientById(Integer id) {
        Optional<Patient> prosumerOptional = patientRepository.findById(id);
//        List<MedicationPlan> list = prosumerOptional.get().getMedication_plan();
//        for(MedicationPlan i: list)
//            System.out.println(i.getID());
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Patient with id {} was not found in db", id);
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + " with id: " + id);
        }
        return PatientBuilder.toPatientDTO(prosumerOptional.get());
    }

    public void delete(Integer id) {
        Optional<Patient> patientOptional = patientRepository.findById(id);
        if (!patientOptional.isPresent()) {
            LOGGER.error("Patient with id {} doesn't exist in DB", id);
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + " with id: " + id);
        }
        patientRepository.deleteById(id);
        LOGGER.debug("Patient with id {} was deleted from the db", id);
    }

    public Integer insert(PatientDTO patientDTO) {

        CareGiver caregiver = getCaregiver(patientDTO);
        Patient patient = PatientBuilder.toEntity(patientDTO, caregiver);
        patient = patientRepository.save(patient);
        LOGGER.debug("Patient with id {} was inserted in db", patient.getID());
        return patient.getID();

    }

    public PatientDTO update(PatientDTO patientDTO, Integer patientId){

        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        if (!patientOptional.isPresent()) {
            LOGGER.error("Patient with id {} doesn't exist in DB", patientId);
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + " with id: " + patientId);
        }
        CareGiver cg = getCaregiver(patientDTO);
        Patient patient = PatientBuilder.toEntity(patientDTO, cg);
        patient.setID(patientId);
        patientDTO.setId(patientId);
        patientRepository.save(patient);
        return patientDTO;
    }

    public void updateCaregiver(Integer caregiverId, Integer patientId){
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        if (!patientOptional.isPresent()) {
            LOGGER.error("Patient with id {} doesn't exist in DB", patientId);
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + " with id: " + patientId);
        }
        Optional<CareGiver> prosumerOptional = caregiverRepository.findById(caregiverId);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Caregiver with id {} was not found in db", caregiverId);
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + " with id: " + caregiverId);
        }
        Patient patient = patientOptional.get();
        patient.setCaregiver(prosumerOptional.get());
        patientRepository.save(patient);
    }

    public CareGiver getCaregiver(PatientDTO patientDTO){
        Optional<CareGiver> prosumerOptional;
        if (patientDTO.getCaregiver()!=null)
            prosumerOptional = caregiverRepository.findById(patientDTO.getCaregiver());
        else
            return null;
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Caregiver with id {} was not found in db", patientDTO.getCaregiver());
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + " with id: " + patientDTO.getCaregiver());
        }
        return prosumerOptional.get();
    }

}


