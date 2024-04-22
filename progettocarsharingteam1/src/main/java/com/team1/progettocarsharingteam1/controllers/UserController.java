package com.team1.progettocarsharingteam1.controllers;

import com.team1.progettocarsharingteam1.dto.UserCleanDTO;
import com.team1.progettocarsharingteam1.dto.UserDTO;
import com.team1.progettocarsharingteam1.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jdk.jfr.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "User", description = "API related to the user")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/create")
    public ResponseEntity<UserCleanDTO> create(@RequestBody UserDTO user) {
        LOGGER.info("creation done");
        return ResponseEntity.ok().body(userService.create(user));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserCleanDTO>> findAll(@RequestParam(required = false, defaultValue = "true") boolean isActive) {
        LOGGER.info("operation completed");
        return ResponseEntity.ok().body(userService.findAll(isActive));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<UserCleanDTO> findById(@PathVariable Long id) {
        Optional<UserCleanDTO> optionalUser = userService.findById(id);
        if (optionalUser.isPresent()) {
            LOGGER.info("operation completed");
            return ResponseEntity.ok(optionalUser.get());
        } else {
            LOGGER.info("user not found");
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<UserCleanDTO> edit(@RequestBody UserDTO user, @PathVariable Long id) {
        Optional<UserCleanDTO> userOptional = userService.edit(id, user);
        if (userOptional.isPresent()) {
            LOGGER.info("operation completed");
            return ResponseEntity.ok(userOptional.get());
        } else {
            LOGGER.info("user not found");
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/delete/{id}")
    ResponseEntity<UserCleanDTO> delete(@PathVariable Long id) {
        Optional<UserCleanDTO> userOptional = userService.delete(id);
        if (userOptional.isPresent()) {
            LOGGER.info("operation completed");
            return ResponseEntity.ok(userOptional.get());
        } else {
            LOGGER.info("user not found");
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/find/name")
    ResponseEntity<List<UserCleanDTO>> findByName(@RequestParam String name) {
        Optional<List<UserCleanDTO>> list = userService.findByName(name);
        if (list.isEmpty()) {
            LOGGER.info("user not found");
            return ResponseEntity.notFound().build();
        } else {
            LOGGER.info("operation completed");
            return ResponseEntity.ok(list.get());
        }
    }

    @GetMapping("/find/surname")
    ResponseEntity<List<UserCleanDTO>> findBySurname(@RequestParam String surname) {
        Optional<List<UserCleanDTO>> list = userService.findBySurname(surname);
        if (list.isEmpty()) {
            LOGGER.info("user not found");
            return ResponseEntity.notFound().build();
        } else {
            LOGGER.info("operation completed");
            return ResponseEntity.ok(list.get());
        }
    }

    /**
     * Endpoint for updating the isActive field of a user
     *
     * @param id       the identifier of the user to be updated
     * @param isActive the boolean value to set for the isActive field
     * @return ResponseEntity containing the updated user, or a 404 Not Found response if the user is not found.
     */
    @Operation(summary = "logical delete of the user")
    @Description("perform a logical delete of an User using the id or return error 404 if the user its not found")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PutMapping("/edit-active/{id}")
    public ResponseEntity<UserCleanDTO> editActive(@PathVariable Long id, @RequestParam boolean isActive) {
        Optional<UserCleanDTO> userOpt = userService.editActive(id, isActive);
        if (userOpt.isPresent()) {
            LOGGER.info("operation completed");
            return ResponseEntity.ok().body(userOpt.get());
        }
        LOGGER.info("user not found");
        return ResponseEntity.notFound().build();
    }

}
