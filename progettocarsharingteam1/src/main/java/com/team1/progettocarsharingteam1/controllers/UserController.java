package com.team1.progettocarsharingteam1.controllers;

import com.team1.progettocarsharingteam1.dto.UserCleanDTO;
import com.team1.progettocarsharingteam1.dto.UserDTO;
import com.team1.progettocarsharingteam1.entities.Rent;
import com.team1.progettocarsharingteam1.entities.Review;
import com.team1.progettocarsharingteam1.entities.User;
import com.team1.progettocarsharingteam1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserCleanDTO> create(@RequestBody UserDTO user) {
        return ResponseEntity.ok().body(userService.create(user));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserCleanDTO>> findAll(@RequestParam(required = false, defaultValue = "true") boolean isActive) {
        return ResponseEntity.ok().body(userService.findAll(isActive));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<UserCleanDTO> findById(@PathVariable Long id) {
        Optional<UserCleanDTO> optionalUser = userService.findById(id);
        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(optionalUser.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<UserCleanDTO> edit(@RequestBody UserDTO user, @PathVariable Long id) {
        Optional<UserCleanDTO> userOptional = userService.edit(id, user);
        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<UserCleanDTO> delete(@PathVariable Long id) {
        Optional<UserCleanDTO> userOptional = userService.delete(id);
        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * find a list of users by name
     *
     * @param name of the user
     * @return a list of users with the given name
     */
    @GetMapping("/find/name")
    ResponseEntity<List<UserCleanDTO>> findByName(@RequestParam String name) {
        Optional<List<UserCleanDTO>> list = userService.findByName(name);
        if (list.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(list.get());
        }
    }

    /**
     * find a list of users by surname given
     *
     * @param surname of the user
     * @return a list of users with the given surname
     */
    @GetMapping("/find/surname")
    ResponseEntity<List<UserCleanDTO>> findBySurname(@RequestParam String surname) {
        Optional<List<UserCleanDTO>> list = userService.findBySurname(surname);
        if (list.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(list.get());
        }
    }

    /**
     * Endpoint for updating the isActive field of a user
     *
     * @param id the identifier of the user to be updated
     * @param isActive the boolean value to set for the isActive field
     * @return ResponseEntity containing the updated user, or a 404 Not Found response if the user is not found.
     */
    @PutMapping("/edit-active/{id}")
    public ResponseEntity<UserCleanDTO> editActive(@PathVariable Long id, @RequestParam boolean isActive) {
        Optional<UserCleanDTO> userOpt = userService.editActive(id, isActive);

        if (userOpt.isPresent()) {
            return ResponseEntity.ok().body(userOpt.get());
        }
        return ResponseEntity.notFound().build();
    }

}
