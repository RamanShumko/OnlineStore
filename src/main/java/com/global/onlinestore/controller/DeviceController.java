package com.global.onlinestore.controller;

import com.global.onlinestore.dto.DeviceDTO;
import com.global.onlinestore.service.DeviceService;
import com.global.onlinestore.util.ErrorValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/devices")
@Tag(name = "Device", description = "Methods for working with Devices")
public class DeviceController {

    private final DeviceService deviceService;

    @Operation(summary = "Getting device information")
    @GetMapping("/{id}")
    public DeviceDTO getDevice(@PathVariable Long id) {
        return deviceService.getDeviceById(id);
    }

    @Operation(summary = "Getting information about all devices")
    @GetMapping
    public List<DeviceDTO> getAllDevices() {
        return deviceService.getAllDevices();
    }

    @Operation(summary = "Creating a device")
    @PostMapping
    public DeviceDTO createDevice(@Valid @RequestBody DeviceDTO deviceDTO,
                                  BindingResult bindingResult) {
        ErrorValidation.message(bindingResult);

        return deviceService.createDevice(deviceDTO);
    }

    @Operation(summary = "Editing a device")
    @PutMapping("/{id}")
    public DeviceDTO editDevice(@PathVariable Long id,
                                @Valid @RequestBody DeviceDTO deviceDTO,
                                BindingResult bindingResult) {
        ErrorValidation.message(bindingResult);

        return deviceService.editDeviceById(id, deviceDTO);
    }

    @Operation(summary = "Deleting a device")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDevice(@PathVariable Long id) {
        return deviceService.deleteDeviceById(id);
    }
}
