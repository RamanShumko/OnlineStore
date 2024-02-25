package com.global.onlinestore.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.global.onlinestore.dto.DeviceDTO;
import com.global.onlinestore.model.Device;
import com.global.onlinestore.service.DeviceService;
import com.global.onlinestore.util.Mapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class DeviceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeviceService deviceServiceTest;

    @Autowired
    private Mapper mapper;

    @Autowired
    private ObjectMapper objectMapper;

    private Device device;
    private DeviceDTO deviceDTO;
    private List<DeviceDTO> deviceDTOList;

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
        deviceDTOList = Collections.singletonList(mapper.convertToDeviceDTO(device));
    }

    @Test
    void getDevice_ValidDeviceId_ReturnsDeviceDTO() throws Exception {
        when(deviceServiceTest.getDeviceById(device.getId())).
                thenReturn(deviceDTO);

        mockMvc.perform(get("/devices/{id}", device.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(deviceDTO)));

        verify(deviceServiceTest, times(1)).getDeviceById(device.getId());
    }

    @Test
    void getDevice_InValidDeviceId_ReturnsEntityNotFoundException() throws Exception{
        when(deviceServiceTest.getDeviceById(0L))
                .thenThrow(new EntityNotFoundException());

        mockMvc.perform(get("/devices/{id}", 0L))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(deviceServiceTest, times(1)).getDeviceById(0L);

    }

    @Test
    void getAllDevices_ReturnsListDeviceDTO() throws Exception {
        when(deviceServiceTest.getAllDevices()).
                thenReturn(deviceDTOList);

        mockMvc.perform(get("/devices"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(deviceDTOList)));

        verify(deviceServiceTest, times(1)).getAllDevices();
    }

    @Test
    void createDevice_ValidDeviceDTO_ReturnsDeviceDTO() throws Exception {
        when(deviceServiceTest.createDevice(deviceDTO)).
                thenReturn(deviceDTO);

        mockMvc.perform(post("/devices")
                        .content(objectMapper.writeValueAsString(deviceDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(deviceDTO)));

        verify(deviceServiceTest, times(1)).createDevice(deviceDTO);
    }

    @Test
    void createDevice_InValidDeviceDTO_ReturnsIllegalArgumentException() throws Exception {
        DeviceDTO deviceBad = DeviceDTO.builder()
                .brand("iPhone")
                .model("12")
                .quantity(-1)
                .price(new BigDecimal(-1))
                .build();

        when(deviceServiceTest.createDevice(deviceBad)).
                thenReturn(deviceBad);

        mockMvc.perform(post("/devices")
                        .content(objectMapper.writeValueAsString(deviceBad))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void editDevice_ReturnsDeviceDTO() throws Exception {
        when(deviceServiceTest.editDeviceById(device.getId(), deviceDTO)).
                thenReturn(deviceDTO);

        mockMvc.perform(put("/devices/{id}", device.getId())
                        .content(objectMapper.writeValueAsString(deviceDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(deviceDTO)));

        verify(deviceServiceTest, times(1)).editDeviceById(device.getId(), deviceDTO);
    }

    @Test
    void deleteDevice_ValidDeviceId_ReturnsResponseEntity() throws Exception {
      when(deviceServiceTest.deleteDeviceById(device.getId())).
                thenReturn(ResponseEntity.ok(any()));

        mockMvc.perform(delete("/devices/{id}", device.getId()))
                .andDo(print())
                .andExpect(status().isOk());

        verify(deviceServiceTest, times(1)).deleteDeviceById(device.getId());
    }

    @Test
    void deleteDevice_InValidDeviceId_ReturnsEntityNotFoundException() throws Exception {
        when(deviceServiceTest.deleteDeviceById(0L)).
                thenThrow(EntityNotFoundException.class);

        mockMvc.perform(delete("/devices/{id}", 0L))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(deviceServiceTest, times(1)).deleteDeviceById(0L);
    }
}