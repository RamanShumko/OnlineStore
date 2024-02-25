package com.global.onlinestore.util;

import com.global.onlinestore.dto.DeviceDTO;
import com.global.onlinestore.model.Device;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * Mapper class for converting between Device and DeviceDTO objects.
 */
@Component
@RequiredArgsConstructor
public class Mapper {

    private final ModelMapper modelMapper = new ModelMapper();

    /**
     * Converts a Device object to a DeviceDTO object.
     *
     * @param device The Device object to convert.
     * @return The corresponding DeviceDTO object.
     */
    public DeviceDTO convertToDeviceDTO(Device device) {
        return modelMapper.map(device, DeviceDTO.class);
    }

    /**
     * Converts a DeviceDTO object to a Device object.
     *
     * @param deviceDTO The DeviceDTO object to convert.
     * @return The corresponding Device object.
     */
    public Device convertToDevice(DeviceDTO deviceDTO) {
        return modelMapper.map(deviceDTO, Device.class);
    }
}
