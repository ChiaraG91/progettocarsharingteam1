package com.team1.progettocarsharingteam1.services;

import com.team1.progettocarsharingteam1.dto.UserDTO;
import com.team1.progettocarsharingteam1.dto.VehicleDTO;
import com.team1.progettocarsharingteam1.entities.Rent;
import com.team1.progettocarsharingteam1.entities.User;
import com.team1.progettocarsharingteam1.entities.Vehicle;
import com.team1.progettocarsharingteam1.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserDTO create(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO ,user);
        User user1 = userRepository.save(user);
        BeanUtils.copyProperties(user1, userDTO);
        return userDTO;
    }


    public List<UserDTO> findAll(boolean isActive) {
        List<UserDTO> userDTOList = new ArrayList<>();
        UserDTO userDTO = new UserDTO();
        if(isActive) {
            List<User> userList = userRepository.findAllByIsActiveTrue();
            for (User user : userList) {
                BeanUtils.copyProperties(user, userDTO);
                userDTOList.add(userDTO);
            }
            return userDTOList;
        }
        List<User> userList = userRepository.findAll();
        for (User user : userList) {
            BeanUtils.copyProperties(user, userDTO);
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    public Optional<UserDTO> findById(Long id) {
        Optional<User> userOptional = userRepository.findByIdAndIsActiveTrue(id);
        if (userOptional.isPresent()) {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(userOptional.get(), userDTO);
            return Optional.of(userDTO);
        } else {
            return Optional.empty();
        }
    }

    public Optional<UserDTO> edit(Long id, UserDTO user) {
        Optional<User> optionalUser = userRepository.findByIdAndIsActiveTrue(id);
        if (optionalUser.isPresent()) {
            UserDTO userDTO = new UserDTO();
            optionalUser.get().setName(user.getName());
            optionalUser.get().setAddress(user.getAddress());
            optionalUser.get().setEmail(user.getEmail());
            optionalUser.get().setSex(user.getSex());
            optionalUser.get().setSurname(user.getSurname());
            optionalUser.get().setLicenseNumber(user.getLicenseNumber());
            optionalUser.get().setTaxId(user.getTaxId());
            optionalUser.get().setDateOfBirth(user.getDateOfBirth());
            optionalUser.get().setVerified(user.isVerified());
            User user1 = userRepository.save(optionalUser.get());
            BeanUtils.copyProperties(user1, userDTO);
            return Optional.of(userDTO);
        } else {
            return Optional.empty();
        }
    }

    public Optional<UserDTO> delete(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            UserDTO userDTO = new UserDTO();
            optionalUser.get().setActive(false);
            User user = userRepository.save(optionalUser.get());
            BeanUtils.copyProperties(user, userDTO);
            return Optional.of(userDTO);
        } else {
            return Optional.empty();
        }
    }

    /**
     * finds users by name
     *
     * @param name of the user
     * @return a list of users with the given name
     */
    public Optional<List<UserDTO>> findByName(String name) {
        List<User> userList = userRepository.findByNameAndIsActiveTrue(name);
        List<UserDTO> userDTOList = new ArrayList<>();
        if (userList.isEmpty()) {
            return Optional.empty();
        } else {
            for (User user : userList) {
                UserDTO userDTO = new UserDTO();
                BeanUtils.copyProperties(user, userDTO);
                userDTOList.add(userDTO);
            }
            return Optional.of(userDTOList);
        }
    }

    /**
     * finds users by surname
     *
     * @param surname surname of the user
     * @return a list of users with the given surname
     */
    public Optional<List<UserDTO>> findBySurname(String surname) {
        List<User> userList = userRepository.findBySurnameAndIsActiveTrue(surname);
        List<UserDTO> userDTOList = new ArrayList<>();
        if (userList.isEmpty()) {
            return Optional.empty();
        } else {
            for (User user : userList) {
                UserDTO userDTO = new UserDTO();
                BeanUtils.copyProperties(user, userDTO);
                userDTOList.add(userDTO);
            }
            return Optional.of(userDTOList);
        }
    }

    /**
     * Sets the isActive field of a user to true or false, effectively performing a soft delete
     *
     * @param id the identifier of the user to be modified.
     * @param isActive the boolean value to set for the isActive field
     * @return an Optional containing the updated user if it exists, or an empty Optional if the user is not found
     */
    public Optional<UserDTO> editActive(Long id, boolean isActive) {
        Optional<User> userOpt = userRepository.findById(id);

        if (userOpt.isPresent()){
            UserDTO userDTO = new UserDTO();
            userOpt.get().setActive(isActive);
            User userUpdated = userRepository.save(userOpt.get());
            BeanUtils.copyProperties(userUpdated, userDTO);
            return Optional.of(userDTO);
        }
        return Optional.empty();
    }

}
