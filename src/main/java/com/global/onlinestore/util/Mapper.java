package com.global.onlinestore.util;

import com.global.onlinestore.dto.DeviceDTO;
import com.global.onlinestore.model.Device;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Mapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public DeviceDTO convertToDeviceDTO(Device device) {
        return modelMapper.map(device, DeviceDTO.class);
    }

    public Device convertToDevice(DeviceDTO deviceDTO) {
        return modelMapper.map(deviceDTO, Device.class);
    }
}
