package com.team1.progettocarsharingteam1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team1.progettocarsharingteam1.controllers.RentController;
import com.team1.progettocarsharingteam1.dto.VehicleDTO;
import com.team1.progettocarsharingteam1.entities.Rent;
import com.team1.progettocarsharingteam1.entities.User;
import com.team1.progettocarsharingteam1.entities.Vehicle;
import com.team1.progettocarsharingteam1.entities.enums.CityEnum;
import com.team1.progettocarsharingteam1.entities.enums.TypeVehicleEnum;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RentControllerTest {

    @Autowired
    private RentController rentController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Order(1)
    void createRent() throws Exception {

        Rent rent = new Rent(1L, LocalDateTime.parse("2024-04-21T20:20:30"),LocalDateTime.parse("2024-04-21T20:20:30"),5.50,new User(),new Vehicle());

        String rentJSON = objectMapper.writeValueAsString(rent);

        MvcResult resultActions = this.mockMvc.perform(post("/rent/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(rentJSON)).andDo(print())
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    @Order(2)
    void updateRentById() throws Exception {
        Long rentId = 1L;
        Rent updatedRent = new Rent(1L, LocalDateTime.parse("2024-04-21T20:20:30"),LocalDateTime.parse("2024-04-21T20:20:30"),5.50,new User(),new Vehicle());
        String rentJSON = objectMapper.writeValueAsString(updatedRent);

        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.put("/rent/edit/{id}", rentId)
                        .contentType(MediaType.APPLICATION_JSON).content(rentJSON))
                .andDo(print()).andExpect(status().isOk()).andReturn();

        String content = result.getResponse().getContentAsString();
        Assertions.assertNotNull(content);
    }

    @Test
    @Order(3)
    void readRentList() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/rent/all"))
                .andDo(print()).andReturn();

        List<Rent> rentFromResponseList = objectMapper.readValue(result.getResponse().getContentAsString(), List.class);
        assertThat(rentFromResponseList.size()).isNotZero();
    }

    @Test
    @Order(4)
    void getRent() throws Exception {
        Long rentId = 1L;
        MvcResult resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/rent/find/{id}", rentId))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(rentId)).andReturn();
    }



    @Test
    @Order(5)
    void deleteRent() throws Exception {
        Long rentId = 1L;
        MvcResult result = mockMvc.perform(delete("/rent/delete/{id}",rentId)
                        .param("id", String.valueOf(1L))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();


    }

    @Test
    @Order(6)
    void contextLoads() {
        assertThat(rentController).isNotNull();
    }

}
