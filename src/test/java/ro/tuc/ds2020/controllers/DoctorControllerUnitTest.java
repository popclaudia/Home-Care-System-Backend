package ro.tuc.ds2020.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import ro.tuc.ds2020.Ds2020TestConfig;
import ro.tuc.ds2020.dtos.DoctorDTO;

import java.util.Calendar;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class DoctorControllerUnitTest extends Ds2020TestConfig {
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
