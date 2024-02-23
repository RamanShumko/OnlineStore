package com.global.onlinestore.service;

import com.global.onlinestore.dto.DeviceDTO;
import com.global.onlinestore.model.Device;
import com.global.onlinestore.repository.DeviceRepository;
import com.global.onlinestore.util.Mapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final Mapper mapper;

    public DeviceDTO getDeviceById(Long id) {
        return mapper.convertToDeviceDTO(deviceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Device with id:" + id + " was not found")));
    }

    public List<DeviceDTO> getAllDevices() {
        return deviceRepository.findAll()
                .stream()
                .map(mapper::convertToDeviceDTO)
                .toList();
    }

    public DeviceDTO createDevice(DeviceDTO deviceDTO) {
        Device device = mapper.convertToDevice(deviceDTO);

        return mapper.convertToDeviceDTO(deviceRepository.save(device));
    }

    public ResponseEntity<String> deleteDeviceById(Long id) {
        deviceRepository.deleteById(id);

        return ResponseEntity.ok("Device with id:" + id + " is delete");
    }

    public DeviceDTO editDeviceById(Long id, DeviceDTO deviceDTO) {
        Device device = mapper.convertToDevice(deviceDTO);

        device.setId(id);

        return mapper.convertToDeviceDTO(deviceRepository.save(device));
    }
}
