package com.team1.progettocarsharingteam1;

import com.team1.progettocarsharingteam1.dto.RentCleanDTO;
import com.team1.progettocarsharingteam1.dto.RentDTO;
import com.team1.progettocarsharingteam1.entities.Rent;
import com.team1.progettocarsharingteam1.entities.User;
import com.team1.progettocarsharingteam1.entities.Vehicle;
import com.team1.progettocarsharingteam1.repositories.RentRepository;
import com.team1.progettocarsharingteam1.services.RentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RentServiceTest {

    @Mock
    private RentRepository rentRepository;

    @InjectMocks
    private RentService rentService;

    private Rent rent;
    private RentDTO rentDTO;
    private Optional<Rent> rentOpt;
    private List<Rent> rentList;

    @BeforeEach
    public void setup() {

        rent = new Rent(1L, LocalDateTime.parse("2024-04-21T20:20:30"),LocalDateTime.parse("2024-04-21T20:20:30"),5.50,new User(),new Vehicle());

        rentDTO = new RentDTO();

        rentOpt = Optional.of(rent);

        rentList = List.of(rent, rent);
    }

    @Test
    public void create() {
        when(this.rentRepository.save(any())).thenReturn(rent);
        RentCleanDTO savedRentDTO = rentService.create(rentDTO);

        assertThat(savedRentDTO).isNotNull();
        assertThat(savedRentDTO.getId()).isNotNull();
    }

    @Test
    public void findAll() {
        when(this.rentRepository.findAllByIsActiveTrue()).thenReturn(rentList);
        List<RentCleanDTO> rentDTOList = rentService.findAll(true);

        assertThat(rentDTOList.size()).isGreaterThan(1);
    }

    @Test
    public void findById() {
        when(this.rentRepository.findByIdAndIsActiveTrue(anyLong())).thenReturn(rentOpt);
        Optional<RentCleanDTO> rentDTOOpt = rentService.findById(1L);

        assertThat(rentDTOOpt.get().getId()).isNotNull();
    }

    @Test
    public void findById_empty() {
        when(this.rentRepository.findByIdAndIsActiveTrue(anyLong())).thenReturn(Optional.empty());
        Optional<RentCleanDTO> rentDTOOpt = rentService.findById(1L);

        assertThat(rentDTOOpt).isEmpty();
    }

    @Test
    public void edit() {
        when(this.rentRepository.save(any())).thenReturn(rent);
        when(this.rentRepository.findByIdAndIsActiveTrue(anyLong())).thenReturn(rentOpt);
        Optional<RentCleanDTO> rentDTOOpt = rentService.edit(1L, rentDTO);

        assertThat(rentDTOOpt.get().getStartTme()).isEqualTo(rentDTO.getStartTme());
        assertThat(rentDTOOpt.get().getEndTime()).isEqualTo(rentDTO.getEndTime());
        assertThat(rentDTOOpt.get().getPrice()).isEqualTo(rentDTO.getPrice());
    }

    @Test
    public void edit_empty() {
        when(this.rentRepository.findByIdAndIsActiveTrue(anyLong())).thenReturn(Optional.empty());
        Optional<RentCleanDTO> rentDTOOpt = rentService.edit(1L, rentDTO);

        assertThat(rentDTOOpt).isEmpty();
    }

    @Test
    public void delete() {
        when(this.rentRepository.findById(anyLong())).thenReturn(rentOpt);
        Optional<RentCleanDTO> rentDTOOpt = rentService.delete(1L);

        assertThat(rentDTOOpt.get().getId()).isNotNull();
    }

    @Test
    public void delete_empty() {
        when(this.rentRepository.findById(anyLong())).thenReturn(Optional.empty());
        Optional<RentCleanDTO> rentDTOOpt = rentService.delete(1L);

        assertThat(rentDTOOpt).isEmpty();
    }


}
