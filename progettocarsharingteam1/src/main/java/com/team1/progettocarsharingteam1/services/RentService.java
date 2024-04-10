package com.team1.progettocarsharingteam1.services;

import com.team1.progettocarsharingteam1.dto.RentDTO;
import com.team1.progettocarsharingteam1.entities.Rent;
import com.team1.progettocarsharingteam1.entities.enums.ChargeEnum;
import com.team1.progettocarsharingteam1.repositories.RentRepository;
import com.team1.progettocarsharingteam1.repositories.UserRepository;
import com.team1.progettocarsharingteam1.repositories.VehicleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RentService {

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehicleRepository vehicleRepository;


    public RentDTO create(RentDTO rentDTO) {
        Rent rent = new Rent();
        BeanUtils.copyProperties(rentDTO, rent);
        rent.setStartTme(LocalDateTime.now());
        Rent rent1 = rentRepository.save(rent);
        BeanUtils.copyProperties(rent1, rentDTO);
        return rentDTO;
    }


    public List<RentDTO> findAll(boolean isActive) {
        List<RentDTO> rentDTOList = new ArrayList<>();
        if(isActive) {
            List<Rent> rentList = rentRepository.findAllByIsActiveTrue();
            for (Rent rent : rentList) {
                RentDTO rentDTO = new RentDTO();
                BeanUtils.copyProperties(rent, rentDTO);
                rentDTOList.add(rentDTO);
            }
            return rentDTOList;
        }
        List<Rent> rentList = rentRepository.findAll();
        for (Rent rent : rentList) {
            RentDTO rentDTO = new RentDTO();
            BeanUtils.copyProperties(rent, rentDTO);
            rentDTOList.add(rentDTO);
        }
        return rentDTOList;
    }

    public Optional<RentDTO> findById(Long id) {
        Optional<Rent> rentOPT = rentRepository.findByIdAndIsActiveTrue(id);
        if (rentOPT.isPresent()) {
            RentDTO rentDTO = new RentDTO();
            BeanUtils.copyProperties(rentOPT.get(), rentDTO);
            return Optional.of(rentDTO);
        } else {
            return Optional.empty();
        }
    }

    public Optional<RentDTO> edit(Long id, Rent rent){
        Optional<Rent> updatedRent = rentRepository.findByIdAndIsActiveTrue(id);
        if (updatedRent.isPresent()){
            RentDTO rentDTO = new RentDTO();
            updatedRent.get().setPrice(rent.getPrice());
            updatedRent.get().setStartTme(rent.getStartTme());
            updatedRent.get().setEndTime(rent.getEndTime());
            Rent rent1 = rentRepository.save(updatedRent.get());
            BeanUtils.copyProperties(rent1, rentDTO);
            return Optional.of(rentDTO);
        } else {
            return Optional.empty();
        }
    }

    public Optional<RentDTO> delete(Long id){
        Optional<Rent> deletedRentOPT = rentRepository.findById(id);
        if(deletedRentOPT.isPresent()){
            RentDTO rentDTO = new RentDTO();
            deletedRentOPT.get().setActive(false);
            Rent rent = rentRepository.save(deletedRentOPT.get());
            BeanUtils.copyProperties(rent, rentDTO);
            return Optional.of(rentDTO);
        }else{
            return Optional.empty();
        }
    }

    public Optional<RentDTO> endRent(Long rentId) {
        Optional<Rent> rentOptional = rentRepository.findById(rentId);
        if (rentOptional.isPresent() && rentOptional.get().isActive()) {
            RentDTO rentDTO = new RentDTO();
            rentOptional.get().setEndTime(LocalDateTime.now());
            rentOptional.get().setPrice(price(rentOptional.get().getStartTme(), rentOptional.get().getEndTime()));
            rentOptional.get().setActive(false);
            Rent rent = rentRepository.save(rentOptional.get());
            BeanUtils.copyProperties(rent, rentDTO);
            return Optional.of(rentDTO);
        } else {
            return Optional.empty();
        }
    }

    public Optional<List<RentDTO>> rentByid(Long id) {
        List<Rent> optionalRents = userRepository.findAllOrdiniByUserId(id);
        if (optionalRents.isEmpty()) {
            return Optional.empty();
        } else {
            List<RentDTO> rentDTOList = new ArrayList<>();
            for (Rent rent : optionalRents) {
                RentDTO rentDTO = new RentDTO();
                BeanUtils.copyProperties(rent, rentDTO);
                rentDTOList.add(rentDTO);
            }
            return Optional.of(rentDTOList);
        }
    }

    /**
     * Sets the isActive field of a rent to true or false, effectively performing a soft delete
     *
     * @param id the identifier of the rent to be modified.
     * @param isActive the boolean value to set for the isActive field
     * @return an Optional containing the updated rent if it exists, or an empty Optional if the rent is not found
     */
    public Optional<RentDTO> editActive(Long id, boolean isActive) {
        Optional<Rent> rentOpt = rentRepository.findById(id);
        if (rentOpt.isPresent()){
            RentDTO rentDTO = new RentDTO();
            rentOpt.get().setActive(isActive);
            Rent rentUpdated = rentRepository.save(rentOpt.get());
            BeanUtils.copyProperties(rentUpdated, rentDTO);
            return Optional.of(rentDTO);
        }
        return Optional.empty();
    }

    public Double price(LocalDateTime start, LocalDateTime end) {
        Duration duration = Duration.between(start, end);
        Long minute = duration.toMinutes();
        if (contine(minute, ChargeEnum.SHORT.getMin(), ChargeEnum.SHORT.getMax())) {
            return minute * ChargeEnum.SHORT.getChargeEnum();
        }
        if (contine(minute, ChargeEnum.MEDIUM.getMin(), ChargeEnum.MEDIUM.getMax())) {
            return minute * ChargeEnum.MEDIUM.getChargeEnum();
        }
        return minute * ChargeEnum.LONG.getChargeEnum();
    }

    public boolean contine(Long valore, Long min, Long max) {
        return valore >= min && valore <= max;
    }
}

