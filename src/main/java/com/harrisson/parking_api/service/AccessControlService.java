package com.harrisson.parking_api.service;

import com.harrisson.parking_api.enums.Type;
import com.harrisson.parking_api.model.AccessControl;
import com.harrisson.parking_api.model.Establishment;
import com.harrisson.parking_api.model.Vehicle;
import com.harrisson.parking_api.repository.AccessControlRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AccessControlService {

    @Autowired
    private AccessControlRepository accessControlRepository;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private EstablishmentService establishmentService;

    @Autowired
    private ModelMapper modelMapper;

    public AccessControl registerEntry(Long vehicleId, Long establishmentId) {
        var vehicle = vehicleService.getById(vehicleId);
        Establishment establishment = establishmentService.getById(establishmentId);

        var vehicleRegistred = modelMapper.map(vehicle, Vehicle.class);
        AccessControl accessControl = AccessControl.builder()
                .vehicle(vehicleRegistred)
                .establishment(establishment)
                .entryTime(LocalDateTime.now())
                .build();

        return accessControlRepository.save(accessControl);
    }

    public AccessControl registerExit(Long vehicleId, Long establishmentId) {
        AccessControl accessControl = accessControlRepository.findByVehicleIdAndEstablishmentIdAndExitTimeIsNull(vehicleId, establishmentId);
        if (accessControl == null) {
            throw new RuntimeException("Entrada não encontrada para o veículo e estabelecimento especificados");
        }
        accessControl.setExitTime(LocalDateTime.now());
        return accessControlRepository.save(accessControl);
    }
}