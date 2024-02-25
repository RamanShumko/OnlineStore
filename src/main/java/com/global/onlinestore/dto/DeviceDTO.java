package com.global.onlinestore.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Device Information")
public class DeviceDTO {

    @Schema(description = "Device brand")
    @NotBlank(message = "Brand of an device must not be empty!")
    private String brand;

    @Schema(description = "Device model")
    @NotBlank(message = "Model of an device must not be empty!")
    private String model;

    @Schema(description = "Device quantity")
    @PositiveOrZero(message = "Quantity of an device must not be negative!")
    private int quantity;

    @Schema(description = "Device price")
    @PositiveOrZero(message = "Quantity of an device must not be negative!")
    private BigDecimal price;
}
