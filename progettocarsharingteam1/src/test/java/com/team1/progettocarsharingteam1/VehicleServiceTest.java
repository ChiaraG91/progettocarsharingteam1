package com.team1.progettocarsharingteam1;

import com.team1.progettocarsharingteam1.dto.VehicleDTO;
import com.team1.progettocarsharingteam1.entities.Vehicle;
import com.team1.progettocarsharingteam1.entities.enums.CityEnum;
import com.team1.progettocarsharingteam1.entities.enums.TypeVehicleEnum;
import com.team1.progettocarsharingteam1.repositories.VehicleRepository;
import com.team1.progettocarsharingteam1.services.VehicleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VehicleServiceTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private VehicleService vehicleService;

    private Vehicle vehicle;
    private VehicleDTO vehicleDTO;
    private Optional<Vehicle> vehicleOpt;
    private List<Vehicle> vehicleList;

    @BeforeEach
    public void setup() {

        vehicle = new Vehicle(1L, TypeVehicleEnum.CAR, "Brand", "Model", "Details", true, new ArrayList<>(), CityEnum.ROME);

        vehicleDTO = new VehicleDTO(1L, "Brand dto", "Model dto", "Details dto", CityEnum.FLORENCE, true, TypeVehicleEnum.CAR);

        vehicleOpt = Optional.of(vehicle);

        vehicleList = List.of(vehicle, vehicle);
    }

    @Test
    public void create() {
        when(this.vehicleRepository.save(any())).thenReturn(vehicle);
        VehicleDTO savedVehicleDTO = vehicleService.create(vehicleDTO);

        assertThat(savedVehicleDTO).isNotNull();
        assertThat(savedVehicleDTO.getId()).isNotNull();
    }

    @Test
    public void findAll() {
        when(this.vehicleRepository.findAllByIsActiveTrue()).thenReturn(vehicleList);
        List<VehicleDTO> vehicleDTOList = vehicleService.findAll(true);

        assertThat(vehicleDTOList.size()).isGreaterThan(1);
    }

    @Test
    public void findById() {
        when(this.vehicleRepository.findByIdAndIsActiveTrue(anyLong())).thenReturn(vehicleOpt);
        Optional<VehicleDTO> vehicleDTOOpt = vehicleService.findById(1L);

        assertThat(vehicleDTOOpt.get().getId()).isNotNull();
    }

    @Test
    public void findById_empty() {
        when(this.vehicleRepository.findByIdAndIsActiveTrue(anyLong())).thenReturn(Optional.empty());
        Optional<VehicleDTO> vehicleDTOOpt = vehicleService.findById(1L);

        assertThat(vehicleDTOOpt).isEmpty();
    }

    @Test
    public void edit() {
        when(this.vehicleRepository.save(any())).thenReturn(vehicle);
        when(this.vehicleRepository.findByIdAndIsActiveTrue(anyLong())).thenReturn(vehicleOpt);
        Optional<VehicleDTO> vehicleDTOOpt = vehicleService.edit(1L, vehicleDTO);

        assertThat(vehicleDTOOpt.get().getBrand()).isEqualTo(vehicleDTO.getBrand());
        assertThat(vehicleDTOOpt.get().getModel()).isEqualTo(vehicleDTO.getModel());
        assertThat(vehicleDTOOpt.get().getDetails()).isEqualTo(vehicleDTO.getDetails());
        assertThat(vehicleDTOOpt.get().getTypeVehicle()).isEqualTo(vehicleDTO.getTypeVehicle());
        assertThat(vehicleDTOOpt.get().getCityEnum()).isEqualTo(vehicleDTO.getCityEnum());
    }

    @Test
    public void edit_empty() {
        when(this.vehicleRepository.findByIdAndIsActiveTrue(anyLong())).thenReturn(Optional.empty());
        Optional<VehicleDTO> vehicleDTOOpt = vehicleService.edit(1L, vehicleDTO);

        assertThat(vehicleDTOOpt).isEmpty();
    }

    @Test
    public void delete() {
        when(this.vehicleRepository.findById(anyLong())).thenReturn(vehicleOpt);
        Optional<VehicleDTO> vehicleDTOOpt = vehicleService.delete(1L);

        assertThat(vehicleDTOOpt.get().getId()).isNotNull();
    }

    @Test
    public void delete_empty() {
        when(this.vehicleRepository.findById(anyLong())).thenReturn(Optional.empty());
        Optional<VehicleDTO> vehicleDTOOpt = vehicleService.delete(1L);

        assertThat(vehicleDTOOpt).isEmpty();
    }

    @Test
    public void findByBrand() {
        when(this.vehicleRepository.findByBrandAndIsActiveTrue(anyString())).thenReturn(vehicleList);
        Optional<List<VehicleDTO>> vehicleDTOListOpt = vehicleService.findByBrand("qualsiasi");

        assertThat(vehicleDTOListOpt.get().size()).isGreaterThan(1);
    }

    @Test
    public void findByBrand_empty() {
        when(this.vehicleRepository.findByBrandAndIsActiveTrue(anyString())).thenReturn(new ArrayList<>());
        Optional<List<VehicleDTO>> vehicleDTOListOpt = vehicleService.findByBrand("qualsiasi");

        assertThat(vehicleDTOListOpt).isEmpty();
    }

    @Test
    public void findByModel() {
        when(this.vehicleRepository.findByModelAndIsActiveTrue(anyString())).thenReturn(vehicleList);
        Optional<List<VehicleDTO>> vehicleDTOListOpt = vehicleService.findByModel("qualsiasi");

        assertThat(vehicleDTOListOpt.get().size()).isGreaterThan(1);
    }

    @Test
    public void findByModel_empty() {
        when(this.vehicleRepository.findByModelAndIsActiveTrue(anyString())).thenReturn(new ArrayList<>());
        Optional<List<VehicleDTO>> vehicleDTOListOpt = vehicleService.findByModel("qualsiasi");

        assertThat(vehicleDTOListOpt).isEmpty();
    }

    @Test
    public void findAllAvailable() {
        when(this.vehicleRepository.findAllAvailable()).thenReturn(vehicleList);
        Optional<List<VehicleDTO>> vehicleDTOListOpt = vehicleService.findAllAvailable();

        assertThat(vehicleDTOListOpt.get().size()).isGreaterThan(1);
    }

    @Test
    public void findAllAvailable_empty() {
        when(this.vehicleRepository.findAllAvailable()).thenReturn(new ArrayList<>());
        Optional<List<VehicleDTO>> vehicleDTOListOpt = vehicleService.findAllAvailable();

        assertThat(vehicleDTOListOpt).isEmpty();
    }

    @Test
    public void findByTypeVehicle() {
        when(this.vehicleRepository.findByTypeVehicleAndIsActiveTrue(any())).thenReturn(vehicleList);
        Optional<List<VehicleDTO>> vehicleDTOListOpt = vehicleService.findByTypeVehicle(TypeVehicleEnum.CAR);

        assertThat(vehicleDTOListOpt.get().size()).isGreaterThan(1);
    }

    @Test
    public void findByTypeVehicle_empty() {
        when(this.vehicleRepository.findByTypeVehicleAndIsActiveTrue(any())).thenReturn(new ArrayList<>());
        Optional<List<VehicleDTO>> vehicleDTOListOpt = vehicleService.findByTypeVehicle(TypeVehicleEnum.CAR);

        assertThat(vehicleDTOListOpt).isEmpty();
    }

    @Test
    public void softDelete() {
        when(this.vehicleRepository.findByIdAndIsActiveTrue(anyLong())).thenReturn(vehicleOpt);
        when(this.vehicleRepository.save(any())).thenReturn(vehicle);
        Optional<VehicleDTO> vehicleDTOOpt = vehicleService.softDelete(1L);

        assertThat(vehicleDTOOpt.get().getId()).isNotNull();
        assertThat(vehicleDTOOpt.get().isAvailable()).isFalse();
    }

    @Test
    public void softDelete_empty() {
        when(this.vehicleRepository.findByIdAndIsActiveTrue(anyLong())).thenReturn(Optional.empty());
        Optional<VehicleDTO> vehicleDTOOpt = vehicleService.softDelete(1L);

        assertThat(vehicleDTOOpt).isEmpty();
    }

    @Test
    public void restore() {
        when(this.vehicleRepository.findById(anyLong())).thenReturn(vehicleOpt);
        when(this.vehicleRepository.save(any())).thenReturn(vehicle);
        Optional<VehicleDTO> vehicleDTOOpt = vehicleService.restore(1L);

        assertThat(vehicleDTOOpt.get().getId()).isNotNull();
    }

    @Test
    public void restore_empty() {
        when(this.vehicleRepository.findById(anyLong())).thenReturn(Optional.empty());
        Optional<VehicleDTO> vehicleDTOOpt = vehicleService.restore(1L);

        assertThat(vehicleDTOOpt).isEmpty();
    }

    @Test
    public void findAllByCityEnum() {
        when(this.vehicleRepository.findAllByCityEnumAndIsActiveTrue(any())).thenReturn(vehicleList);
        Optional<List<VehicleDTO>> vehicleDTOListOpt = vehicleService.findAllByCityEnum(CityEnum.ROME);

        assertThat(vehicleDTOListOpt.get().size()).isGreaterThan(1);
    }

    @Test
    public void findAllByCityEnum_empty() {
        when(this.vehicleRepository.findAllByCityEnumAndIsActiveTrue(any())).thenReturn(new ArrayList<>());
        Optional<List<VehicleDTO>> vehicleDTOListOpt = vehicleService.findAllByCityEnum(CityEnum.ROME);

        assertThat(vehicleDTOListOpt).isEmpty();
    }
}
