package ro.tuc.ds2020.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ro.tuc.ds2020.Ds2020TestConfig;
import ro.tuc.ds2020.dtos.DoctorDTO;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.services.PersonService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.util.Calendar;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class PersonControllerUnitTest extends Ds2020TestConfig {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void insertDoctorTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        DoctorDTO doctorDTO = new DoctorDTO(1, "Doctor", new Date(1975, Calendar.MARCH, 16),"Male","Somewhere Else street");

        mockMvc.perform(post("/doctor")
                .content(objectMapper.writeValueAsString(doctorDTO))
                .contentType("application/json"))
                .andExpect(status().isCreated());
    }

}
