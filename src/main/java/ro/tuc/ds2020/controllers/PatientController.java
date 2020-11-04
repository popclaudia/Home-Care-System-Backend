package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.PatientDTO;
import ro.tuc.ds2020.services.PatientService;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@RequestMapping(value = "/patient")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping()
    public ResponseEntity<List<PatientDTO>> getPatients() {
        List<PatientDTO> dtos = patientService.findPatients();
        for (PatientDTO dto : dtos) {
            Link patientLink = linkTo(methodOn(PatientController.class)
                    .getPatient(dto.getId())).withRel("patientDetails");
            dto.add(patientLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping(value = "caregiver/{id}")
    public ResponseEntity<List<PatientDTO>> getPatientsCg(@PathVariable("id") Integer id) {
        List<PatientDTO> dtos = patientService.findPatientsCg(id);
        for (PatientDTO dto : dtos) {
            Link patientLink = linkTo(methodOn(PatientController.class)
                    .getPatient(dto.getId())).withRel("patientDetails");
            dto.add(patientLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Integer> insertPatient(@Valid @RequestBody PatientDTO patientDTO) {
        Integer patientID = patientService.insert(patientDTO);
        return new ResponseEntity<>(patientID, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Integer> deletePatient(@PathVariable("id") Integer patientId) {
        patientService.delete(patientId);
        return new ResponseEntity<>(patientId, HttpStatus.OK);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PatientDTO> getPatient(@PathVariable("id") Integer patientId) {
        PatientDTO dto = patientService.findPatientById(patientId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PatientDTO> updatePatient(@RequestBody PatientDTO patient, @PathVariable("id") Integer patientId) {
        PatientDTO dto = patientService.update(patient, patientId);
        return new ResponseEntity<>(dto, HttpStatus.OK);

    }

    @PutMapping(value = "caregiver/{id}")
    public ResponseEntity<PatientDTO> updateCaregiverForPatient(@RequestBody Integer caregiver, @PathVariable("id") Integer patientId) {
        patientService.updateCaregiver(caregiver, patientId);
        return new ResponseEntity<>(HttpStatus.OK);

    }


}



