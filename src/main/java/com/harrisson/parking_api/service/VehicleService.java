package com.harrisson.parking_api.service;

import com.harrisson.parking_api.model.Vehicle;
import com.harrisson.parking_api.repository.VehicleRepository;
import com.harrisson.parking_api.to.VehicleData;
import com.harrisson.parking_api.to.VehicleDataDetails;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository repository;

    @Autowired
    private ModelMapper modelMapper;


    public VehicleDataDetails save(VehicleData vehicleData) {
        var vehicle = modelMapper.map(vehicleData, Vehicle.class);
        repository.save(vehicle);
        return modelMapper.map(vehicle, VehicleDataDetails.class);
    }

    public Page<VehicleDataDetails> getVehicles(Pageable page) {
        return repository.findAll(page).map(VehicleDataDetails::new);
    }

    public VehicleDataDetails update(Long id, VehicleData vehicleData) {
        var vehicle = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        modelMapper.map(vehicleData, vehicle);
        repository.save(vehicle);
        return modelMapper.map(vehicle, VehicleDataDetails.class);
    }

    public VehicleDataDetails getById(Long id) {
        var vehicle =  repository.findById(id).orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(vehicle, VehicleDataDetails.class);
    }

    public void deleteVehicle(Long id) {
        repository.deleteById(id);
    }

    public Vehicle getByPlate(String plate) {
        return repository.findByPlate(plate);
    }
}
