package com.global.onlinestore.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DeviceDTO {

    private Long id;

    private String brand;

    private String model;

    private int quantity;

    private BigDecimal price;
}
