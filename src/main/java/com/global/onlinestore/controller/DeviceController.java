package com.global.onlinestore.controller;

import com.global.onlinestore.dto.DeviceDTO;
import com.global.onlinestore.service.DeviceService;
import com.global.onlinestore.util.ErrorValidation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/devices")
public class DeviceController {

    private final DeviceService deviceService;

    @GetMapping("/{id}")
    public DeviceDTO getDevice(@PathVariable Long id) {
        return deviceService.getDeviceById(id);
    }

    @GetMapping
    public List<DeviceDTO> getAllDevices() {
        return deviceService.getAllDevices();
    }

    @PostMapping
    public DeviceDTO createDevice(@Valid @RequestBody DeviceDTO deviceDTO,
                                  BindingResult bindingResult) {
        ErrorValidation.message(bindingResult);

        return deviceService.createDevice(deviceDTO);
    }

    @PutMapping("/{id}")
    public DeviceDTO editDevice(@PathVariable Long id,
                                @Valid @RequestBody DeviceDTO deviceDTO,
                                BindingResult bindingResult) {
        ErrorValidation.message(bindingResult);

        return deviceService.editDeviceById(id, deviceDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDevice(@PathVariable Long id) {
        return deviceService.deleteDeviceById(id);
    }
}
