package com.team1.progettocarsharingteam1;

import com.team1.progettocarsharingteam1.dto.UserCleanDTO;
import com.team1.progettocarsharingteam1.dto.UserDTO;
import com.team1.progettocarsharingteam1.entities.User;
import com.team1.progettocarsharingteam1.repositories.UserRepository;
import com.team1.progettocarsharingteam1.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {


    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;
    private UserDTO userDTO;
    private Optional<User> userOpt;
    private List<User> userList;

    @BeforeEach
    public void setup() {

        user = new User(15L,"Filippo","Rossi", LocalDate.parse("1995-05-01"),"gugdurgurgui@mail.com","fhfjjn45u6785g9","male","via Roma 1","h56757585",true,new ArrayList<>());

        userDTO = new UserDTO();

        userOpt = Optional.of(user);

        userList = List.of(user, user);
    }

    @Test
    public void create() {
        when(this.userRepository.save(any())).thenReturn(user);
        UserCleanDTO savedUserDTO = userService.create(userDTO);

        assertThat(savedUserDTO).isNotNull();
        assertThat(savedUserDTO.getId()).isNotNull();
    }

    @Test
    public void findAll() {
        when(this.userRepository.findAllByIsActiveTrue()).thenReturn(userList);
        List<UserCleanDTO> userDTOList = userService.findAll(true);

        assertThat(userDTOList.size()).isGreaterThan(1);
    }

    @Test
    public void findById() {
        when(this.userRepository.findByIdAndIsActiveTrue(anyLong())).thenReturn(userOpt);
        Optional<UserCleanDTO> userDTOOpt = userService.findById(15L);

        assertThat(userDTOOpt.get().getId()).isNotNull();
    }

    @Test
    public void findById_empty() {
        when(this.userRepository.findByIdAndIsActiveTrue(anyLong())).thenReturn(Optional.empty());
        Optional<UserCleanDTO> userDTOOpt = userService.findById(15L);

        assertThat(userDTOOpt).isEmpty();
    }

    @Test
    public void edit() {
        when(this.userRepository.save(any())).thenReturn(user);
        when(this.userRepository.findByIdAndIsActiveTrue(anyLong())).thenReturn(userOpt);
        Optional<UserCleanDTO> userDTOOpt = userService.edit(15L, userDTO);

        assertThat(userDTOOpt.get().getName()).isEqualTo(userDTO.getName());
        assertThat(userDTOOpt.get().getSurname()).isEqualTo(userDTO.getSurname());
        assertThat(userDTOOpt.get().getDateOfBirth()).isEqualTo(userDTO.getDateOfBirth());
        assertThat(userDTOOpt.get().getSex()).isEqualTo(userDTO.getSex());
    }

    @Test
    public void edit_empty() {
        when(this.userRepository.findByIdAndIsActiveTrue(anyLong())).thenReturn(Optional.empty());
        Optional<UserCleanDTO> userDTOOpt = userService.edit(15L, userDTO);

        assertThat(userDTOOpt).isEmpty();
    }

    @Test
    public void delete() {
        when(this.userRepository.findById(anyLong())).thenReturn(userOpt);
        Optional<UserCleanDTO> userDTOOpt = userService.delete(15L);

        assertThat(userDTOOpt.get().getId()).isNotNull();
    }

    @Test
    public void delete_empty() {
        when(this.userRepository.findById(anyLong())).thenReturn(Optional.empty());
        Optional<UserCleanDTO> userDTOOpt = userService.delete(15L);

        assertThat(userDTOOpt).isEmpty();
    }

    @Test
    public void findByName() {
        when(this.userRepository.findByNameAndIsActiveTrue(anyString())).thenReturn(userList);
        Optional<List<UserCleanDTO>> userDTOListOpt = userService.findByName("qualsiasi");

        assertThat(userDTOListOpt.get().size()).isGreaterThan(1);
    }

    @Test
    public void findByName_empty() {
        when(this.userRepository.findByNameAndIsActiveTrue(anyString())).thenReturn(new ArrayList<>());
        Optional<List<UserCleanDTO>> userDTOListOpt = userService.findByName("qualsiasi");

        assertThat(userDTOListOpt).isEmpty();
    }

    @Test
    public void findBySurname() {
        when(this.userRepository.findBySurnameAndIsActiveTrue(anyString())).thenReturn(userList);
        Optional<List<UserCleanDTO>> userDTOListOpt = userService.findBySurname("qualsiasi");

        assertThat(userDTOListOpt.get().size()).isGreaterThan(1);
    }

    @Test
    public void findBySurname_empty() {
        when(this.userRepository.findBySurnameAndIsActiveTrue(anyString())).thenReturn(new ArrayList<>());
        Optional<List<UserCleanDTO>> userDTOListOpt = userService.findBySurname("qualsiasi");

        assertThat(userDTOListOpt).isEmpty();
    }


}
