package com.global.onlinestore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DeviceDTO {

    @NotBlank(message = "Brand of an device must not be empty!")
    private String brand;

    @NotBlank(message = "Model of an device must not be empty!")
    private String model;

    @PositiveOrZero(message = "Quantity of an device must not be negative!")
    private int quantity;

    @PositiveOrZero(message = "Quantity of an device must not be negative!")
    private BigDecimal price;
}
