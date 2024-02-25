package com.global.onlinestore.service;

import com.global.onlinestore.dto.DeviceDTO;
import com.global.onlinestore.model.Device;
import com.global.onlinestore.repository.DeviceRepository;
import com.global.onlinestore.util.Mapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
class DeviceServiceTest {

    @Autowired
    private DeviceService deviceService;

    @MockBean
    private DeviceRepository deviceRepository;

    @Autowired
    private Mapper mapper;

    private Device device;
    private DeviceDTO deviceDTO;
    private List<Device> deviceList;

    @BeforeEach
    void setUp() {
        device = Device.builder()
                .id(1L)
                .brand("iPhone")
                .model("12")
                .quantity(15)
                .price(new BigDecimal(40_000))
                .build();
        deviceDTO = mapper.convertToDeviceDTO(device);
        deviceList = Collections.singletonList(device);
    }

    @Test
    void getDeviceByID_ValidDeviceId_ReturnsDeviceDTO() {
        when(deviceRepository.findById(device.getId()))
                .thenReturn(Optional.of(device));

        assertThat(deviceService.getDeviceById(device.getId())).isEqualTo(deviceDTO);
        verify(deviceRepository, times(1)).findById(device.getId());
    }

    @Test
    void getDeviceById_InValidDeviceId_ReturnsEntityNotFoundException() {
        assertThrows(EntityNotFoundException.class, () -> deviceService.getDeviceById(0L));
    }

    @Test
    void getAllDevices_ReturnsListDevice() {
        when(deviceRepository.findAll())
                .thenReturn(deviceList);

        assertThat(deviceService.getAllDevices()).isEqualTo(deviceList.stream().map(mapper::convertToDeviceDTO).toList());
        verify(deviceRepository, times(1)).findAll();
    }

    @Test
    void createDevice_ValidDeviceDTO_ReturnsDeviceDTO() {
        when(deviceRepository.save(any(Device.class)))
                .thenReturn(device);

        assertThat(deviceService.createDevice(deviceDTO)).isEqualTo(deviceDTO);
        verify(deviceRepository, times(1)).save(any(Device.class));
    }

    @Test
    void deleteDeviceById_ValidDeviceId_ReturnsResponseEntity() {
        when(deviceRepository.findById(device.getId()))
                .thenReturn(Optional.of(device));
        doNothing().when(deviceRepository).deleteById(device.getId());

        assertThat(deviceService.deleteDeviceById(device.getId()))
                .isEqualTo(ResponseEntity.ok("Device with id:" + device.getId() + " is delete"));
        verify(deviceRepository, times(1)).findById(device.getId());
        verify(deviceRepository, times(1)).deleteById(device.getId());
    }

    @Test
    void editDeviceById_ValidId_ReturnsDeviceDTO() {
        when(deviceRepository.save(any(Device.class)))
                .thenReturn(device);

        assertThat(deviceService.editDeviceById(device.getId(), deviceDTO)).isEqualTo(deviceDTO);
        verify(deviceRepository, times(1)).save(any(Device.class));
    }
}