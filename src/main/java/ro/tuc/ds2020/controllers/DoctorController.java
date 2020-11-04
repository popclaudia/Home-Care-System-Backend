package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.DoctorDTO;
import ro.tuc.ds2020.services.DoctorService;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping(value = "/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }
    
    @PostMapping()
    public ResponseEntity<Integer> insertDoctor(@Valid @RequestBody DoctorDTO doctorDTO) {
        Integer DoctorID = doctorService.insert(doctorDTO);
        return new ResponseEntity<>(DoctorID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DoctorDTO> getDoctor(@PathVariable("id") Integer doctorId) {
        DoctorDTO dto = doctorService.findDoctorById(doctorId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
