package com.team1.progettocarsharingteam1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team1.progettocarsharingteam1.controllers.VehicleController;
import com.team1.progettocarsharingteam1.dto.VehicleDTO;
import com.team1.progettocarsharingteam1.entities.enums.CityEnum;
import com.team1.progettocarsharingteam1.entities.enums.TypeVehicleEnum;
import com.team1.progettocarsharingteam1.services.VehicleService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(VehicleController.class)
public class VehicleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private VehicleService vehicleService;
    private VehicleDTO vehicleDTO;
    private Optional<VehicleDTO> vehicleOpt;
    private final List<VehicleDTO> vehicleDTOList = new ArrayList<>();


    @BeforeEach
    public void setup() {

        vehicleDTO = new VehicleDTO(1L, "Brand", "Model", "Details", CityEnum.FLORENCE, true, TypeVehicleEnum.CAR);

        vehicleOpt = Optional.of(vehicleDTO);

        vehicleDTOList.add(vehicleDTO);
    }

    @Test
    public void create() throws Exception {
        when(this.vehicleService.create(any())).thenReturn(vehicleDTO);
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(vehicleDTO);
        this.mockMvc.perform(post("/vehicle/create")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content)).andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.brand").value("Brand"))
                .andExpect(jsonPath("$.model").value("Model"));
    }

    @Test
    public void findById_success() throws Exception {
        when(this.vehicleService.findById(anyLong())).thenReturn(vehicleOpt);
        this.mockMvc.perform(get("/vehicle/find/1")).andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.brand").value("Brand"))
                .andExpect(jsonPath("$.model").value("Model"));
    }

    @Test
    public void findById_notFound() throws Exception {
        when(this.vehicleService.findById(anyLong())).thenReturn(Optional.empty());
        this.mockMvc.perform(get("/vehicle/find/1")).andExpect(status().isNotFound());
    }

    @Test
    public void findAll_success() throws Exception {
        when(this.vehicleService.findAll(anyBoolean())).thenReturn(vehicleDTOList);
        this.mockMvc.perform(get("/vehicle/all")).andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(vehicleDTOList.size())))
                .andExpect(jsonPath("$[0].brand").value("Brand"));
    }

    @Test
    public void finAll_notFound() throws Exception {
        when(this.vehicleService.findAll(anyBoolean())).thenReturn(new ArrayList<>());
        this.mockMvc.perform(get("/vehicle/all")).andExpect(status().isNotFound());
    }

    @Test
    public void edit_success() throws Exception {
        when(this.vehicleService.edit(anyLong(), any())).thenReturn(vehicleOpt);
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(vehicleOpt);
        this.mockMvc.perform(put("/vehicle/edit/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content)).andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.brand").value("Brand"))
                .andExpect(jsonPath("$.model").value("Model"));
    }

    @Test
    public void edit_notFound() throws Exception {
        when(this.vehicleService.edit(anyLong(), any())).thenReturn(Optional.empty());
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(vehicleOpt);
        this.mockMvc.perform(put("/vehicle/edit/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(content))
                .andExpect(status().isNotFound());
    }

    @Test
    public void delete_success() throws Exception {
        when(this.vehicleService.delete(anyLong())).thenReturn(vehicleOpt);
        this.mockMvc.perform(delete("/vehicle/delete/1")).andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.brand").value("Brand"))
                .andExpect(jsonPath("$.model").value("Model"));
    }

    @Test
    public void delete_notFound() throws Exception {
        when(this.vehicleService.delete(anyLong())).thenReturn(Optional.empty());
        this.mockMvc.perform(delete("/vehicle/delete/1")).andExpect(status().isNotFound());
    }

    @Test
    public void softDelete_success() throws Exception {
        when(this.vehicleService.softDelete(anyLong())).thenReturn(vehicleOpt);
        this.mockMvc.perform(delete("/vehicle/soft-delete/1")).andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.brand").value("Brand"))
                .andExpect(jsonPath("$.model").value("Model"));
    }

    @Test
    public void softDelete_notFound() throws Exception {
        when(this.vehicleService.softDelete(anyLong())).thenReturn(Optional.empty());
        this.mockMvc.perform(delete("/vehicle/soft-delete/1")).andExpect(status().isNotFound());
    }

    @Test
    public void restore_success() throws Exception {
        when(this.vehicleService.restore(anyLong())).thenReturn(vehicleOpt);
        this.mockMvc.perform(put("/vehicle/restore/1")).andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.brand").value("Brand"))
                .andExpect(jsonPath("$.model").value("Model"));
    }

    @Test
    public void restore_notFound() throws Exception {
        when(this.vehicleService.restore(anyLong())).thenReturn(Optional.empty());
        this.mockMvc.perform(put("/vehicle/restore/1")).andExpect(status().isNotFound());
    }

}
